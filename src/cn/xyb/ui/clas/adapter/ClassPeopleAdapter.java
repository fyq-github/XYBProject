package cn.xyb.ui.clas.adapter;

import java.util.ArrayList;
import java.util.List;

import cn.xyb.R;
import cn.xyb.bean.Actlist;
import cn.xyb.model.main.UserInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassPeopleAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private List<UserInfo> userInfolist;
	public ClassPeopleAdapter(Context context) {
		userInfolist=new ArrayList<UserInfo>();
		this.context = context;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public void setData(List<UserInfo> userInfolist){
		this.userInfolist=userInfolist;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		return userInfolist.size();
	}

	@Override
	public Object getItem(int position) {
		return userInfolist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
ImageView userphoto;
TextView userpotion;
TextView userrname;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=inflater.inflate(R.layout.classpeople_list_item, null);
		userphoto=(ImageView) convertView.findViewById(R.id.classpeople_userphoto);
		userpotion=(TextView) convertView.findViewById(R.id.classpeople_position);
		userrname=(TextView) convertView.findViewById(R.id.classpeople_rname);
		//userphoto.setImageResource(userInfolist.get(position).getPhoto());
		userpotion.setText(userInfolist.get(position).getPosition());
		userrname.setText(userInfolist.get(position).getRname());
		return convertView;
	}

}
