package cn.xyb.ui.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
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
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import cn.utils.http.ACache;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.utils.http.NetworkUtil;
import cn.xyb.R;
import cn.xyb.api.ActivitiyReqUtil;
import cn.xyb.base.BaseFragment;
import cn.xyb.bean.Actlist;
import cn.xyb.navigate.CNavigate;
import cn.xyb.ui.main.MainActivity;
import cn.xyb.ui.widgets.XListView;
import cn.xyb.ui.widgets.XListView.IXListViewListener;
import cn.xyb.ui.widgets.XListViewFooter;
/**
 * 活动的主界面
 * @author weiyu
 *
 */
public class ActivityFrag extends BaseFragment implements OnItemClickListener,IXListViewListener {
	private ViewPager viewPager; // android-support-v4中的滑动组件
	private List<ImageView> imageViews; // 滑动的图片集合
	private String[] titles; // 图片标题
	private int[] imageResId; // 图片ID
	private List<View> dots; // 图片标题正文的那些点
	private TextView tv_title;
	private int currentItem = 0; // 当前图片的索引号
	private ScheduledExecutorService scheduledExecutorService;
	private XListView xactList;
	private ActivitiesAdapter activitiesAdapter;
	private Actlist actlist;
	private Handler mHandler;
	private ACache mCache;//缓存框架
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.frag_activity, container, false);
		mHandler = new Handler();
		mCache = ACache.get(getActivity());
		initHuaDong(view);//初始化自动滚动图片
		
		activitiesAdapter=new ActivitiesAdapter(getActivity());
		xactList=(XListView) view.findViewById(R.id.activity_list);
		xactList.setPullLoadEnable(true);
		xactList.setOnItemClickListener(this);
		xactList.setXListViewListener(this);
		xactList.setAdapter(activitiesAdapter);
		
		if(NetworkUtil.isNetworkConnected(getActivity())){//网络有连接
			ActivitiyReqUtil.doGetActivities(getActivity(), iBindData, null);//加载数据
			showLoadingDialog();
		}else{//无连接读取本地缓存
			read();
		}
		return view;
	}  
	public void initHuaDong(View view){
		imageResId = new int[] { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e };
		titles = new String[imageResId.length];
		titles[0] = "极客课堂";
		titles[1] = "公益徒步活动";
		titles[2] = "UI设计案例教学";
		titles[3] = "青年商业活动";
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
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
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
				//System.out.println("currentItem: " + currentItem);
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
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		CNavigate.startActivityDetailActivity(getActivity(), actlist.getActList().get(arg2-1));
		
	}
	//读取本地缓存
	public void read() {
		actlist=(Actlist) mCache.getAsObject("actlist");
		if(actlist!=null){
			activitiesAdapter.setData(actlist);
		}
	}
	IBindData iBindData=new IBindData() {
		
		@Override
		public void bindData(int tag, Object object) {
			if(tag==EAPIConsts.ActivityReqType.GETACTLIST&&object!=null){
				dismissLoadingDialog();
				actlist=(Actlist) object;
				activitiesAdapter.setData(actlist);
				save(actlist);
			}
		}
	};
	//保存到本地
	public void save(Actlist actlist) {
		mCache.put("actlist", actlist);
	}
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if(NetworkUtil.isNetworkConnected(getActivity())){//网络有连接
					ActivitiyReqUtil.doGetActivities(getActivity(), iBindData, null);//加载数据
					showLoadingDialog();
				}else{//无连接读取本地缓存
					read();
				}
				//				start = ++refreshCnt;
//				items.clear();
//				geneItems();
//				// mAdapter.notifyDataSetChanged();
//				mAdapter = new ArrayAdapter<String>(XListViewActivity.this, R.layout.list_item, items);
//				mListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}
	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				System.out.println("数值"+activitiesAdapter.getCount());
				if(activitiesAdapter.getCount()<actlist.getActList().size()){
					ActivitiesAdapter.index++;
				}
				
//				geneItems();
//				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
	private void onLoad() {
		xactList.stopRefresh();
		xactList.stopLoadMore();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");// 设置日期格式
		Date date=new Date();//接收到时的时间
		xactList.setRefreshTime(df.format(date));
	}
}
