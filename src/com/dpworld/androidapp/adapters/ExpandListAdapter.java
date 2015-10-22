package com.dpworld.androidapp.adapters;

import java.util.ArrayList;

import com.dpworld.androidapp.R;
import com.dpworld.androidapp.R.layout;
import com.dpworld.androidapp.models.ExpandParent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class ExpandListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<ExpandParent> fullList;
	
	public ExpandListAdapter(Context context, ArrayList<ExpandParent> fullList) {
		this.context = context;
		this.fullList = fullList;
	}

	@Override
	public Object getChild(int parentPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int parentPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int parentPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parentView) {
		String childText = (String) getChild(parentPosition, childPosition);
		View v = convertView;
		if(v == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.expand_child,null);
		}
		CheckedTextView textview = (CheckedTextView) v.findViewById(R.id.expand_child);
		textview.setText(childText);
		return v;
	}

	@Override
	public int getChildrenCount(int parentPosition) {
		return 0;
	}

	@Override
	public Object getGroup(int parentPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return 0;
	}

	@Override
	public long getGroupId(int parentPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int parentPosition, boolean isLastChild, View convertView, ViewGroup parentView) {
		String parentText = (String) getGroup(parentPosition);
		View v = convertView;
		if(v == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.expand_parent, null);
		}
		TextView textview = (TextView) v.findViewById(R.id.expand_parent);
		textview.setText(parentText);
		return v;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int parentPosition, int childPosition) {
		return false;
	}

}
