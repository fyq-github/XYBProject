package cn.xyb.ui.main;

import cn.utils.common.CommonUtil;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.R;
import cn.xyb.api.UserReqUtil;
import cn.xyb.base.BaseActivity;
import cn.xyb.model.main.SimpleResult;
import cn.xyb.model.main.User;
import cn.xyb.navigate.CNavigate;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * @author weiyu
 * 注册界面
 */
public class RegisterActivity extends BaseActivity {

	private EditText phonenumberedittest = null;// 手机号
	private EditText emailedittest = null;// 邮箱
	private EditText passwdedittest = null;// 密码
	private EditText passwdagainedittest = null;// 确认密码
	private EditText schooledittest = null;// 学校
	private EditText departmentedittest = null;// 院系
	private EditText engineeringedittest = null;// 专业
	private EditText gradeedittest = null;// 班级
	private EditText studentidedittest = null;// 学号

	private Button vc_ok = null;// 注册
	private ImageButton back = null;// 返回按钮

	ImageView vc_image; // 验证码图标
	Button vc_shuaixi; // 刷新按钮
	String getCode = null;// 获取验证码的值
	EditText vc_code;// 输入文本框的验证码的值
   private User user=new User();
   private SimpleResult simpleResult=new SimpleResult();
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.register);

		phonenumberedittest = (EditText) findViewById(R.id.phonenumberedittest);// 手机号
		emailedittest = (EditText) findViewById(R.id.emailedittest);// 邮箱
		passwdedittest = (EditText) findViewById(R.id.passwdedittest);// 密码
		passwdagainedittest = (EditText) findViewById(R.id.passwdagainedittest);// 确认密码
		schooledittest = (EditText) findViewById(R.id.schooledittest);// 学校
		departmentedittest = (EditText) findViewById(R.id.departmentedittest);// 院系
		engineeringedittest = (EditText) findViewById(R.id.engineeringedittest);// 专业
		gradeedittest = (EditText) findViewById(R.id.gradeedittest);// 班级
		studentidedittest = (EditText) findViewById(R.id.studentidedittest);// 学号

		vc_ok = (Button) findViewById(R.id.vc_ok);// 注册
		back = (ImageButton) findViewById(R.id.back);// 返回按钮

		// 返回登陆界面按钮
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				CNavigate.startLoginActivity(RegisterActivity.this);
				RegisterActivity.this.finish();

			}
		});

		// 验证码

		vc_image = (ImageView) findViewById(R.id.vc_image);
		vc_image.setImageBitmap(Code.getInstance().getBitmap());
		vc_code = (EditText) findViewById(R.id.vc_code);

		getCode = Code.getInstance().getCode(); // 获取显示的验证码
		// Log.e("info", getCode + "----");
		vc_shuaixi = (Button) findViewById(R.id.vc_shuaixin);
		vc_shuaixi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vc_image.setImageBitmap(Code.getInstance().getBitmap());
				getCode = Code.getInstance().getCode();
			}
		});

		vc_ok = (Button) findViewById(R.id.vc_ok);
		vc_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String v_code = vc_code.getText().toString().trim();
				if (v_code == null || v_code.equals("")) {
					CommonUtil.showLongToast("验证码输入为空");
				} else if (!v_code.equals(getCode)) {
					CommonUtil.showLongToast("验证码输入错误");
				} else{//验证码输入正确   注册去
					//Toast.makeText(RegisterActivity.this, "验证码输入正确", 2).show();
					user.setPhonenum(phonenumberedittest.getText().toString());
					user.setEmail(emailedittest.getText().toString());
					user.setPassword(passwdedittest.getText().toString());
					user.setSchool(schooledittest.getText().toString());
					//user.setSchoolid(schoolid);
					user.setDepartment(departmentedittest.getText().toString());
					//user.setDepartmentid(departmentid);
					user.setSubject(engineeringedittest.getText().toString());
					//user.setSubjectid(subjectid);
					user.setClas(gradeedittest.getText().toString());
					//user.setClasid(clasid);
					user.setStunum(studentidedittest.getText().toString());
					UserReqUtil.doRegister(RegisterActivity.this, bindData, user, null);
					showLoadingDialog();
				}
			}
		});

	}
IBindData bindData=new IBindData() {
	@Override
	public void bindData(int tag, Object object) {
		if(tag==EAPIConsts.UserReqType.ADDUSER&&object!=null){
			dismissLoadingDialog();
			simpleResult=(SimpleResult) object;
			if(simpleResult.isResult()){
				CommonUtil.showLongToast(simpleResult.getContent());
				CNavigate.startLoginActivity(RegisterActivity.this);
				RegisterActivity.this.finish();
			}else{
				CommonUtil.showLongToast(simpleResult.getContent());
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
