package cn.xyb.ui.clas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import cn.xyb.R;
import cn.xyb.base.BaseActivity;
/**
 * 班级简介界面
 * @author FYQ
 *
 */
public class ClassIntroducActivity extends BaseActivity {
private String classintroduct;
private TextView classintro;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		classintroduct=intent.getStringExtra("classintroduct");//得到班级简介
		setContentView(R.layout.classintroactivity);
		classintro=(TextView) findViewById(R.id.clas_intro_txt);
		classintro.setText(classintroduct);
	}

	@Override
	public void initJabActionBar() {
		// TODO Auto-generated method stub

	}

}
