package cn.utils.http;

import cn.utils.common.CommonUtil;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 与网络状态有关的工具类
 * @author FYQ
 *
 */
public class NetworkUtil {
	/**
	 * 网络是否连接
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) { 
		if (context != null) { 
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context 
		.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo(); 
		if (mNetworkInfo != null) { 
		return mNetworkInfo.isAvailable(); 
		} 
		} 
		CommonUtil.showToast("当前网络无连接");
		return false; 
		}

}
