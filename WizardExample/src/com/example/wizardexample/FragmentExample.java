package com.example.wizardexample;

import com.wizardpager.wizard.WizardFragment;
import com.wizardpager.wizard.model.AbstractWizardModel;
import com.wizardpager.wizard.model.Page;
import com.wizardpager.wizard.ui.StepPagerStrip;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentExample extends WizardFragment {

	private AbstractWizardModel mWizardModel = new SandwichWizardModel(getActivity());
	
	//Set layout of Pager
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		return mWizardModel;
	}

	//Method that runs after wizard is finished
	@Override
	public void onSubmit() {
		String type = mWizardModel.findByKey("Order type").getData().getString(Page.SIMPLE_DATA_KEY);
		
		if(type.equals("Sandwich")){
			String bread = mWizardModel.findByKey("Sandwich:Bread").getData().getString(Page.SIMPLE_DATA_KEY);
			String meats = "None";
			String veggies = "None";
			String cheeses = "None";
			String toasted = mWizardModel.findByKey("Sandwich:Toasted?").getData().getString(Page.SIMPLE_DATA_KEY);

			if(mWizardModel.findByKey("Sandwich:Meats").getData().get(Page.SIMPLE_DATA_KEY)!=null){
				meats = mWizardModel.findByKey("Sandwich:Meats").getData().get(Page.SIMPLE_DATA_KEY).toString();
			}
			if(mWizardModel.findByKey("Sandwich:Veggies").getData().get(Page.SIMPLE_DATA_KEY)!=null){
				veggies = mWizardModel.findByKey("Sandwich:Veggies").getData().get(Page.SIMPLE_DATA_KEY).toString();				
			}
			if(mWizardModel.findByKey("Sandwich:Cheeses").getData().get(Page.SIMPLE_DATA_KEY).toString()!=null){
				cheeses = mWizardModel.findByKey("Sandwich:Cheeses").getData().get(Page.SIMPLE_DATA_KEY).toString();				
			}

			Toast.makeText(getActivity(), "Ordering Your "+type+ "\n" + bread + ", " + meats + ", " + veggies + ", " + cheeses + ", " + toasted, Toast.LENGTH_LONG).show();
		}
		
		else if(type.equals("Salad")){
			String salad = mWizardModel.findByKey("Salad:Salad type").getData().getString(Page.SIMPLE_DATA_KEY);
			String dressing = mWizardModel.findByKey("Salad:Dressing").getData().getString(Page.SIMPLE_DATA_KEY);
			Toast.makeText(getActivity(), "Ordering Your "+type+ "\n" + salad + ", " + dressing, Toast.LENGTH_LONG).show();
		}
	}
}