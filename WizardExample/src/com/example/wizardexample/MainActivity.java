package com.example.wizardexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	//Set layout of Pager
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button buttonActivity = (Button) findViewById(R.id.activity_button);
		Button buttonFragment = (Button) findViewById(R.id.fragment_button);
		Button buttonDialog = (Button) findViewById(R.id.dialog_button);

		buttonActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intentActivity = new Intent(getBaseContext(), ActivityExample.class);
				intentActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intentActivity);				
			}
		});

		buttonFragment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FragmentExample frag = new FragmentExample();

				getSupportFragmentManager().beginTransaction().
				replace(R.id.fragment_layout, frag,"frag_tag").commit();

				getSupportFragmentManager().executePendingTransactions();

			}
		});

		buttonDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    DialogExample frag = DialogExample.newInstance();
			    frag.show(getSupportFragmentManager(), "dialog_tag");
			}
		});
		
	}
	
	//Override this method if you want fragments to navigate when back is pressed
	@Override
	public void onBackPressed(){
		   Fragment fragment = getSupportFragmentManager().findFragmentByTag("frag_tag");
		   
		   if (fragment instanceof FragmentExample) {
			   ((FragmentExample) fragment).onNavigatePrevious();
		       return;
		   }
		   
		   super.onBackPressed();
	}

}