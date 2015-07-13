package cn.xyb.api;

import android.content.Context;
import android.os.Handler;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;

/**
 * @Description: 班级的相关接口
 * ！！！！！！！！！继承了ReqBase  网络请求的通用接口！！！！！！！！！！！！！！！！！！！！！！
 * @author 
 *
 */
public class ClassReqUtil extends ReqBase {
	//获取用户信息
		public static void doGetClassDetail(Context context, IBindData bind,
				int classid,Handler handler){
			String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.GETCLASSDETAIL+classid;//获取班级信息
			System.out.println(url);
			String param="";
			doExecute(context,bind,param,handler,url,EAPIConsts.ClassReqType.GETCLASSDETAIL);
		}
}
