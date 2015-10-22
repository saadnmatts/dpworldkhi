package com.dpworld.androidapp;

import com.dpworld.androidapp.fragments.FragContainerStatus;
import com.dpworld.androidapp.helpers.DPAppHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PostMenu extends Activity {	
	
	Bundle extras = null;
	
	Intent incomingIntent;
	String title_activity = "";
	int icon = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_menu);
		customAppBar();
		addlayout(title_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_menu, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	void addlayout(String title_name){
		
		if(title_name.equals("Container Status")){
			containerStatus();
		}
	}
	
	void customAppBar(){
		incomingIntent = getIntent();
		extras = incomingIntent.getExtras();
		if(extras != null){
			title_activity = getTitleForAppBar();
			icon = getIconForAppBar();
			setAppbar();
		}		
	}
	
	void setAppbar(){				
		setTitle(getResources().getString(R.string.app_name));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//getActionBar().setIcon(android.R.color.transparent);
	}
	
	String getTitleForAppBar(){
		return incomingIntent.getStringExtra(DPAppHelper.INTENT_EXTRA_TITLE);
	}
	
	int getIconForAppBar(){
		return incomingIntent.getIntExtra(DPAppHelper.INTENT_EXTRA_ICON,-1);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	void containerStatus(){
		FragContainerStatus fragment = new FragContainerStatus();
		fragment.setArguments(extras);
		FragmentManager manager = getFragmentManager();
		manager.beginTransaction()
        .add(R.id.frame_post_menu, fragment).commit();		
	}

}
