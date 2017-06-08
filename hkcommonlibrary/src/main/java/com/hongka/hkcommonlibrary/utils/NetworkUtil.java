package com.hongka.hkcommonlibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

/**
 * 네트워크 정보를 관리하는 class
 * @author 
 *
 */
public class NetworkUtil 
{	
	public static final int NETWORK_TYPE_UNKNOWN = -1;
	public static final int NETWORK_TYPE_MOBILE = ConnectivityManager.TYPE_MOBILE;
	public static final int NETWORK_TYPE_WIFI = ConnectivityManager.TYPE_WIFI;
	public static final int NETWORK_TYPE_WIMAX = 6; //ConnectivityManager.TYPE_WIMAX;
	
	public static final String NETWORK_TYPE_NAME_UNKNOWN = "";
	public static final String NETWORK_TYPE_NAME_MOBILE = "3G";
	public static final String NETWORK_TYPE_NAME_WIFI = "WIFI";
	public static final String NETWORK_TYPE_NAME_WIMAX = "4G";
	
	/**
	 * 실시간 네트워크 상태 파악하여 타입 설정 및 반환
	 * @param context
	 * @return 4G : 6 / 3G : 0 / WIFI : 1
	 */
	public static int getActiveNetworkType(Context context)
	{
    	int networkType = NETWORK_TYPE_UNKNOWN;
    	
    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null)
        {
	        switch (activeNetwork.getType())
	        {
	        case 6: //ConnectivityManager.TYPE_WIMAX: 		// 4G
	        	networkType = NETWORK_TYPE_WIMAX;
	        	break;
	        	
	        case ConnectivityManager.TYPE_MOBILE: 			// 3G
	        	networkType = NETWORK_TYPE_MOBILE;
	            break;
	        	
	        case ConnectivityManager.TYPE_WIFI: 			// Wi-Fi
	        	networkType = NETWORK_TYPE_WIFI;
	        	break;
	        }
        }
        
        return networkType;
	}
	
	/**
	 * 실시간 네트워크 상태 파악하여 String으로 타입반환
	 * 
	 * @param context
	 * @return 4G/3G/WIFI
	 */
	public static String getActiveNetworkTypeName(Context context)
	{
    	int networkType = getActiveNetworkType(context);
    	
    	if (networkType == NETWORK_TYPE_WIMAX)
    	{
    		return NETWORK_TYPE_NAME_WIMAX;
    	}
    	else if (networkType == NETWORK_TYPE_MOBILE)
    	{
    		return NETWORK_TYPE_NAME_MOBILE;
    	}
    	else if (networkType == NETWORK_TYPE_WIFI)
    	{
    		return NETWORK_TYPE_NAME_WIFI;
    	}
    	else
    	{
    		return NETWORK_TYPE_NAME_UNKNOWN;
    	}
	}
	
	/**
	 * 네트워크가 wifi로 연결되어있을 경우 현재 링크 속도를 반환 <p>
	 * Returns the current link speed in LINK_SPEED_UNITS.
	 * @param context
	 * @return link speed
	 */
	public static int getActiveNetworkSpeed(Context context)
	{
		int speed = 0;
		int networkType = getActiveNetworkType(context);
		switch (networkType) {
			case NETWORK_TYPE_WIFI:
				WifiManager wManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
				WifiInfo info = wManager.getConnectionInfo();
				speed =  info.getLinkSpeed(); //mBps
	
				break;
	
			default:
				break;
		}
		return speed;
	}
	
	/**
	 * 요청전 네트웍 상태 체크
	 * wifi 나 mobile network 중 하나가 ok 이면 true 반환
	 * @param context
	 * @return
	 */
	public static boolean getNetworkStatus( Context context)
	{	
		ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo_mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo netInfo_wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		
		NetworkInfo netInfo_bluetooth = null;
		if (Build.VERSION.SDK_INT >=13) {
			netInfo_bluetooth = cm.getNetworkInfo(7);
		}
		
		if (netInfo_mobile == null && netInfo_wifi == null) {
			return false;
		}
		
		try {
			if (netInfo_mobile != null &&  netInfo_mobile.getState() == NetworkInfo.State.CONNECTED ) {
				return true;
			}
			
			if (netInfo_wifi != null &&   netInfo_wifi.getState() == NetworkInfo.State.CONNECTED ) {
				return true;
			}
			
			if (isWiMAXNetworkConnected(cm)) {
				return true;
			}
			
			if (Build.VERSION.SDK_INT >=13) {
				if (netInfo_bluetooth != null && netInfo_bluetooth.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * WiMax 네트웍이냐?
	 * @param connectivityManager
	 * @return
	 */
	private static boolean isWiMAXNetworkConnected(ConnectivityManager connectivityManager)
	{
		// WiMAX: Android 2.2 부터 추가됨.
		if (Build.VERSION.SDK_INT >= 8) {
			//public static final int TYPE_WIMAX = 6;
			NetworkInfo wimaxNetwork = connectivityManager.getNetworkInfo(6);
			
			if (wimaxNetwork != null && wimaxNetwork.isConnected()) { 
				return true; 
			}
		}

		return false;
	}
}
