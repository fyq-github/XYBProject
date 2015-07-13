package cn.xyb.ui.clas;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.R;
import cn.xyb.api.ClassReqUtil;
import cn.xyb.base.BaseFragment;
import cn.xyb.bean.ClassDetail;
import cn.xyb.model.main.User;
import cn.xyb.model.main.UserInfo;
import cn.xyb.navigate.CNavigate;
import cn.xyb.ui.clas.adapter.ClassPeopleAdapter;
import cn.xyb.ui.clas.adapter.SchoolAdapter;
import cn.xyb.ui.main.MainActivity;
/**
 * 班级主界面
 * @author FYQ
 *
 */
public class ClassFrag extends BaseFragment implements OnItemClickListener,IBindData, OnClickListener {
private Button school,department,subject,clas;
private TextView classsummary,classimages,classtimes;
private int btnWidth;
//PopupWindow对象声明
private PopupWindow pw;
private ArrayList<String> schoolList,departmentList,subjectList,clasList;
private int clickPsition = -1;
private ListView classpeopleslv;
private ClassPeopleAdapter classPeopleAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.frg_class, container, false);
		initview(view);
		classPeopleAdapter=new ClassPeopleAdapter(getActivity());
		
		classpeopleslv=(ListView) view.findViewById(R.id.classpeople_list);
		classpeopleslv.setOnItemClickListener(this);
		classpeopleslv.setAdapter(classPeopleAdapter);
		
		return view;
	}
	//被MainActivity调用
	public void freshData(User user){//默认获取用户所在班级的信息
		if(school!=null&&department!=null&&subject!=null&&clas!=null){
			school.setText(user.getSchool());
			department.setText(user.getDepartment());
			subject.setText(user.getSubject());
			clas.setText(user.getClas());
			ClassReqUtil.doGetClassDetail(getActivity(), this, user.getClasid(), null);
		}
		
	}
	 public ArrayList<String> getList() {
	        ArrayList<String> list = new ArrayList<String>();
	        list.add("学校");
	        list.add("灵感笔记");
	        list.add("爆笑王文");
	        list.add("内涵段子");
	        list.add("每日吐槽");
	        list.add("灵感笔记");
	        list.add("爆笑王文");
	        list.add("内涵段子");
	        list.add("每日吐槽");
	        list.add("灵感笔记");
	        list.add("爆笑王文");
	        list.add("内涵段子");
	 
	        return list;
	 
	    }
public void initview(View view){
	school=(Button) view.findViewById(R.id.frg_class_school);
	department=(Button) view.findViewById(R.id.frg_class_department);
	subject=(Button) view.findViewById(R.id.frg_class_subject);
	clas=(Button) view.findViewById(R.id.frg_class_class);
	
	classsummary=(TextView) view.findViewById(R.id.class_summary);
	classimages=(TextView) view.findViewById(R.id.class_images);
	classtimes=(TextView) view.findViewById(R.id.class_times);
	classsummary.setOnClickListener(this);
	classimages.setOnClickListener(this);
	classtimes.setOnClickListener(this);
	
	btnWidth=getActivity().getWindowManager().getDefaultDisplay().getWidth() / 4;
	LinearLayout.LayoutParams lParams=(LayoutParams) school.getLayoutParams();
	lParams.width=btnWidth;
	school.setLayoutParams(lParams);
	department.setLayoutParams(lParams);
	subject.setLayoutParams(lParams);
	clas.setLayoutParams(lParams);
	//下面定义pupwindow
	schoolList = getList();
	//school.setText(schoolList.get(0));
}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
		switch (arg1.getId()) {
		case R.id.lv_pop:
			school.setText(schoolList.get(position));
			if (clickPsition != position) {
				clickPsition = position;
			}
			pw.dismiss();
			break;
		case R.id.classpeople_list:
			CNavigate.startPersonIntroducActivity(getActivity(), userInfolist.get(position));
		}
	}
	ClassDetail classDetail;
	List<UserInfo> userInfolist;
	@Override
	public void bindData(int tag, Object object) {
		if (tag == EAPIConsts.ClassReqType.GETCLASSDETAIL && object != null) {
			classDetail = (ClassDetail) object;//得到了数据
			userInfolist = classDetail.getUserInfolist();
			if (userInfolist != null) {
				classPeopleAdapter.setData(userInfolist);
			}
		}
	}
@Override
public void onClick(View arg0) {
	switch (arg0.getId()){
	case R.id.frg_class_school:
		//通过布局注入器，注入布局给View对象
        View myView = getActivity().getLayoutInflater().inflate(R.layout.pup_class_frag, null);
        //通过view 和宽·高，构造PopopWindow
		pw = new PopupWindow(myView, getActivity().getWindowManager().getDefaultDisplay().getWidth(),  getActivity().getWindowManager()
				.getDefaultDisplay().getHeight()-500,true);
		 //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
        pw.setBackgroundDrawable(getResources().getDrawable( R.drawable.bottom_bar));
        pw.setFocusable(true);//设置焦点为可点击
        pw.showAsDropDown(school);//将window视图显示在myButton下面
        ListView lv = (ListView) myView.findViewById(R.id.lv_pop);
        lv.setAdapter(new SchoolAdapter(getActivity(), schoolList));
        lv.setOnItemClickListener(this);
		break;
	case R.id.class_summary:
		CNavigate.startClassIntroducActivity(getActivity(), classDetail.getClassintroduct());
		break;
	case R.id.class_images:
		CNavigate.startClassPictruesActivity(getActivity(), classDetail);
		break;
	case R.id.class_times:
		CNavigate.startClassTimeActivity(getActivity(), 1);
		break;
	}
}
}
