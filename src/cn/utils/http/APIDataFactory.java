package cn.utils.http;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import cn.xyb.bean.Actlist;
import cn.xyb.bean.AroundDetList;
import cn.xyb.bean.ClassDetail;
import cn.xyb.bean.FunDetailList;
import cn.xyb.model.main.SimpleResult;
import cn.xyb.model.main.User;
import cn.xyb.model.main.UserInfo;
import cn.xyb.model.main.UserLoginResult;

/**
 * 根据tag将服务器返回的数据加工成不同的object
 * @author FYQ
 *
 */
public class APIDataFactory {
	
	private GetPostUtil getPostUtil=new GetPostUtil();
	private String response;
	private String url;
	private String param;
	private SimpleResult simpleResult;
	private UserLoginResult userLoginResult;
	private Gson gson=new Gson();
	private UserInfo userInfo=new UserInfo();
	private User user=new User();
	public APIDataFactory(String url,String param){
		this.url=url;
		this.param=param;
	}
	/**
	 * 根据tag判断何种方式访问网络 get或者post  并加工成object
	 * @param tag
	 * @return
	 */
  public Object createMsgObject(int tag){
		if (tag == EAPIConsts.UserReqType.ADDUSER
				|| tag == EAPIConsts.UserReqType.CHANGEUSER
				|| tag == EAPIConsts.ActivityReqType.SIGNUPACT
				|| tag == EAPIConsts.AroundReqType.ADDAROUNDCOMMENT
				|| tag == EAPIConsts.UserReqType.CHANGEUSERINFO) {
			response = getPostUtil.sendPost(url, param);
			simpleResult = gson.fromJson(response, SimpleResult.class);
			return simpleResult;
		} else if(tag == EAPIConsts.UserReqType.FINDPWD){
			response=getPostUtil.sendGet(url, param);
			simpleResult = gson.fromJson(response, SimpleResult.class);
			return simpleResult;
		}else if( tag == EAPIConsts.UserReqType.EMAILLOGIN
				|| tag == EAPIConsts.UserReqType.PHONELOGIN){
			response = getPostUtil.sendPost(url, param);
			userLoginResult=gson.fromJson(response, UserLoginResult.class);
			return userLoginResult;
		}else if(tag == EAPIConsts.UserReqType.GETUSERINFO){//获取用户详细信息
			response = getPostUtil.sendGet(url, param);
			userInfo=gson.fromJson(response, UserInfo.class);
			return userInfo;
		}else if(tag == EAPIConsts.UserReqType.GETUSER){ //获取用户基本信息
			response = getPostUtil.sendGet(url, param);
			user=gson.fromJson(response, User.class);
			return user;
		}else if(tag == EAPIConsts.ActivityReqType.GETACTLIST){
			response = getPostUtil.sendGet(url, param);
			Actlist actlist=new Actlist();
			actlist=gson.fromJson(response, Actlist.class);
			return actlist;
		}else if(tag == EAPIConsts.AroundReqType.AROUNDDETAILLIST){
			response = getPostUtil.sendGet(url, param);
			AroundDetList aroundDetList=new AroundDetList();
			aroundDetList=gson.fromJson(response, AroundDetList.class);
			return aroundDetList;
		}else if(tag == EAPIConsts.ClassReqType.GETCLASSDETAIL){ //班级信息
			response = getPostUtil.sendGet(url, param);
			ClassDetail classDetail=new ClassDetail();
			classDetail=gson.fromJson(response, ClassDetail.class);
			return classDetail;
		}else if(tag == EAPIConsts.FunReqType.GETFUNDETAILLIST){ //趣吧详细信息
			response = getPostUtil.sendGet(url, param);
			FunDetailList funDetailList=new FunDetailList();
			funDetailList=gson.fromJson(response, FunDetailList.class);
			return funDetailList;
		}
		return null;
  }
 
}
