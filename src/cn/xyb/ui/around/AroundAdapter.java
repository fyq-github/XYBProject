package cn.xyb.ui.around;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.w3c.dom.Text;

import com.google.gson.Gson;

import cn.xyb.R;
import cn.xyb.bean.Actlist;
import cn.xyb.bean.AroundDetList;
import cn.xyb.model.activities.Activities;
import cn.xyb.model.around.Around;
import cn.xyb.model.around.AroundDetail;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 周边列表的适配器
 * 
 * @author weiyu
 * 
 */
public class AroundAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	// private List<Around> around=new ArrayList<Around>();//周边的种类链表
	private List<AroundDetail> aroundDeailList = new ArrayList<AroundDetail>();// 每个种类信息的链表

	public AroundAdapter(Context context) {
		this.context = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(AroundDetList aroundDetaillist1) {
		// TODO Auto-generated method stub
		this.aroundDeailList = aroundDetaillist1.getAroundDeailList();
		this.notifyDataSetChanged();// 更新列表
	}

	@Override
	public int getCount() {
		return aroundDeailList.size();
	}

	@Override
	public Object getItem(int position) {
		return aroundDeailList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// private ImageView around_list_pic;
	private TextView name;
	private TextView price;
	private TextView comment;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Gson gson = new Gson();
		// System.out.println(gson.toJson(aroundDeailList.get(position)));
		convertView = inflater.inflate(R.layout.around_main_list, null);

		// around_list_pic = (ImageView)
		// convertView.findViewById(R.id.around_list_pic);
		name = (TextView) convertView.findViewById(R.id.name);
		price = (TextView) convertView.findViewById(R.id.price);
		comment = (TextView) convertView.findViewById(R.id.comment);
		name.setText(aroundDeailList.get(position).getMerchantinfo());
		price.setText(aroundDeailList.get(position).getPrice() + "");
		if (aroundDeailList.get(position).getCommentlist() != null
				&& aroundDeailList.get(position).getCommentlist().size() > 0) {
			comment.setText(aroundDeailList.get(position).getCommentlist()
					.get(0).getMerchantscomment()
					+ "");
			
			

//			
			
		}


		return convertView;

	}

}
