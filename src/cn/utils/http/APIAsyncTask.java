package cn.utils.http;

import java.lang.ref.WeakReference;
import java.net.URL;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
/**
 * 异步处理  从网络上获取数据
 * @author FYQ
 *
 */
public class APIAsyncTask extends AsyncTask<Object, Integer, Object> {
private IBindData bindData=null;
private Context context;
private int tag;
private Handler handler=null; //消息处理器
	public APIAsyncTask(IBindData bind) {
		this.bindData = bind;
	}

	@Override
	protected Object doInBackground(Object... params) {
		if(params[0] instanceof Context){
			context=(Context) params[0];
		}else{
			return null;
		}
		if(params[1] instanceof IBindData){
			bindData=(IBindData) params[1];
		}else{
			return null;
		}
		String param=null;
		if(params[2] instanceof String){
			param=(String) params[2];
		}
		
		if(params[3] instanceof Handler){
			handler=(Handler) params[3];
		}
		String url=null;
		if(params[4] instanceof String){
			url=(String) params[4];
		}
		if(params[5] instanceof Integer){
			tag=(Integer) params[5];
		} 
		//获取网络数据，返回object
		APIDataFactory apiDataFactory=new APIDataFactory(url,param);
		Object resonj=apiDataFactory.createMsgObject(tag);
		return resonj;
	}

	
	@Override  //doInBackground()的返回值  传进来
	protected void onPostExecute(Object result) {
		if (bindData != null) {
			bindData.bindData(tag, result); // 将数据发送给回调函数
		}
	}
	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
   /*
   * 其实这里就是  execute方法  ，只不过为了更好的处理一下
   * 将这个异步处理传过去了  做了  WeakReference  处理
   */
	public static void doExecute(Context context, IBindData bind, String param,
			Handler handler, String url, int type) {
		executeAsync(new APIAsyncTask(bind), context, bind, param,handler,
				url, type);
	}

	private static <T> void executeAsync(final AsyncTask<T, ?, ?> task,final T... args) {
		final WeakReference<AsyncTask<T, ?, ?>> taskReference = new WeakReference<AsyncTask<T, ?, ?>>(
                task);	
		taskReference.get().execute(args); //记得垃圾回收 内存
	}
	
}
