package cn.xyb.navigate;

import java.util.List;

import cn.xyb.bean.ClassDetail;
import cn.xyb.model.activities.Activities;
import cn.xyb.model.around.AroundDetail;
import cn.xyb.model.clas.ClassImage;
import cn.xyb.model.main.User;
import cn.xyb.model.main.UserInfo;
import cn.xyb.ui.activities.ActivityDetailActivity;
import cn.xyb.ui.activities.ActivitySignInfoActivity;
import cn.xyb.ui.around.AroundItemDetailActivity;
import cn.xyb.ui.around.AroundPubCommentActivity;
import cn.xyb.ui.clas.ClassIntroducActivity;
import cn.xyb.ui.clas.ClassPictruesActivity;
import cn.xyb.ui.clas.ClassTimeActivity;
import cn.xyb.ui.clas.PersonIntroducActivity;
import cn.xyb.ui.fun.FunItemDetailActivity;
import cn.xyb.ui.fun.FunLoveActivity;
import cn.xyb.ui.fun.FunLovePublishActivity;
import cn.xyb.ui.info.InfoMyselfActivity;
import cn.xyb.ui.info.InfoNewActivity;
import cn.xyb.ui.main.ForgetPwdActivity;
import cn.xyb.ui.main.LoginActivity;
import cn.xyb.ui.main.MainActivity;
import cn.xyb.ui.main.RegisterActivity;
import cn.xyb.ui.main.SearchActivity;
import cn.xyb.ui.main.user.ChangePwdActivity;
import cn.xyb.ui.main.user.CreatePicturesActivity;
import cn.xyb.ui.main.user.PersonInfoActivity;
import cn.xyb.ui.main.user.PersonMainActivity;
import cn.xyb.ui.main.user.PersonPicturesActivity;
import cn.xyb.ui.main.user.PersonResumeActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
/**
 * 导航类 Activity的跳转
 * @author FYQ
 *
 */
public class CNavigate {
	//跳转到登录界面
public static void startLoginActivity(Activity fromActivity){
	Intent intent = new Intent(fromActivity, LoginActivity.class);
	fromActivity.startActivity(intent);
}
//跳转到注册界面
public static void startRegisterActivity(Activity fromActivity){
	Intent intent = new Intent(fromActivity, RegisterActivity.class);
	fromActivity.startActivity(intent);
}
//跳转到主界面
public static void startMainActivity(Activity fromActivity,int userid){
	Intent intent = new Intent(fromActivity, MainActivity.class);
	intent.putExtra("userid", userid);
	fromActivity.startActivity(intent);
}
//跳转到主界面
public static void startSearchActivity(Activity fromActivity){
	Intent intent = new Intent(fromActivity, SearchActivity.class);
	fromActivity.startActivity(intent);
}
//跳转忘记密码主界面
public static void startForgetPwdActivity(Activity fromActivity){
	Intent intent = new Intent(fromActivity, ForgetPwdActivity.class);
	fromActivity.startActivity(intent);
}
//跳转活动详情界面，传活动ID  actid
public static void startActivityDetailActivity(Activity fromActivity,Activities activities){
	Intent intent = new Intent(fromActivity, ActivityDetailActivity.class);
	Bundle actBundle=new Bundle();
	actBundle.putSerializable("activities", activities);
	intent.putExtra("actBundle", actBundle);
	fromActivity.startActivity(intent);
}
//跳转活动报名信息界面  传活动ID  actid
public static void startActivitySignInfoActivity(Activity fromActivity,int actid){
	Intent intent = new Intent(fromActivity, ActivitySignInfoActivity.class);
	intent.putExtra("actid", actid);
	fromActivity.startActivity(intent);
}
//跳转班级简介界面  传班级ID  classid
public static void startClassIntroducActivity(Activity fromActivity,String classintroduct){
	Intent intent = new Intent(fromActivity, ClassIntroducActivity.class);
	intent.putExtra("classintroduct", classintroduct);
	fromActivity.startActivity(intent);
}
//跳转班级相册界面  传班级ID  classid
public static void startClassPictruesActivity(Activity fromActivity,ClassDetail classDetail){
	Intent intent = new Intent(fromActivity, ClassPictruesActivity.class);
	Bundle imglistbundle=new Bundle();
	imglistbundle.putSerializable("classDetail", classDetail);
	intent.putExtra("imglistbundle", imglistbundle);
	fromActivity.startActivity(intent);
}
//跳转班级时光轴界面  传班级ID  classid
public static void startClassTimeActivity(Activity fromActivity,int classid){
	Intent intent = new Intent(fromActivity, ClassTimeActivity.class);
	intent.putExtra("classid", classid);
	fromActivity.startActivity(intent);
}
//跳转班级个人简介界面  传班级成员ID  userid
public static void startPersonIntroducActivity(Activity fromActivity,UserInfo userInfo){
	Intent intent = new Intent(fromActivity, PersonIntroducActivity.class);
	Bundle userinfobundle=new Bundle();
	userinfobundle.putSerializable("userInfo", userInfo);
	intent.putExtra("userinfobundle", userinfobundle);
	fromActivity.startActivity(intent);
}
//跳转资讯自定义的界面  传资讯ID  infoid
public static void startInfoMyselfActivity(Activity fromActivity,int infoid){
	Intent intent = new Intent(fromActivity, InfoMyselfActivity.class);
	intent.putExtra("infoid", infoid);
	fromActivity.startActivity(intent);
}
//跳转资讯引新闻界面  传资讯ID  infoid
public static void startInfoNewActivity(Activity fromActivity,int infoid){
	Intent intent = new Intent(fromActivity, InfoNewActivity.class);
	intent.putExtra("infoid", infoid);
	fromActivity.startActivity(intent);
}
//跳转趣吧某一条目界面  传趣吧条目ID  funid
public static void startFunItemDetailActivity(Activity fromActivity,int funid){
	Intent intent = new Intent(fromActivity, FunItemDetailActivity.class);
	intent.putExtra("funid", funid);
	fromActivity.startActivity(intent);
}

//跳转趣吧表白墙界面  传趣吧条目ID  funid
public static void startFunLoveActivity(Activity fromActivity,int funid){
	Intent intent = new Intent(fromActivity, FunLoveActivity.class);
	intent.putExtra("funid", funid);
	fromActivity.startActivity(intent);
}
//跳转趣吧发布表白界面  传趣吧条目ID  funid
public static void startFunLovePublishActivity(Activity fromActivity,int funid){
	Intent intent = new Intent(fromActivity, FunLovePublishActivity.class);
	intent.putExtra("funid", funid);
	fromActivity.startActivity(intent);
}
//跳转周边条目详情界面 周边条目ID  aroundid
public static void startAroundItemDetailActivity(Activity fromActivity,AroundDetail aroundDetail){
	Intent intent = new Intent(fromActivity, AroundItemDetailActivity.class);
	Bundle aroundDetbundle=new Bundle();
	aroundDetbundle.putSerializable("aroundDetail", aroundDetail);
	intent.putExtra("araroundDetbundleoundid", aroundDetbundle);
	fromActivity.startActivity(intent);
}
//跳转周边条目发布评论界面 周边条目ID  aroundid
public static void startAroundPubCommentActivity(Activity fromActivity,int aroundid){
	Intent intent = new Intent(fromActivity, AroundPubCommentActivity.class);
	intent.putExtra("aroundid", aroundid);
	fromActivity.startActivity(intent);
}
//跳转个人主界面   传入用户ID  userid
public static void startPersonMainActivity(Activity fromActivity,User user,UserInfo userInfo){
	Intent intent = new Intent(fromActivity, PersonMainActivity.class);
	Bundle bundle=new Bundle();
	bundle.putSerializable("user", user);
	bundle.putSerializable("userInfo", userInfo);
	intent.putExtra("bundle", bundle);
	fromActivity.startActivity(intent);
}
//跳转修改密码界面   传入用户ID  userid
public static void startChangePwdActivity(Activity fromActivity,int userid){
	Intent intent = new Intent(fromActivity, ChangePwdActivity.class);
	intent.putExtra("userid", userid);
	fromActivity.startActivity(intent);
}
//跳转个人简历编辑界面   传入用户ID  userid
public static void startPersonResumeActivity(Activity fromActivity,int userid){
	Intent intent = new Intent(fromActivity, PersonResumeActivity.class);
	intent.putExtra("userid", userid);
	fromActivity.startActivity(intent);
}
//跳转个人信息界面   传入用户ID  userid
public static void startPersonInfoActivity(Activity fromActivity,int userid){
	Intent intent = new Intent(fromActivity, PersonInfoActivity.class);
	intent.putExtra("userid", userid);
	fromActivity.startActivity(intent);
}
//跳转个人相册界面   传入用户ID  userid
public static void startPersonPicturesActivity(Activity fromActivity,int userid){
	Intent intent = new Intent(fromActivity, PersonPicturesActivity.class);
	intent.putExtra("userid", userid);
	fromActivity.startActivity(intent);
}
//跳转个人创建相册界面   传入用户ID  userid
public static void startCreatePicturesActivity(Activity fromActivity,int userid){
	Intent intent = new Intent(fromActivity, CreatePicturesActivity.class);
	intent.putExtra("userid", userid);
	fromActivity.startActivity(intent);
}
}
