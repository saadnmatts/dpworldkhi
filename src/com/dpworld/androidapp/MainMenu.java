package com.dpworld.androidapp;

import com.dpworld.androidapp.adapters.MenuGridAdapter;
import com.dpworld.androidapp.helpers.DPAppHelper;
import com.dpworld.androidapp.helpers.DPServices;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainMenu extends Activity {
	
	Intent gridItemIntent = null;
	
	GridView inquiryGridView, serviceGridView, helpGridView;
	MenuGridAdapter inquiryGridAdapter, serviceGridAdapter, helpGridAdapter;
	
	String sendingTitleExtra = "";
	int sendingIconExtra;
	
	String[] inquiryOptions, serviceOptions, helpOptions;
	
	public Integer[] inquiryIcons = {
			R.drawable.ic_container,R.drawable.ic_bill,
			R.drawable.ic_custom,R.drawable.ic_vessel
	};
	
	public Integer[] serviceIcons = {
			R.drawable.ic_weighment, R.drawable.ic_sampling,
			R.drawable.ic_scan,R.drawable.ic_seal,
			R.drawable.ic_custom,R.drawable.ic_import,
			R.drawable.ic_fumigation
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		gridItemIntent = new Intent(MainMenu.this,PostMenu.class);
		
		inquiryOptions = getResources().getStringArray(R.array.options_inquiry);
		inquiryGridView = (GridView) findViewById(R.id.grids_inquiry);		
		inquiryGridAdapter = new MenuGridAdapter(this, inquiryIcons, inquiryOptions);
		inquiryGridView.setAdapter(inquiryGridAdapter);
		inquiryGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				sendingTitleExtra = inquiryOptions[position];
				sendingIconExtra = inquiryIcons[position];
				postMenu(sendingTitleExtra,sendingIconExtra);
			}
		});
		
		serviceOptions = getResources().getStringArray(R.array.options_services);
		serviceGridView = (GridView) findViewById(R.id.grids_service);
		serviceGridAdapter = new MenuGridAdapter(this, serviceIcons, serviceOptions);
		serviceGridView.setAdapter(serviceGridAdapter);
		serviceGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				sendingTitleExtra = serviceOptions[position];
				sendingIconExtra = serviceIcons[position];
				postMenu(sendingTitleExtra,sendingIconExtra);
			}
		});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	void postMenu(String sendingTitle, int sendingIcon){
		gridItemIntent.putExtra(DPAppHelper.INTENT_EXTRA_TITLE, sendingTitle);
		gridItemIntent.putExtra(DPAppHelper.INTENT_EXTRA_ICON, sendingIcon);
		startActivity(gridItemIntent);		
	}

}
