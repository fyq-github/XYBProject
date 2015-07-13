package cn.xyb.ui.main;

import java.util.ArrayList;
import java.util.List;
import com.nineoldandroids.view.ViewHelper;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.utils.common.CommonUtil;
import cn.utils.http.ACache;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.utils.http.NetworkUtil;
import cn.utils.image.imageloader.ImageDownLoader;
import cn.utils.image.imageloader.ImageDownLoader.onImageLoaderListener;
import cn.xyb.R;
import cn.xyb.api.UserReqUtil;
import cn.xyb.base.BaseFragmentActivity;
import cn.xyb.bean.Actlist;
import cn.xyb.model.main.User;
import cn.xyb.model.main.UserInfo;
import cn.xyb.navigate.CNavigate;
import cn.xyb.ui.activities.ActivityFrag;
import cn.xyb.ui.around.AroundFrag;
import cn.xyb.ui.clas.ClassFrag;
import cn.xyb.ui.fun.FunFrag;
import cn.xyb.ui.info.InfoFrag;
import cn.xyb.ui.widgets.CircularImage;

/**
 * APP的主界面  含有5个fragment
 * @author FYQ
 *
 */
public class MainActivity extends BaseFragmentActivity implements IBindData, OnClickListener{
	private ImageView mTabactivities,mTabclass,mTabinfo,mTabfun,mTabaround;
	private TextView tTabactivities,tTabclass,tTabinfo,tTabfun,tTabaround;
	private ImageView mainSearch;
	private CircularImage mainUserPhoto,leftUserPhoto;//主界面头像和侧滑头像
	public static TextView mainTitle;
	private ViewPager viewPager;
	private ActivityFrag activityFrag=new ActivityFrag();//活动
	private ClassFrag classFrag=new ClassFrag();//班级
	private InfoFrag infoFrag=new InfoFrag(); //资讯
	private FunFrag funFrag=new FunFrag();//趣吧
	private AroundFrag aroundFrag=new AroundFrag();//周边
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	private long exitTime = 0;  //退出键记录时间
	public static DrawerLayout mDrawerLayout;
	private int currIndex = 0;// 上个页卡编号
	private int userid;
	private User user=new User();
	private UserInfo userInfo=new UserInfo();
	private TextView username;//侧滑菜单中的用户名
	/**
	 * Image 下载器
	 */
	private ImageDownLoader mImageDownLoader;
	private ACache mCache;//缓存框架
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		Intent intent=this.getIntent();
		userid=intent.getIntExtra("userid", 0);//得到用户id    userid
		mImageDownLoader = new ImageDownLoader(MainActivity.this);
		mCache = ACache.get(MainActivity.this);
		initView();
		initEvents();
		InitImageView();
		InitViewPager();
		username=(TextView) findViewById(R.id.user_username);
		if(NetworkUtil.isNetworkConnected(MainActivity.this)){
			UserReqUtil.doGetUser(MainActivity.this, this, userid, null);
			UserReqUtil.doGetUserinfo(MainActivity.this, this, userid, null);
			showLoadingDialog();
		}else{
			read();
		}
		
	}
	//读取本地缓存  user userinfo
		public void read() {
			user=(User) mCache.getAsObject("user");
			userInfo=(UserInfo) mCache.getAsObject("userInfo");
			if(user!=null){
				bindData(EAPIConsts.UserReqType.GETUSER,user);
			}
		if (userInfo!= null) {
			bindData(EAPIConsts.UserReqType.GETUSERINFO,userInfo);
		}
			
		}
	//初始化  ViewPager
			private void InitViewPager() {
				viewPager=(ViewPager) findViewById(R.id.mainpager);
				viewPager.setOffscreenPageLimit(5);
				fragmentList.add(activityFrag);
				fragmentList.add(classFrag);
				fragmentList.add(infoFrag);
				fragmentList.add(funFrag);
				fragmentList.add(aroundFrag);
				viewPager.setOnTouchListener(new OnTouchListener() {//禁止viewpaper滑动
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						return true;
					}
				});
				viewPager.setAdapter(new MyFrgPagerAdapter(getSupportFragmentManager(),fragmentList));
				viewPager.setCurrentItem(0);
				//viewPager.setOnPageChangeListener(new MyHyOnPageChangeListener());
			}
			//viewPager 适配器
			public class MyFrgPagerAdapter extends FragmentPagerAdapter{
				private List<Fragment> fragmentList = new ArrayList<Fragment>();
				public MyFrgPagerAdapter(FragmentManager fm) {
					super(fm);
				}
				public MyFrgPagerAdapter(FragmentManager fm, List<Fragment> fragment){
					super(fm);
					fragmentList=fragment;
				}
				@Override
				public Fragment getItem(int arg0) {
					return fragmentList.get(arg0);
				}

				@Override
				public int getCount() {
					return fragmentList.size();
				}

				@Override
				public int getItemPosition(Object object) {
					return super.getItemPosition(object);
				}
			}
	//初始化  Imageview
			private void InitImageView(){
				mainUserPhoto=(CircularImage) findViewById(R.id.main_userphoto);
				leftUserPhoto=(CircularImage) findViewById(R.id.user_photo);
				mainSearch=(ImageView) findViewById(R.id.main_search);
				mainTitle=(TextView) findViewById(R.id.main_title);
				mainUserPhoto.setOnClickListener(this);
				mainSearch.setOnClickListener(this);
				
				mTabactivities=(ImageView) findViewById(R.id.tab_img_activities);
				mTabactivities.setOnClickListener(new MyOnClickListener(0));
				mTabclass=(ImageView) findViewById(R.id.tab_img_class);
				mTabclass.setOnClickListener(new MyOnClickListener(1));
				mTabinfo=(ImageView) findViewById(R.id.tab_img_info);
				mTabinfo.setOnClickListener(new MyOnClickListener(2));
				mTabfun=(ImageView) findViewById(R.id.tab_img_fun);
				mTabfun.setOnClickListener(new MyOnClickListener(3));
				mTabaround=(ImageView) findViewById(R.id.tab_img_around);
				mTabaround.setOnClickListener(new MyOnClickListener(4));
				tTabactivities=(TextView) findViewById(R.id.tab_txt_activities);
				tTabactivities.setOnClickListener(new MyOnClickListener(0));
				tTabclass=(TextView) findViewById(R.id.tab_txt_class);
				tTabclass.setOnClickListener(new MyOnClickListener(1));
				tTabinfo=(TextView) findViewById(R.id.tab_txt_info);
				tTabinfo.setOnClickListener(new MyOnClickListener(2));
				tTabfun=(TextView) findViewById(R.id.tab_txt_fun);
				tTabfun.setOnClickListener(new MyOnClickListener(3));
				tTabaround=(TextView) findViewById(R.id.tab_txt_around);
				tTabaround.setOnClickListener(new MyOnClickListener(4));
			}
			//图片的监听事件
			public class MyOnClickListener implements View.OnClickListener {
				private int index = 0;
               
				public MyOnClickListener(int i) {
					index = i;
				}
				@Override
				public void onClick(View v) {
					viewPager.setCurrentItem(index);
					switch(index){
					case 0:
						//System.out.println(0);
						MainActivity.mainTitle.setText("活动");	
						mTabactivities.setImageDrawable(getResources().getDrawable(R.drawable.tab_activities_pressed));
						tTabactivities.setTextColor(getResources().getColor(R.color.txtselect));
						if (currIndex == 1) {
							mTabclass.setImageDrawable(getResources().getDrawable(R.drawable.tab_class_normal));
						    tTabclass.setTextColor(getResources().getColor(R.color.txtnormal));
						}else if(currIndex == 2){
							mTabinfo.setImageDrawable(getResources().getDrawable(R.drawable.tab_info_normal));
						    tTabinfo.setTextColor(getResources().getColor(R.color.txtnormal));
						}else if(currIndex == 3){
							mTabfun.setImageDrawable(getResources().getDrawable(R.drawable.tab_fun_normal));
						    tTabfun.setTextColor(getResources().getColor(R.color.txtnormal));
						}else if(currIndex == 4){
							mTabaround.setImageDrawable(getResources().getDrawable(R.drawable.tab_around_normal));
						    tTabaround.setTextColor(getResources().getColor(R.color.txtnormal));
						}
						currIndex = 0;
						break;
					case 1:
						//System.out.println(1);
						MainActivity.mainTitle.setText("班级");
						mTabclass.setImageDrawable(getResources().getDrawable(R.drawable.tab_class_pressed));
						tTabclass.setTextColor(getResources().getColor(R.color.txtselect));
						if (currIndex == 0) {
							mTabactivities.setImageDrawable(getResources().getDrawable(R.drawable.tab_activities_normal));
							tTabactivities.setTextColor(getResources().getColor(R.color.txtnormal));
						}else if(currIndex == 2){
							mTabinfo.setImageDrawable(getResources().getDrawable(R.drawable.tab_info_normal));
							tTabinfo.setTextColor(getResources().getColor(R.color.txtnormal));
						}else if(currIndex == 3){
							mTabfun.setImageDrawable(getResources().getDrawable(R.drawable.tab_fun_normal));
							tTabfun.setTextColor(getResources().getColor(R.color.txtnormal));
						}else if(currIndex == 4){
							mTabaround.setImageDrawable(getResources().getDrawable(R.drawable.tab_around_normal));
							tTabaround.setTextColor(getResources().getColor(R.color.txtnormal));
						}
						currIndex = 1;
						break;
					case 2:
						//System.out.println(2);
						MainActivity.mainTitle.setText("资讯");
						mTabinfo.setImageDrawable(getResources().getDrawable(R.drawable.tab_info_pressed));
						tTabinfo.setTextColor(getResources().getColor(R.color.txtselect));
						if (currIndex == 0) {
							mTabactivities.setImageDrawable(getResources().getDrawable(R.drawable.tab_activities_normal));
							tTabactivities.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 1) {
							mTabclass.setImageDrawable(getResources().getDrawable(R.drawable.tab_class_normal));
							tTabclass.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 3) {
							mTabfun.setImageDrawable(getResources().getDrawable(R.drawable.tab_fun_normal));
							tTabfun.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 4) {
							mTabaround.setImageDrawable(getResources().getDrawable(R.drawable.tab_around_normal));
							tTabaround.setTextColor(getResources().getColor(R.color.txtnormal));
						}
						currIndex = 2;
						break;
					case 3:
						//System.out.println(3);
						MainActivity.mainTitle.setText("趣吧");
						mTabfun.setImageDrawable(getResources().getDrawable(R.drawable.tab_fun_pressed));
						tTabfun.setTextColor(getResources().getColor(R.color.txtselect));
						if (currIndex == 0) {
							mTabactivities.setImageDrawable(getResources().getDrawable(R.drawable.tab_activities_normal));
							tTabactivities.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 1) {
							mTabclass.setImageDrawable(getResources().getDrawable(R.drawable.tab_class_normal));
							tTabclass.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 2) {
							mTabinfo.setImageDrawable(getResources().getDrawable(R.drawable.tab_info_normal));
							tTabinfo.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 4) {
							mTabaround.setImageDrawable(getResources().getDrawable(R.drawable.tab_around_normal));
							tTabaround.setTextColor(getResources().getColor(R.color.txtnormal));
						}
						currIndex = 3;
						break;
						
					case 4:
						//System.out.println(4);
						MainActivity.mainTitle.setText("周边");
						mTabaround.setImageDrawable(getResources().getDrawable(R.drawable.tab_around_pressed));
						tTabaround.setTextColor(getResources().getColor(R.color.txtselect));
						if (currIndex == 0) {
							mTabactivities.setImageDrawable(getResources().getDrawable(R.drawable.tab_activities_normal));
							tTabactivities.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 1) {
							mTabclass.setImageDrawable(getResources().getDrawable(R.drawable.tab_class_normal));
							tTabclass.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 2) {
							mTabinfo.setImageDrawable(getResources().getDrawable(R.drawable.tab_info_normal));
							tTabinfo.setTextColor(getResources().getColor(R.color.txtnormal));
						} else if (currIndex == 3) {
							mTabfun.setImageDrawable(getResources().getDrawable(R.drawable.tab_fun_normal));
							tTabfun.setTextColor(getResources().getColor(R.color.txtnormal));
						}
						currIndex = 4;
						break;
					default:
							break;
					}
				}
			};
	private void initEvents()
	{
		mDrawerLayout.setDrawerListener(new DrawerListener()
		{
			@Override
			public void onDrawerStateChanged(int newState)
			{
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset)
			{
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals("LEFT"))
				{

					float leftScale = 1 - 0.3f * scale;

					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
					ViewHelper.setTranslationX(mContent,
							mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} else
				{
					ViewHelper.setTranslationX(mContent,
							-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			@Override
			public void onDrawerOpened(View drawerView)
			{
			}

			@Override
			public void onDrawerClosed(View drawerView)
			{
				mDrawerLayout.setDrawerLockMode(
						DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
			}
		});
	}

	private void initView()
	{
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
				Gravity.RIGHT);
	}
	
	@Override
	public void bindData(int tag, Object object) {
		
		if(object!=null){
			dismissLoadingDialog();//不显示
			if(tag==EAPIConsts.UserReqType.GETUSER){
				user=(User) object;
				//通知班级模块去加载用户所在班级的信息
				classFrag.freshData(user);
				saveUser(user);
				MenuLeftFragment.personuser=this.user;
				//System.out.println(gson.toJson(user));
			}
			if(tag==EAPIConsts.UserReqType.GETUSERINFO){
				userInfo=(UserInfo) object;
				MenuLeftFragment.personuserInfo=this.userInfo;
				saveUserInfo(userInfo);
				//System.out.println(gson.toJson(userInfo));
				if(userInfo.getPhoto()!=null&&userInfo.getPhoto().length()>0){
//					ImageLoader imageLoader=ImageLoader.getInstance();
//					imageLoader.displayImage(userInfo.getPhoto(), mainUserPhoto);
//					imageLoader.displayImage(userInfo.getPhoto(),leftUserPhoto);
					Bitmap bitmap1 = null;	
					bitmap1 = mImageDownLoader.downloadImage(userInfo.getPhoto(), new onImageLoaderListener() {
									
									@Override
									public void onImageLoader(Bitmap bitmap, String url) {
										if(mainUserPhoto != null && bitmap != null){
											//回调之后才会设置,现在不会直接显示图片
											mainUserPhoto.setImageBitmap(bitmap);
										}
									}
								});
					if(bitmap1 != null){
						mainUserPhoto.setImageBitmap(bitmap1);
					}
					Bitmap bitmap2 = null;	
					bitmap2 = mImageDownLoader.downloadImage(userInfo.getPhoto(), new onImageLoaderListener() {
									@Override
									public void onImageLoader(Bitmap bitmap, String url) {
										if(leftUserPhoto != null && bitmap != null){
											//回调之后才会设置,现在不会直接显示图片
											leftUserPhoto.setImageBitmap(bitmap);
										}
									}
								});
					if(bitmap2 != null){
						leftUserPhoto.setImageBitmap(bitmap2);
					}
				}
				
				if(userInfo.getVname()==null){
					username.setText(user.getPhonenum());
				}else{
					username.setText(userInfo.getVname());
				}
			}
			
		}
	}

	// 保存到本地
	public void saveUser(User user) {
		mCache.put("user", user);
	}

	// 保存到本地
	public void saveUserInfo(UserInfo userInfo) {
		mCache.put("userInfo", userInfo);
	}
	
	@SuppressLint("NewApi")
	@Override
	public void initJabActionBar() {
		jabGetActionBar().hide();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
	}
	 public void exit() {
	        if ((System.currentTimeMillis() - exitTime) > 2000) {
	            CommonUtil.showToast("再按一次退出程序");
	            exitTime = System.currentTimeMillis();
	        } else {
	            this.finish();
	            System.exit(0);
	        }
	    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_userphoto:
			MainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
			MainActivity.mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
			Gravity.LEFT);
			break;
		case R.id.main_search:
			CNavigate.startSearchActivity(MainActivity.this);
			break;
		default:
			break;
		}
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		userid=CommonUtil.getUserId();
		UserReqUtil.doGetUser(MainActivity.this, this, userid, null);
		UserReqUtil.doGetUserinfo(MainActivity.this, this, userid, null);
	}

}
