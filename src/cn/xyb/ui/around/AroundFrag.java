package cn.xyb.ui.around;

import android.content.Intent;
import android.os.Bundle;
import cn.xyb.bean.AroundDetList;
import cn.xyb.bean.AroundList;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.xyb.R;
import cn.xyb.api.ActivitiyReqUtil;
import cn.xyb.api.AroundReqUtil;
import cn.xyb.base.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Settings.System;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import cn.xyb.R;
import cn.xyb.base.BaseFragment;
import cn.xyb.bean.Actlist;
import cn.xyb.model.activities.Activities;
import cn.xyb.model.around.Around;
import cn.xyb.model.around.AroundDetail;
import cn.xyb.navigate.CNavigate;
import cn.xyb.ui.activities.ActivitiesAdapter;
import cn.xyb.ui.main.LoginActivity;
import cn.xyb.ui.main.MainActivity;

/**
 * 周边主界面
 * 
 * @author FYQ
 * 
 */
public class AroundFrag extends BaseFragment implements OnItemClickListener {

	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合

	private String[] titles; // 图片标题
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点

	private TextView tv_title;
	private int currentItem = 0; // 当前图片的索引号
	private ScheduledExecutorService scheduledExecutorService;

	private ListView aroundlist;
	AroundDetList aroundList;

	AroundAdapter aroundAdapter = null;
	TextView foodtextview = null;
	TextView movietextview = null;
	TextView sighttextview = null;
	TextView takeouttextview = null;

	// private SimpleAdapter arr_adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.around_main, container, false);
		initHuaDong(view);// 初始化自动滚动图片
		iniaroundlist(view);// 美食，电影，景点，外卖的总标题点击显示
		AroundReqUtil.doGetAroundDetailList(getActivity(), iBindData, 1, null);// 加载默认数据
		showLoadingDialog();

		initHuaDong(view);// 初始化自动滚动图片

		aroundAdapter = new AroundAdapter(getActivity());

		aroundlist = (ListView) view.findViewById(R.id.aroundlist);

		aroundlist.setAdapter(aroundAdapter);

		aroundlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				CNavigate.startAroundItemDetailActivity(getActivity(),
						aroundList.getAroundDeailList().get(arg2));

				//

			}
		});

		return view;

	}

	/*
	 * 四个标题的，监听
	 */
	public void iniaroundlist(View view) {
		foodtextview = (TextView) view.findViewById(R.id.foodtextview);
		movietextview = (TextView) view.findViewById(R.id.movietextview);
		sighttextview = (TextView) view.findViewById(R.id.sighttextview);
		takeouttextview = (TextView) view.findViewById(R.id.takeouttextview);

		foodtextview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AroundReqUtil.doGetAroundDetailList(getActivity(), iBindData,
						1, null);// 加载美食数据

			}
		});
		movietextview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AroundReqUtil.doGetAroundDetailList(getActivity(), iBindData,
						2, null);// 加载电影数据

			}
		});
		sighttextview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AroundReqUtil.doGetAroundDetailList(getActivity(), iBindData,
						3, null);// 加载风景数据

			}
		});
		takeouttextview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AroundReqUtil.doGetAroundDetailList(getActivity(), iBindData,
						4, null);// 加载外卖数据

			}
		});

	}

	/**
	 * 下面的代码都是上面的图片滚动
	 */

	public void initHuaDong(View view) {
		imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e };
		titles = new String[imageResId.length];
		titles[0] = "巩俐不低俗，我就不能低俗";
		titles[1] = "扑树又回来啦！再唱经典老歌引万人大合唱";
		titles[2] = "揭秘北京电影如何升级";
		titles[3] = "乐视网TV版大派送";
		titles[4] = "热血屌丝的反杀";

		imageViews = new ArrayList<ImageView>();

		// 初始化图片资源
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setImageResource(imageResId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}

		dots = new ArrayList<View>();
		dots.add(view.findViewById(R.id.v_dot0));
		dots.add(view.findViewById(R.id.v_dot1));
		dots.add(view.findViewById(R.id.v_dot2));
		dots.add(view.findViewById(R.id.v_dot3));
		dots.add(view.findViewById(R.id.v_dot4));

		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_title.setText(titles[0]);//

		viewPager = (ViewPager) view.findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
	}

	@Override
	public void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {

				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	/**
	 * 填充ViewPager页面的适配器
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imageResId.length;
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

		@Override
		public void finishUpdate(View arg0) {

		}
	}

	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			tv_title.setText(titles[position]);
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	IBindData iBindData = new IBindData() {

		@Override
		public void bindData(int tag, Object object) {
			if (tag == EAPIConsts.AroundReqType.AROUNDDETAILLIST
					&& object != null) {
				dismissLoadingDialog();
				aroundList = (AroundDetList) object;
				// Gson gson = new Gson();
				// System.out.println(gson.toJson(aroundList));
				aroundAdapter.setData(aroundList);
			}
		}
	};

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}
