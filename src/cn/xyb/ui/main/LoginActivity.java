package cn.xyb.ui.main;

import cn.utils.common.CommonUtil;
import cn.utils.common.RegexValidateUtil;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.R;
import cn.xyb.api.UserReqUtil;
import cn.xyb.base.BaseActivity;
import cn.xyb.model.main.User;
import cn.xyb.model.main.UserLoginResult;
import cn.xyb.navigate.CNavigate;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class LoginActivity extends BaseActivity {

	private EditText username = null;
	private EditText passwd = null;
	private Button login = null;
	private Button regist = null;
	private Button forget_passwd = null;
	private User user=new User();
	private UserLoginResult loginResult=new UserLoginResult();
	public static SharedPreferences sharedPreferences;
	private int userid;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sharedPreferences = getSharedPreferences("hasUserId", MODE_PRIVATE);
		userid=sharedPreferences.getInt("userid", 0);
		if(userid>0){
			CNavigate.startMainActivity(LoginActivity.this,userid);
			LoginActivity.this.finish();
		}else{
			this.setContentView(R.layout.login);
		username = (EditText) findViewById(R.id.admin);
		passwd = (EditText) findViewById(R.id.passwd);
		login = (Button) findViewById(R.id.adminbt);
		regist = (Button) findViewById(R.id.registbt);
		forget_passwd = (Button) findViewById(R.id.forget_passwd);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("登录");
				user.setPassword(passwd.getText().toString());
				if(RegexValidateUtil.checkEmail(username.getText().toString())){
					System.out.println("邮箱登陆");
					user.setEmail(username.getText().toString());
					UserReqUtil.doEmailLogin(LoginActivity.this, bindData, user, null);
					showLoadingDialog();
				}else if(RegexValidateUtil.checkMobileNumber(username.getText().toString())){
					System.out.println("手机号登陆");
					user.setPhonenum(username.getText().toString());
					UserReqUtil.doPhoneLogin(LoginActivity.this, bindData, user, null);
					showLoadingDialog();
				}else{
					CommonUtil.showToast("用户名有误");
				}

			}
		});

		regist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				CNavigate.startRegisterActivity(LoginActivity.this);
			}
		});
		forget_passwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				CNavigate.startForgetPwdActivity(LoginActivity.this);
			}
		});}
		
	}

	IBindData bindData = new IBindData() {

		@Override
		public void bindData(int tag, Object object) {
			if (tag == EAPIConsts.UserReqType.EMAILLOGIN
					|| tag == EAPIConsts.UserReqType.PHONELOGIN&& object != null) {
				dismissLoadingDialog();
				loginResult=(UserLoginResult) object;
				if (loginResult.isIsuser()) {
					CommonUtil.showToast(loginResult.getReason());
					CNavigate.startMainActivity(LoginActivity.this,loginResult.getUserid());
					Editor editor = sharedPreferences.edit();
					editor.putInt("userid", loginResult.getUserid());
					editor.commit();
					LoginActivity.this.finish();
				} else {
					CommonUtil.showToast(loginResult.getReason());
				}
			}
		}
	};
	@SuppressLint("NewApi")
	@Override
	public void initJabActionBar() {
		jabGetActionBar().hide(); // 隐藏ActionBar
	}
}
