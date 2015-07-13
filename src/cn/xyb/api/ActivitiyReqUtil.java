package cn.xyb.api;

import com.google.gson.Gson;

import android.content.Context;
import android.os.Handler;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.model.activities.ActPeople;
import cn.xyb.model.main.User;

/**
 * @Description: 活动的相关接口
 * ！！！！！！！！！继承了ReqBase  网络请求的通用接口！！！！！！！！！！！！！！！！！！！！！！
 * @author 
 *
 */
public class ActivitiyReqUtil extends ReqBase {
	
	public static void doGetActivities(Context context, IBindData bind,Handler handler){
		String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.GETACTLIST;
		System.out.println(url);
		String param="";
		doExecute(context,bind,param,handler,url,EAPIConsts.ActivityReqType.GETACTLIST);
	}
	
	public static void doSignUpAct(Context context, IBindData bind,ActPeople actPeople,Handler handler){
		String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.SIGNUPACT;
		System.out.println(url);
		String param=getSignActParams(actPeople);
		doExecute(context,bind,param,handler,url,EAPIConsts.ActivityReqType.SIGNUPACT);
	}
	/**
	 * 工具函数
	 * @param actPeople
	 * @return
	 */
	public static String getSignActParams(ActPeople actPeople){
		Gson gson=new Gson();
		String params=gson.toJson(actPeople);
		String str="actpeople="+params;
		return str;
	}
}
