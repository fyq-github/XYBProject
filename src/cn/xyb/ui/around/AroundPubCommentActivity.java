package cn.xyb.ui.around;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.utils.common.CommonUtil;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.R;
import cn.xyb.api.AroundReqUtil;
import cn.xyb.base.BaseActivity;
import cn.xyb.model.around.AroundComments;
import cn.xyb.model.around.AroundDetail;
import cn.xyb.model.main.SimpleResult;
import cn.xyb.navigate.CNavigate;
import cn.xyb.ui.activities.ActivitySignInfoActivity;

/**
 * 周边条目发表评论界面
 * 
 * @author weiyu
 * 
 */
public class AroundPubCommentActivity extends BaseActivity {

	private EditText mycomment = null;// 输入的评论
	private Button submitcomment = null;// 提交按钮
	private SimpleResult simpleResult = new SimpleResult();
	private AroundComments acs = new AroundComments();
	private int merchantsid;

	AroundDetail ad = new AroundDetail();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.around_comment_write);
		mycomment = (EditText) findViewById(R.id.mycomment);
		submitcomment = (Button) findViewById(R.id.submitcomment);

		Intent intent = this.getIntent();
		merchantsid = intent.getIntExtra("merchantsid", 0);

		submitcomment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String mycomment1 = mycomment.getText().toString();
				acs.setMerchantsid(merchantsid);
				acs.setMerchantscomment(mycomment1);
				AroundReqUtil.doAroundPub(AroundPubCommentActivity.this,
						iBindData, acs, null);

				CommonUtil.showToast(mycomment1);

			}
		});

	}

	@Override
	public void initJabActionBar() {
		// TODO Auto-generated method stub

	}

	IBindData iBindData = new IBindData() {

		@Override
		public void bindData(int tag, Object object) {
			if (tag == EAPIConsts.AroundReqType.ADDAROUNDCOMMENT
					&& object != null) {
				dismissLoadingDialog();
				simpleResult = (SimpleResult) object;
				if (simpleResult.isResult()) {
					CommonUtil.showToast(simpleResult.getContent());
					AroundPubCommentActivity.this.finish();
				} else {
					CommonUtil.showToast(simpleResult.getContent());
				}
			}

		}
	};

}
