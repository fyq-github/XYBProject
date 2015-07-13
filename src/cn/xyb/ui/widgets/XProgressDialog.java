package cn.xyb.ui.widgets;

import cn.xyb.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Window;
import android.widget.ImageView;
/**
 * 加载数据时  动态小旋转图
 * @author FYQ
 *
 */
public class XProgressDialog extends Dialog {

	public XProgressDialog(Context context) {
		super(context, R.style.dialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.widget_loading);
		ImageView rocketImage = (ImageView) findViewById(R.id.loading);
		AnimationDrawable rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
		rocketAnimation.start();
	}
	 public void setMessage(String message) {

		}
}
