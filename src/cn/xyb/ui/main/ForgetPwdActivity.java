package cn.xyb.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import cn.xyb.R;
import cn.xyb.base.BaseActivity;
import cn.xyb.navigate.CNavigate;

/**
 * 
 * @author weiyu 注册界面
 */
public class ForgetPwdActivity extends BaseActivity {

	private EditText emailedittext = null;// 邮箱
	private ImageView vc_image = null; // 验证码图标
	private Button vc_shuaixi = null; // 刷新按钮
	private Button forgetpwd = null; // 找回密码按钮
	private EditText vc_code;// 输入文本框的验证码的值
	private String getCode = null;// 获取验证码的值
	private ImageButton back = null; // 找回密码按钮
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.forgetpwd);

		emailedittext = (EditText) findViewById(R.id.emailedittest);// 邮箱
		forgetpwd = (Button) findViewById(R.id.forgetpwd);// 找回密码
		back = (ImageButton) findViewById(R.id.back);// 返回按钮

		// 返回登陆界面按钮
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				CNavigate.startLoginActivity(ForgetPwdActivity.this);
				ForgetPwdActivity.this.finish();

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

		forgetpwd = (Button) findViewById(R.id.forgetpwd);
		forgetpwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String v_code = vc_code.getText().toString().trim();
				if (v_code == null || v_code.equals("")) {
					Toast.makeText(ForgetPwdActivity.this, "验证码输入为空", 2).show();
				} else if (!v_code.equals(getCode)) {
					Toast.makeText(ForgetPwdActivity.this, "验证码输入错误", 2).show();
				} else {
					Toast.makeText(ForgetPwdActivity.this, "验证码输入正确", 2).show();
				}

			}
		});

	}

	@SuppressLint("NewApi")
	@Override
	public void initJabActionBar() {
		jabGetActionBar().hide(); // 隐藏ActionBar
	}
}