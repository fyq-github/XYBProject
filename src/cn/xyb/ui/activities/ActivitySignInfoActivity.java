package cn.xyb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.utils.common.CommonUtil;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.R;
import cn.xyb.api.ActivitiyReqUtil;
import cn.xyb.base.BaseActivity;
import cn.xyb.model.activities.ActPeople;
import cn.xyb.model.main.SimpleResult;

/**
 * 活动报名信息填写界面
 * 
 * @author FYQ
 * 
 */

public class ActivitySignInfoActivity extends BaseActivity{
	private Button cancel = null; // 取消按钮
	private Button finish = null;// 完成按钮

	private EditText nameedittest = null;// 姓名
	private EditText schooledittest = null;// 学校
	private EditText departmentedittest = null;// 系
	private EditText engineeringedittest = null;// 专业
	private EditText gradeedittest = null;// 班级
	private EditText studentidedittest = null;// 学号
	private EditText phonenumberedittest = null;// 手机号

	private RadioGroup sex = null;// 性别
	private RadioButton male = null;// 男
	private RadioButton female = null;// 女
	private CheckBox tongbu = null;// 同步
    private ActPeople actPeople=new ActPeople();
    private int actid;
    private SimpleResult simpleResult=new SimpleResult();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_information);
		Intent intent = this.getIntent();
		actid = intent.getIntExtra("actid", 0);

		cancel = (Button) findViewById(R.id.cancel);
		finish = (Button) findViewById(R.id.finish);

		nameedittest = (EditText) findViewById(R.id.nameedittest);
		schooledittest = (EditText) findViewById(R.id.schooledittest);
		departmentedittest = (EditText) findViewById(R.id.departmentedittest);
		engineeringedittest = (EditText) findViewById(R.id.engineeringedittest);
		gradeedittest = (EditText) findViewById(R.id.gradeedittest);
		studentidedittest = (EditText) findViewById(R.id.studentidedittest);
		phonenumberedittest = (EditText) findViewById(R.id.phonenumberedittest);

		sex = (RadioGroup) findViewById(R.id.sex);
		male = (RadioButton) findViewById(R.id.male);
		female = (RadioButton) findViewById(R.id.female);
		tongbu = (CheckBox) findViewById(R.id.tongbu);

		// 性别
		sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == male.getId()) {
					actPeople.setSex("男");
				}
				if (checkedId == female.getId()) {
					actPeople.setSex("女");
				}
			}
		});

		// 完成
		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				actPeople.setActid(actid);
				actPeople.setUserid(CommonUtil.getUserId());
				actPeople.setRname(nameedittest.getText().toString());
				actPeople.setDepartment(departmentedittest.getText().toString());
				actPeople.setPhonenum(phonenumberedittest.getText().toString());
				actPeople.setSchool(schooledittest.getText().toString());
				actPeople.setStunum(studentidedittest.getText().toString());
				actPeople.setSubject(engineeringedittest.getText().toString());
				ActivitiyReqUtil.doSignUpAct(ActivitySignInfoActivity.this,iBindData, actPeople, null);
				showLoadingDialog();
			}
		});

		// 取消
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ActivitySignInfoActivity.this.finish();
			}
		});

		// 同步
		tongbu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {

				// TODO Auto-generated method stub
				if (isChecked) {//如果被选中

				} else {

				}

			}
		});
	}

	@Override
	public void initJabActionBar() {
		jabGetActionBar().hide();    
	}
IBindData iBindData=new IBindData() {
	
	@Override
	public void bindData(int tag, Object object) {
		if(tag==EAPIConsts.ActivityReqType.SIGNUPACT&&object!=null){
			dismissLoadingDialog();
			simpleResult=(SimpleResult) object;
			if(simpleResult.isResult()){
				CommonUtil.showToast(simpleResult.getContent());
				ActivitySignInfoActivity.this.finish();
			}else{
				CommonUtil.showToast(simpleResult.getContent());
			}
		}
		
	}
};

}
