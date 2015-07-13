package cn.xyb.base;

import cn.xyb.ui.widgets.XProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
private XProgressDialog mProgressDialog;
	
	public void refresh() {

	}
	
	
	public void showLoadingDialog() {
		showLoadingDialog("");
	}
	
	public void showLoadingDialog(String message) {
		if (null == mProgressDialog) {
			mProgressDialog = new XProgressDialog(getActivity());
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
