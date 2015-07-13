package cn.xyb.ui.clas.adapter;

import java.util.ArrayList;

import cn.xyb.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SchoolAdapter extends BaseAdapter {
	private LayoutInflater inflater;
    private ArrayList<String> list; 
public SchoolAdapter(Context context, ArrayList<String> list){
	this.inflater = LayoutInflater.from(context);
    this.list = list;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            convertView = inflater.inflate(R.layout.pup_class_school_item, null);
        }
        TextView tv = (TextView)convertView.findViewById(R.id.text);
        tv.setText(list.get(position));
         
        return convertView;
	}

}
