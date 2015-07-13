package cn.xyb.base;

import cn.xyb.R;
import cn.xyb.ui.widgets.XProgressDialog;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.Window;

public abstract class BaseFragmentActivity extends FragmentActivity {
	private XProgressDialog mProgressDialog;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_PROGRESS);  //显示进度条
		initJActionBarImpl();//记得写上次函数  对actionbar进行设置
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//return super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	//子类在该回调中实现actionbar相关修改和设置
    public abstract void initJabActionBar();
    
    @SuppressLint("NewApi")
	private void initJActionBarImpl(){
		// 设置ActionBar样式
		android.app.ActionBar actionbar = this.getActionBar();
		actionbar.setDisplayShowTitleEnabled(true);
		actionbar.setHomeButtonEnabled(false);
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setIcon(R.color.none);
		// 设置actionbar的背景图
		Drawable myDrawable = getResources().getDrawable(
				R.drawable.auth_title_back);
		actionbar.setBackgroundDrawable(myDrawable); // 设置背景图片
		actionbar.setSplitBackgroundDrawable(getResources().getDrawable(R.drawable.auth_title_back));
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    	initJabActionBar();
    }

	@SuppressLint("NewApi")
	protected ActionBar jabGetActionBar() {
		return getActionBar();
	}

	public void showLoadingDialog() {
		showLoadingDialog("");
	}
	
	public void showLoadingDialog(String message) {
		if (null == mProgressDialog) {
			mProgressDialog = new XProgressDialog(BaseFragmentActivity.this);
			mProgressDialog
					.setOnCancelListener(new DialogInterface.OnCancelListener() {
						@Override
						public void onCancel(DialogInterface dialogInterface) {
							//KeelLog.d(TAG, "mProgressDialog.onCancel");
							// onLoadingDialogCancel();//如果取消对话框， 结束当前activity
						}
					});
		}
		mProgressDialog.setMessage(message);
		mProgressDialog.show();
	}
	
	public void dismissLoadingDialog() {
		try{
			if (mProgressDialog != null && mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
		}catch(Exception e){
			
		}
	}
}
