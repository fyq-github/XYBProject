package cn.xyb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.xyb.R;
import cn.xyb.base.BaseActivity;
import cn.xyb.model.activities.Activities;
import cn.xyb.navigate.CNavigate;
import android.view.View.OnClickListener;

/**
 * 活动报名信息填写界面
 * 
 * @author FYQ
 * 
 */

public class ActivityDetailActivity extends BaseActivity {
	//ImageButton back = null; // 返回按钮
	Button care = null;// 关注按钮
	Button share = null;// 分享按钮
	Button signup = null;// 参加活动按钮
	TextView activitycontent = null;// 活动内容
	TextView activitysummary;
	private Bundle actBundle;
	private Activities activities;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_information);
		Intent intent = this.getIntent();
		actBundle = intent.getBundleExtra("actBundle");
		activities = (Activities) actBundle.getSerializable("activities");

		//back = (ImageButton) findViewById(R.id.back);
		care = (Button) findViewById(R.id.care);
		share = (Button) findViewById(R.id.share);
		signup = (Button) findViewById(R.id.signup);

		activitycontent = (TextView) findViewById(R.id.activitycontent);
		// 活动的内容
		activitycontent.setText(activities.getActcontent());
		activitysummary=(TextView) findViewById(R.id.activity_summary);
		activitysummary.setText(activities.getActsummary());
		// 返回主界面
//		back.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				CNavigate.startMainActivity(ActivityDetailActivity.this,null);
//
//			}
//		});
		// 分享活动
		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		// 关注
		care.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
//参加
		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
	CNavigate.startActivitySignInfoActivity(ActivityDetailActivity.this, activities.getActid());

			}
		});

	}

	@Override
	public void initJabActionBar() {
		// TODO Auto-generated method stub

	}

}
