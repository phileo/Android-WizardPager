package com.example.wizardexample;

import com.wizardpager.wizard.WizardActivity;
import com.wizardpager.wizard.model.AbstractWizardModel;
import com.wizardpager.wizard.ui.StepPagerStrip;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.widget.Button;

public class ActivityExample extends WizardActivity {

	//Set layout of Pager
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wizard);

		ViewPager mPager = (ViewPager) findViewById(R.id.pager);
		StepPagerStrip mStepPagerStrip = (StepPagerStrip) findViewById(R.id.strip);
		Button mNextButton = (Button) findViewById(R.id.next_button);
		Button mPrevButton = (Button) findViewById(R.id.prev_button);
		setControls(mPager, mStepPagerStrip, mNextButton, mPrevButton);
	}

	//Create Wizard
	@Override
	public AbstractWizardModel onCreateModel() {
		return new SandwichWizardModel(this);
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
		dialog.show(getSupportFragmentManager(), "place_order_dialog");
	}

	//Allow back button to be used to go back a step in the wizard
    @Override
    public boolean useBackForPrevious() {
        return true;
    }

}