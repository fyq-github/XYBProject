package cn.xyb.ui.main;

import com.umeng.update.UmengDialogButtonListener;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import cn.xyb.R;
import cn.xyb.base.BaseActivity;
import cn.xyb.navigate.CNavigate;
/**
 * 闪屏界面
 * @author FYQ
 *
 */
public class SplashActivity extends BaseActivity {

	@SuppressLint("NewApi")
	@Override
	public void initJabActionBar() {
		jabGetActionBar().hide(); // 隐藏ActionBar
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		//return super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.activity_splash, null);
		setContentView(view);
		//渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener()
		{

			@Override
			public void onAnimationEnd(Animation animation) {
				CNavigate.startLoginActivity(SplashActivity.this);
				SplashActivity.this.finish();//跳到登录界面   就结束掉这个
			}

			@Override
			public void onAnimationRepeat(Animation animation) {}

			@Override
			public void onAnimationStart(Animation animation) {}
			
			
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==4 ){//按下“返回”按键
			android.os.Process.killProcess(android.os.Process.myPid());//让程序完全退出应用
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onStart() {
		super.onStart();
		// 检查更新
				UmengUpdateAgent.setUpdateOnlyWifi(false);
				UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
					
					@Override
					public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
						switch (updateStatus) {
						case UpdateStatus.Yes: // 有更新
							UmengUpdateAgent.showUpdateDialog(SplashActivity.this, updateInfo);
							break;
						default: // 默认处理
//							UserReqUtil.doLoginConfiguration(SplashActivity.this, mIBindData, UserReqUtil.getDoLoginConfigurationParams("", "", EUtil.getDeviceID(SplashActivity.this),
//									EUtil.getAppVersionName(SplashActivity.this), "", "", "", "android", "", "", mMainApp.getAppData().getUserName(), mMainApp.getAppData().getPassword()), null);
							break;
						}
					}
				});
				
				// 设置监听事件
				UmengUpdateAgent.setDialogListener(new UmengDialogButtonListener() {
					@Override
					public void onClick(int status) {
						switch (status) {
						case UpdateStatus.Update:
							break;
						default:
							// finish();
							// 默认处理
//							UserReqUtil.doLoginConfiguration(SplashActivity.this, mIBindData, UserReqUtil.getDoLoginConfigurationParams("", "", EUtil.getDeviceID(SplashActivity.this),
//									EUtil.getAppVersionName(SplashActivity.this), "", "", "", "android", "", "", mMainApp.getAppData().getUserName(), mMainApp.getAppData().getPassword()), null);
							break;
						}
					}
				});
				// 请求更新
				UmengUpdateAgent.update(this);
	}

}
