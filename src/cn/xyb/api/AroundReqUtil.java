package cn.xyb.api;

import com.google.gson.Gson;

import android.content.Context;
import android.os.Handler;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.model.around.AroundComments;

/**
 * @Description: 周边的相关接口
 * ！！！！！！！！！继承了ReqBase  网络请求的通用接口！！！！！！！！！！！！！！！！！！！！！！
 * @author 
 *
 */
public class AroundReqUtil extends ReqBase {
	//获取周边某类别的list
		public static void doGetAroundDetailList(Context context, IBindData bind,
				int merchantsid,Handler handler){
			String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.AROUNDDETAILLIST+merchantsid;
			System.out.println(url);
			String param="";
			doExecute(context,bind,param,handler,url,EAPIConsts.AroundReqType.AROUNDDETAILLIST);
		}

	public static void doAroundPub(Context context, IBindData bind,
			AroundComments aroundComments, Handler handler) {
		String url = EAPIConsts.COMMON_URL + EAPIConsts.ReqUrl.ADDAROUNDCOMMENT;
		System.out.println(url);
		String param = getAroundPubParams(aroundComments);
		System.out.println(param);
		doExecute(context, bind, param, handler, url,
				EAPIConsts.AroundReqType.ADDAROUNDCOMMENT);
	}

	/**
	 * 
	 * @param aroundComments
	 * @return
	 */
	public static String getAroundPubParams(AroundComments aroundComments) {
		Gson gson = new Gson();
		String params = gson.toJson(aroundComments);
		String str = "comment=" + params;
		return str;
	}
}
