package cn.xyb.ui.main;

import cn.xyb.R;
import cn.xyb.base.BaseActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
/**
 * 程序向导界面
 * @author FYQ
 *
 */
public class LoadingGuideActivity extends BaseActivity {
	private int[] pagers = { R.drawable.first_splash_pager, R.drawable.second_splash_pager, R.drawable.third_splash_pager };
	private ViewPager pager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences sharedPreferences = getSharedPreferences("share_itemFirstLogin", MODE_PRIVATE);
		boolean flag = sharedPreferences.getBoolean("first_entry", true);
		if (!flag) {
			Intent intent = new Intent(LoadingGuideActivity.this, SplashActivity.class);
			startActivity(intent);
			finish();
		}
		else {
			setContentView(R.layout.activity_loading_guide);
			pager = (ViewPager) findViewById(R.id.splashPager);
			pager.setAdapter(new SplashPagerAdapter());
			Editor editor = sharedPreferences.edit();
			editor.putBoolean("first_entry", false);
			editor.commit();
		}
	}

	private class SplashPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return pagers.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(LoadingGuideActivity.this, R.layout.activity_splash_viewpagers, null);
			ImageView img = (ImageView) view.findViewById(R.id.splash_viewpager);
			img.setBackgroundResource(pagers[position]);
			Button button = (Button) view.findViewById(R.id.splash_start_app);
			if (position == 2) {
				button.setVisibility(View.VISIBLE);
				button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(LoadingGuideActivity.this, SplashActivity.class);
						startActivity(intent);
						finish();
					}
				});
			}
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

	@SuppressLint("NewApi")
	@Override
	public void initJabActionBar() {
		jabGetActionBar().hide(); // 隐藏ActionBar
	}
}
