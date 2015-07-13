package cn.xyb.ui.widgets;

import cn.utils.image.ImageUtils;
import cn.xyb.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class BottomDialog extends Dialog implements android.view.View.OnClickListener {
private Button takephoto,usephoto,cancle;
private Context context;
	public BottomDialog(Context context) {
		//super(context);
		super(context,R.style.MyDialogStyleBottom);
		this.context=context;
		setContentView(R.layout.widget_bottom_dialog);
		initview();
	}
	private void initview() {
		takephoto=(Button) findViewById(R.id.take_photo);
		usephoto=(Button) findViewById(R.id.use_photo);
		cancle=(Button) findViewById(R.id.photo_cancle);
		takephoto.setOnClickListener(this);
		usephoto.setOnClickListener(this);
		cancle.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.take_photo:
			ImageUtils.openCameraImage((Activity) context);
			dismiss();
			break;
		case R.id.use_photo:
			ImageUtils.openLocalImage((Activity) context);
			dismiss();
			break;
		case R.id.photo_cancle:
			dismiss();
			break;
		default:
			break;
		}	
	}


}
