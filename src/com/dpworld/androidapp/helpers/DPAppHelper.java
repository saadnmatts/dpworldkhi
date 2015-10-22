package com.dpworld.androidapp.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public final class DPAppHelper {
	
	public static final String LOGTAG = "test";
	
	public static final String INTENT_EXTRA_TITLE = "activity_title";
	public static final String INTENT_EXTRA_ICON = "activity_icon";
	public static final String MAIN_USERNAME = "MATS";
	public static final String MAIN_PASSWORD = "abc123";
	
	public static final String EMPTY_FIELD = "Please, Enter the Container Number.";
	
	public static void quickToast(Context c, String s){
		Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
	}
	
	public static void longToast(Context c, String s){
		Toast.makeText(c, s, Toast.LENGTH_LONG).show();
	}
	
    public static boolean isOn(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean isWIFIOn(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean isMobileOn(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (networkInfo != null && networkInfo.isConnected());
    }
	
}
