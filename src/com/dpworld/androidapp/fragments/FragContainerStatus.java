package com.dpworld.androidapp.fragments;

import com.dpworld.androidapp.R;
import com.dpworld.androidapp.helpers.DPAppHelper;
import com.dpworld.androidapp.helpers.DPServices;
import com.dpworld.androidapp.models.ModelContainerStatus;
import com.google.gson.Gson;
import android.app.Fragment;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FragContainerStatus extends Fragment {
	
	Bundle getExtras;
	TextView fragTitle, headtitle;
	Button btnSearch;
	EditText searchContainerNo;
	String retainedContainerNo;
	ProgressBar indicatorProgress;
	TableLayout dataTable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.frag_search_container, container,false);
		
		getExtras = getArguments();
		
		headtitle = (TextView) rootView.findViewById(R.id.heading_title);
		headtitle.setText(getTitleForAppBar());			
		
		dataTable = (TableLayout) rootView.findViewById(R.id.table_container_status);
		
		searchContainerNo = (EditText) rootView.findViewById(R.id.search_container_no);
		
		btnSearch = (Button) rootView.findViewById(R.id.btn_search);
		btnSearch.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View view) {
				retainedContainerNo = searchContainerNo.getText().toString();
				if(retainedContainerNo.equals("") || retainedContainerNo.equals(" ")){
					DPAppHelper.quickToast(getActivity(), DPAppHelper.EMPTY_FIELD);
				}
				else{
					dataTable.removeAllViews();
					DoAsync startTask = new DoAsync();
					startTask.execute(retainedContainerNo);	
				}
			}
		});
		
		return rootView;
	}
	
	String getTitleForAppBar(){
		return getExtras.getString(DPAppHelper.INTENT_EXTRA_TITLE);
	}
	
	class DoAsync extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {			
		}
		
		@Override
		protected String doInBackground(String... params) {			
			String result = "";
			try {				
				result = DPServices.containerStatus(params[0], DPAppHelper.MAIN_USERNAME, DPAppHelper.MAIN_PASSWORD);	
			} catch (Exception e) {
			}			
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			ModelContainerStatus getJo = onDataLoaded(result);
			displayData(getJo);						
		}
	}

	ModelContainerStatus onDataLoaded(String jsonResult){
		ModelContainerStatus obj;
		ModelContainerStatus[] items = (new Gson()).fromJson(jsonResult, ModelContainerStatus[].class);
		if(items.length != 0){
			obj = new ModelContainerStatus();
			for (ModelContainerStatus item : items) {
				obj.setEQ_NBR(item.getEQ_NBR());
				obj.setGROUP_ID(item.getGROUP_ID());
				obj.setLOCATION(item.getLOCATION());
				obj.setWEIGHT(item.getWEIGHT());
				obj.setSCAN_STATUS(item.getSCAN_STATUS());
				obj.setANF_STATUS(item.getANF_STATUS());
			}
		}else{
			DPAppHelper.quickToast(getActivity(), "No Data Found.");
			obj = null;			
		}
		return obj;
	}
	
	@SuppressWarnings("deprecation")
	void displayData(ModelContainerStatus object){
		if(object == null){	
			DPAppHelper.longToast(getActivity(), "Data is not found for this Container Number. \n Try Entering Correct Container Number.");
		}
		else{
			drawTable(object);
		}
	}
	
	void drawTable(ModelContainerStatus obj){
		drawTableRow("EQ NBR", obj.getEQ_NBR());
		drawTableRow("GROUP ID", obj.getGROUP_ID());
		drawTableRow("LOCATION", obj.getLOCATION());
		drawTableRow("WEIGHT", obj.getWEIGHT());
		drawTableRow("SCAN STATUS", obj.getSCAN_STATUS());
		drawTableRow("ANF STATUS", obj.getANF_STATUS());
	}
	
	void drawTableRow(String colLeft, String colRight){
		TableRow trow = new TableRow(getActivity());
		trow.addView(drawTableLeftColumn(colLeft));
		trow.addView(drawTableRightColumn(colRight));
		dataTable.addView(trow);		
	}
	
	TextView drawTableLeftColumn(String value){
		TextView colEqnrb = new TextView(getActivity());
		colEqnrb.setPadding(15, 15, 15, 15);
		colEqnrb.setBackgroundColor(getResources().getColor(R.color.icons));
		colEqnrb.setTextColor(getResources().getColor(R.color.dark_blue));
		colEqnrb.setText(value);
		return colEqnrb;
	}	
	TextView drawTableRightColumn(String value){
		TextView colEqnrbVal = new TextView(getActivity());
		colEqnrbVal = new TextView(getActivity());
		colEqnrbVal.setPadding(15, 15, 15, 15);
		colEqnrbVal.setBackgroundColor(getResources().getColor(R.color.icons));
		colEqnrbVal.setGravity(Gravity.RIGHT);
		colEqnrbVal.setTextColor(getResources().getColor(R.color.light_blue));
		colEqnrbVal.setText(value);
		return colEqnrbVal;
	}
}
