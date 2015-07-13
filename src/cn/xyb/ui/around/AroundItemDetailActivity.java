package cn.xyb.ui.around;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.xyb.R;
import cn.xyb.base.BaseActivity;
import cn.xyb.model.activities.Activities;
import cn.xyb.model.around.AroundDetail;
import cn.xyb.navigate.CNavigate;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 周边条目的详情页
 * 
 * @author FYQ
 * 
 */
public class AroundItemDetailActivity extends BaseActivity {
	private TextView priceinformation = null;// 价格
	private TextView Relatedinformation = null;// 相关信息
	private TextView recommendinformation = null;// 推荐
	private TextView promptinformation = null;// 温馨提示
	TextView aroundcommentlist;// 评论列表
	Button commentbutton = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.around_detail_information);

		priceinformation = (TextView) findViewById(R.id.priceinformation);
		Relatedinformation = (TextView) findViewById(R.id.Relatedinformation);
		recommendinformation = (TextView) findViewById(R.id.recommendinformation);
		promptinformation = (TextView) findViewById(R.id.promptinformation);
		commentbutton = (Button) findViewById(R.id.commentbutton);

		// 获得上一个界面的item的id

		Intent intent = this.getIntent();
		Bundle aroundBundle = intent.getBundleExtra("araroundDetbundleoundid");
		final AroundDetail aroundDetail = (AroundDetail) aroundBundle
				.getSerializable("aroundDetail");

		Intent intent1 = new Intent();
		// intent1.putExtra(merchantsid, 0)

		priceinformation.setText(aroundDetail.getPrice() + "");
		Relatedinformation.setText(aroundDetail.getMerchantinfo() + "");
		recommendinformation.setText(aroundDetail.getRecommend() + "");
		promptinformation.setText(aroundDetail.getPrompt() + "");

		commentbutton.setOnClickListener(new OnClickListener() {
			Intent intent1 = new Intent();

			@Override
			public void onClick(View arg0) {
				intent1.setClass(AroundItemDetailActivity.this,
						AroundPubCommentActivity.class);

				// 把点击的产品id传过去
//
		int merchantsid = aroundDetail.getMerchantsid();
				intent1.putExtra("merchantsid", merchantsid);
				
				
				AroundItemDetailActivity.this.startActivity(intent1);


			}
		});

		// 评论列表
		String[] string = new String[aroundDetail.getCommentlist().size()];
		ListView aroundcommentlist = (ListView) findViewById(R.id.aroundcommentlist);
		aroundcommentlist.setAdapter(new ArrayAdapter<String>(
				AroundItemDetailActivity.this,
				android.R.layout.simple_list_item_1, string));

		if (aroundDetail.getCommentlist() != null
				&& aroundDetail.getCommentlist().size() > 0) {

			for (int i = 0; i < aroundDetail.getCommentlist().size(); i++) {

				int y = i + 1;

				string[aroundDetail.getCommentlist().size() - 1 - i] = "\n第"// 把评论列表倒序
						+ y
						+ "条评论:\n"
						+ aroundDetail.getCommentlist().get(i)
								.getMerchantscomment() + "";
				
				

			}

		}
		

	}

	//

	@SuppressLint("NewApi")
	@Override
	public void initJabActionBar() {
		// TODO Auto-generated method stub
		jabGetActionBar().hide();

	}

}