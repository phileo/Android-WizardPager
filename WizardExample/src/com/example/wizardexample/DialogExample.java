package com.example.wizardexample;

import com.wizardpager.wizard.WizardDialogFragment;
import com.wizardpager.wizard.model.AbstractWizardModel;
import com.wizardpager.wizard.ui.StepPagerStrip;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DialogExample extends WizardDialogFragment{

	public static DialogExample newInstance() {
		DialogExample frag = new DialogExample();

		//Add arguments if you need them
		Bundle args = new Bundle();
		frag.setArguments(args);

		return frag;
	}


	//Set Style & Theme of Dialog
	@SuppressLint("InlinedApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(android.os.Build.VERSION.SDK_INT>14){
			setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog);
		}
		else{
			setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_light);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.wizard, null, false);

		ViewPager mPager = (ViewPager) myFragmentView.findViewById(R.id.pager);
		StepPagerStrip mStepPagerStrip = (StepPagerStrip) myFragmentView.findViewById(R.id.strip);
		Button mNextButton = (Button) myFragmentView.findViewById(R.id.next_button);
		Button mPrevButton = (Button) myFragmentView.findViewById(R.id.prev_button);
		setControls(mPager, mStepPagerStrip, mNextButton, mPrevButton);

		return myFragmentView;
	}

	//Create Wizard
	@Override
	public AbstractWizardModel onCreateModel() {
		return new SandwichWizardModel(getActivity());
	}

	//Method that runs after wizard is finished
	@Override
	public void onSubmit() {
		DialogFragment dialog = new DialogFragment() {

			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				return new AlertDialog.Builder(getActivity())
				.setTitle(R.string.submit_confirm_title)
				.setMessage(R.string.submit_confirm_message)
				.setPositiveButton(R.string.submit_confirm_button, null)
				.setNegativeButton(android.R.string.cancel, null)
				.create();
			}
		};
		dialog.show(getChildFragmentManager(), "place_order_dialog");
	}

	//Allow back button to be used to go back a step in the wizard
	//Doesn't currently work with fragments
	@Override
	public boolean useBackForPrevious() {
		return true;
	}

}