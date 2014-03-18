package com.example.wizardexample;

import com.wizardpager.wizard.WizardFragment;
import com.wizardpager.wizard.model.AbstractWizardModel;
import com.wizardpager.wizard.ui.StepPagerStrip;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentExample extends WizardFragment {

	private ViewPager mPager;

	private StepPagerStrip mStepPagerStrip;

	private Button mNextButton;
	private Button mPrevButton;

	//Set layout of Pager
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View myFragmentView = inflater.inflate(R.layout.activity_main, null, false);
		
		mPager = (ViewPager) myFragmentView.findViewById(R.id.pager);
		mStepPagerStrip = (StepPagerStrip) myFragmentView.findViewById(R.id.strip);
		mNextButton = (Button) myFragmentView.findViewById(R.id.next_button);
		mPrevButton = (Button) myFragmentView.findViewById(R.id.prev_button);
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
    @Override
    public boolean useBackForPrevious() {
        return true;
    }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
	}

}