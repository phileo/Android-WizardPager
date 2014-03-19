package com.example.wizardexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	private Button buttonActivity;
	private Button buttonFragment;
	private Button buttonDialog;

	//Set layout of Pager
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		buttonActivity = (Button) findViewById(R.id.activity_button);
		buttonFragment = (Button) findViewById(R.id.fragment_button);
		buttonDialog = (Button) findViewById(R.id.dialog_button);

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
			    frag.show(getSupportFragmentManager(), "dialog");
			}
		});
		
	}

}