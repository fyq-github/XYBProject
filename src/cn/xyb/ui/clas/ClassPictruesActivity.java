package cn.xyb.ui.clas;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import cn.xyb.base.BaseActivity;
import cn.xyb.bean.ClassDetail;
import cn.xyb.model.clas.ClassImage;
/**
 * 班级相册界面
 * @author FYQ
 *
 */
public class ClassPictruesActivity extends BaseActivity {
private Bundle imglistbundle=new Bundle();
private List<ClassImage> classImagelist;
	@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	Intent intent=getIntent();
	imglistbundle=intent.getBundleExtra("imglistbundle");
	ClassDetail classDetail=(ClassDetail) imglistbundle.getSerializable("classDetail");
	classImagelist=classDetail.getClassImagelist();//获取到相册列表
}

	@Override
	public void initJabActionBar() {
		
		
	}

}
