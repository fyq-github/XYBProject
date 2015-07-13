package cn.xyb.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.model.main.User;
import cn.xyb.model.main.UserInfo;

import com.google.gson.Gson;
/**
 * @Description: 用户的相关接口、用户登录时的一些工具方法
 * ！！！！！！！！！继承了ReqBase  网络请求的通用接口！！！！！！！！！！！！！！！！！！！！！！
 * @author 
 *
 */
public class UserReqUtil extends ReqBase {
	//执行注册操作
public static void doRegister(Context context, IBindData bind,
		User user,Handler handler){
	String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.ADDUSER;//注册地址
	System.out.println(url);
	String param=getUserParams(user);
	doExecute(context,bind,param,handler,url,EAPIConsts.UserReqType.ADDUSER);
}

//修改用户信息（密码）
public static void doChangeUser(Context context, IBindData bind,
	User user,Handler handler){
String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.CHANGEUSER;
System.out.println(url);
String param=getUserParams(user);
doExecute(context,bind,param,handler,url,EAPIConsts.UserReqType.CHANGEUSER);
}

//修改用户详细信息
public static void doChangeUserInfo(Context context, IBindData bind,
		UserInfo userInfo,Handler handler){
String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.CHANGEUSERINFO;
System.out.println(url);
String param=getUserInfoParams(userInfo);
Looper.prepare();
doExecute(context,bind,param,handler,url,EAPIConsts.UserReqType.CHANGEUSERINFO);
}

//找回密码
public static void doFindPwd(Context context, IBindData bind,
		String email,Handler handler){
	String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.FINDPWD+email;
	System.out.println(url);
	String param="";
	doExecute(context,bind,param,handler,url,EAPIConsts.UserReqType.FINDPWD);
}

	//执行邮箱登录操作
	public static void doEmailLogin(Context context, IBindData bind,
			User user,Handler handler){
		String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.EMAILLOGIN;  //邮箱登录地址
		System.out.println(url);
		String param=getUserParams(user);
		doExecute(context,bind,param,handler,url,EAPIConsts.UserReqType.EMAILLOGIN);
	}
	//执行手机号登录操作
		public static void doPhoneLogin(Context context, IBindData bind,
				User user,Handler handler){
			String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.PHONELOGIN;  //手机号登录地址
			System.out.println(url);
			String param=getUserParams(user);
			doExecute(context,bind,param,handler,url,EAPIConsts.UserReqType.PHONELOGIN);
		}
	//获取用户信息
	public static void doGetUserinfo(Context context, IBindData bind,
			int userid,Handler handler){
		String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.GETUSERINFO+userid;//获取用户详细信息
		System.out.println(url);
		String param="";
		doExecute(context,bind,param,handler,url,EAPIConsts.UserReqType.GETUSERINFO);
	}
	//获取用户信息
		public static void doGetUser(Context context, IBindData bind,
				int userid,Handler handler){
			String url = EAPIConsts.COMMON_URL+EAPIConsts.ReqUrl.GETUSER+userid;//获取用户基本信息
			System.out.println(url);
			String param="";
			doExecute(context,bind,param,handler,url,EAPIConsts.UserReqType.GETUSER);
		}
	/**
	 * 工具函数
	 * @param user
	 * @return
	 */
	public static String getUserParams(User user){
		Gson gson=new Gson();
		String params=gson.toJson(user);
		String str="user="+params;
		return str;
	}
	/**
	 * 工具函数
	 * @param userInfo
	 * @return
	 */
	public static String getUserInfoParams(UserInfo userInfo){
		Gson gson=new Gson();
		String params=gson.toJson(userInfo);
		String str="userinfo="+params;
		return str;
	}
}
