package cn.xyb.ui.activities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Text;
import cn.utils.http.ACache;
import cn.utils.http.NetworkUtil;
import cn.utils.image.imageloader.ImageDownLoader;
import cn.utils.image.imageloader.ImageDownLoader.onImageLoaderListener;
import cn.xyb.R;
import cn.xyb.bean.Actlist;
import cn.xyb.model.activities.Activities;
import cn.xyb.ui.widgets.XListViewFooter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 活动列表的适配器
 * @author FYQ
 *
 */
public class ActivitiesAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private List<Activities> listActivity=new ArrayList<Activities>();
	private static int DEFAULTNUM=10;//每页条目数
	public static int index=0;//前面有几页
	private ACache mCache;//缓存框架
	/**
	 * Image 下载器
	 */
	private ImageDownLoader mImageDownLoader;
	
	public ActivitiesAdapter(Context context) {
        this.context=context;
        mImageDownLoader=new ImageDownLoader(context);
        mCache = ACache.get(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public void setData(Actlist actlist){
		index=0; //重新加载数据后，置为0
		this.listActivity=actlist.getActList();
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		if(listActivity.size()-DEFAULTNUM*index<DEFAULTNUM){
			XListViewFooter.mHintView.setText("没有更多");
			return listActivity.size();
		}else{
			return DEFAULTNUM*index+DEFAULTNUM;
		}
		//return listActivity.size();
	}

	@Override
	public Object getItem(int position) {
		return listActivity.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
TextView actname;
TextView actsummary;
ImageView actpic;
File cacheDir;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=inflater.inflate(R.layout.activities_list_item,null);
		Activities activities=listActivity.get(position);
		actname=(TextView) convertView.findViewById(R.id.act_name);
		actsummary=(TextView) convertView.findViewById(R.id.act_summary);
		actpic=(ImageView) convertView.findViewById(R.id.activity_pic);
		actname.setText(activities.getActname());
		actsummary.setText(activities.getActsummary());
		if (activities.getActimg() != null) {
			Bitmap bitmap = null;	
bitmap = mImageDownLoader.downloadImage(activities.getActimg(), new onImageLoaderListener() {
				
				@Override
				public void onImageLoader(Bitmap bitmap, String url) {
					if(actpic != null && bitmap != null){
						//回调之后才会设置,现在不会直接显示图片
						actpic.setImageBitmap(bitmap);
					}
					
				}
			});
			
			if(bitmap != null){
				actpic.setImageBitmap(bitmap);
			}else{
				actpic.setImageDrawable(context.getResources().getDrawable(R.drawable.userphoto_default));
			}
			
			
//			if (NetworkUtil.isNetworkConnected(context)) {
//				mImageLoader.displayImage(activities.getActimg(), actpic);
//			}else{//本地
//				System.out.println("本地");
//				String localurl=mImageLoader.getDiscCache().get(activities.getActimg()).getPath();
//				 String filename=String.valueOf(localurl.hashCode());
//				 System.out.println(filename);
////		            File f=new File(mImageLoader.getDiscCache(), filename);
////				 cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"Android/data/cn.xyb/cache/");
//				 cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"jt/image/Cache");
//		            System.out.println(android.os.Environment.getExternalStorageDirectory().toString());
//				 System.out.println(cacheDir+"cacheDir");
//		            File f=new File(cacheDir, filename);
//		            //f.setReadable(true);
//		           if(f.canRead()){
//		        	   System.out.println("可读");
//		           }
//		            if(f.exists()){
//		                Uri b = Uri.fromFile(f);
//		                System.out.println(f.toString()+"!!!!!!!!!!!!!!!!!");
//		            }else
//		                cacheDir=context.getCacheDir();
//		            if(!cacheDir.exists())
//		                cacheDir.mkdirs();
//		        }
			}
		return convertView;
	}

}
