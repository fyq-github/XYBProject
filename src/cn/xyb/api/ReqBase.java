package cn.xyb.api;

import cn.utils.http.APIAsyncTask;
import cn.utils.http.IBindData;
import android.content.Context;
import android.os.Handler;

/**
 * 网络请求入口类
 * @author 
 *
 */
 
public class ReqBase {
	/**
	 * 网络请求的通用接口
	 * @param context
	 * @param bind
	 * @param param
	 * @param handler
	 * @param url
	 * @param type
	 */
	public static void doExecute(Context context, IBindData bind, String param,
			Handler handler, String url, int type) {
		if(handler == null){
			handler =  new HandlerAPI();
		}
		APIAsyncTask.doExecute(context,bind,param,handler,url,type);
	}
}
