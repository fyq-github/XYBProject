package cn.xyb.ui.main;

import cn.xyb.R;
import cn.xyb.model.main.User;
import cn.xyb.model.main.UserInfo;
import cn.xyb.navigate.CNavigate;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/**
 * 侧滑左菜单
 * @author FYQ
 *
 */
public class MenuLeftFragment extends Fragment implements OnClickListener
{
private LinearLayout linearLayout;
public static User personuser;
public static UserInfo personuserInfo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view=inflater.inflate(R.layout.menu_layout_left, container, false);
		linearLayout=(LinearLayout) view.findViewById(R.id.left_linearlayout);
		linearLayout.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.left_linearlayout:
			CNavigate.startPersonMainActivity(getActivity(), personuser,personuserInfo);
			break;
		}
		
	}
}
