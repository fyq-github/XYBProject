package cn.xyb.base;


import cn.xyb.R;
import cn.xyb.ui.widgets.BottomDialog;
import cn.xyb.ui.widgets.XProgressDialog;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends Activity {
	protected XProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initActionBarImpl();
		
	}
	//点击返回键  就结束自己
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
	
	@SuppressLint("NewApi")
	protected ActionBar jabGetActionBar(){
    	return getActionBar();
    }

	@SuppressLint("NewApi")
	private void initActionBarImpl(){
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
	//子类在该回调中实现actionbar相关修改和设置
    public abstract void initJabActionBar();
    protected void showLoadingDialog() {
        showLoadingDialog("");
    }

    protected void showLoadingDialog(int resId) {
        showLoadingDialog(getString(resId));
    }
    
    protected void showLoadingDialog(String message) {
        if (null==mProgressDialog) {
            mProgressDialog=new XProgressDialog(BaseActivity.this);
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    onLoadingDialogCancel();
                }
            });
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }
    /**
     * 取消loading对话框动作回调，如果想要处理，就继承该函数，默认关闭当前页面
     */
    public void onLoadingDialogCancel(){
    	finish();
    }
    protected void dismissLoadingDialog() {
    	try{
	        if (mProgressDialog!=null&&mProgressDialog.isShowing()) {
	            mProgressDialog.dismiss();
	        }
    	}catch(Exception e){
    		
    	}
    }
}
