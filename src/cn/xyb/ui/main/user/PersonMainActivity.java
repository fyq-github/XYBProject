package cn.xyb.ui.main.user;

import java.util.HashMap;
import java.util.Map;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.utils.common.CommonUtil;
import cn.utils.http.EAPIConsts;
import cn.utils.http.IBindData;
import cn.utils.image.ImageUtils;
import cn.utils.image.UploadUtil;
import cn.utils.image.UploadUtil.OnUploadProcessListener;
import cn.utils.image.imageloader.ImageDownLoader;
import cn.utils.image.imageloader.ImageDownLoader.onImageLoaderListener;
import cn.xyb.R;
import cn.xyb.api.UserReqUtil;
import cn.xyb.base.BaseActivity;
import cn.xyb.model.main.SimpleResult;
import cn.xyb.model.main.User;
import cn.xyb.model.main.UserInfo;
import cn.xyb.ui.main.MainActivity;
import cn.xyb.ui.widgets.BottomDialog;
/**
 * 用户主界面
 * @author FYQ
 *
 */
public class PersonMainActivity extends BaseActivity implements OnClickListener,OnUploadProcessListener {
private Bundle bundle=new Bundle();
private User user;
private UserInfo userInfo;
private ImageButton back;
private ImageView userphoto;
private TextView username;
private  BottomDialog bottomDialog;
private SimpleResult simpleResult;


private ProgressDialog progressDialog;
private String picPath = null;
protected static final int TO_UPLOAD_FILE = 1; //去上传文件
private static String requestURL=EAPIConsts.UPLOADPIC_URL;//上传图片服务器地址
protected static final int UPLOAD_FILE_DONE = 2;  //上传完成
/**
 * Image 下载器
 */
private ImageDownLoader mImageDownLoader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		bundle=intent.getBundleExtra("bundle");
		user=(User) bundle.getSerializable("user");
		userInfo=(UserInfo) bundle.getSerializable("userInfo");
		setContentView(R.layout.person_main_activity);
		
		mImageDownLoader = new ImageDownLoader(PersonMainActivity.this);
		initview();
	}
	
	public void initview(){
		progressDialog = new ProgressDialog(this); 
		
		back=(ImageButton) findViewById(R.id.person_main_back);
		back.setOnClickListener(this);
		userphoto=(ImageView) findViewById(R.id.person_main_userphoto);
		userphoto.setOnClickListener(this);
		username=(TextView) findViewById(R.id.person_main_username);
		if(userInfo!=null){
			if(userInfo.getVname()==null||userInfo.getVname().length()==0){
				username.setText(user.getPhonenum());
			}else{
				username.setText(userInfo.getVname());
			}
			if(userInfo.getPhoto()!=null&&userInfo.getPhoto().length()>0){
//				ImageLoader imageLoader=ImageLoader.getInstance();
//				imageLoader.displayImage(userInfo.getPhoto(), userphoto);
				Bitmap bitmap = null;	
				bitmap = mImageDownLoader.downloadImage(userInfo.getPhoto(), new onImageLoaderListener() {
								@Override
								public void onImageLoader(Bitmap bitmap, String url) {
									if(userphoto != null && bitmap != null){
										//回调之后才会设置,现在不会直接显示图片
										userphoto.setImageBitmap(bitmap);
									}
								}
							});
				if(bitmap!=null){
					userphoto.setImageBitmap(bitmap);
				}
			}
		}
		
	}
	@Override
	public void initJabActionBar() {
		jabGetActionBar().hide();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.person_main_back:
			finish();
			break;
        case R.id.person_main_userphoto:
        	showBottomDialog();
			break;
		default:
			break;
		}
	}

	//底部选择照片dialog
    protected void showBottomDialog(){
    	if(null==bottomDialog){
    		bottomDialog=new BottomDialog(PersonMainActivity.this);
    	}
    	
    	 Window window = bottomDialog.getWindow();
    	 window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置 
    	bottomDialog.show();
    	
    	WindowManager windowManager = getWindowManager();
    	Display display = windowManager.getDefaultDisplay();
    	WindowManager.LayoutParams lp = bottomDialog.getWindow().getAttributes();
    	lp.width = (int)(display.getWidth()); //设置宽度
    	bottomDialog.getWindow().setAttributes(lp);
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) {
			return;
		}
		
		switch (requestCode) {
		// 拍照获取图片
		case ImageUtils.GET_IMAGE_BY_CAMERA:
			// uri传入与否影响图片获取方式,以下二选一
			// 方式一,自定义Uri(ImageUtils.imageUriFromCamera),用于保存拍照后图片地址
			if(ImageUtils.imageUriFromCamera != null) {
				// 可以直接显示图片,或者进行其他处理(如压缩或裁剪等)
//				iv.setImageURI(ImageUtils.imageUriFromCamera);
				
				// 对图片进行裁剪
				ImageUtils.cropImage(this, ImageUtils.imageUriFromCamera);
				break;
			}
			
			break;
		// 手机相册获取图片
		case ImageUtils.GET_IMAGE_FROM_PHONE:
			if(data != null && data.getData() != null) {
				// 可以直接显示图片,或者进行其他处理(如压缩或裁剪等)
				// iv.setImageURI(data.getData());
				
				// 对图片进行裁剪
				ImageUtils.cropImage(this, data.getData());
			}
			break;
		// 裁剪图片后结果
		case ImageUtils.CROP_IMAGE:
			if(ImageUtils.cropImageUri != null) {
				// 可以直接显示图片,或者进行其他处理(如压缩等)
				userphoto.setImageURI(ImageUtils.cropImageUri);
				//获取图片路径
				String[] proj = { MediaStore.Images.Media.DATA };
				Cursor actualimagecursor = managedQuery(ImageUtils.cropImageUri,proj,null,null,null);
				int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				actualimagecursor.moveToFirst();
				picPath= actualimagecursor.getString(actual_image_column_index);
//				System.out.println("图片路径："+picPath);
				//开始上传图片
				if(picPath!=null)
				{
					handler.sendEmptyMessage(TO_UPLOAD_FILE);
				}else{
					Toast.makeText(this, "上传的文件路径出错", Toast.LENGTH_LONG).show();
				}
			}
			break;
		default:
			break;
		}
	}

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case TO_UPLOAD_FILE:
				toUploadFile();
				break;
			
//			case UPLOAD_INIT_PROCESS:				
//				break;
//				
//			case UPLOAD_IN_PROCESS:				
//				break;
//				
			case UPLOAD_FILE_DONE:
				
				
				//String result = "响应码："+msg.arg1+"\n响应信息："+msg.obj+"\n耗时："+UploadUtil.getRequestTime()+"秒";
				//uploadImageResult.setText(result);
				//CommonUtil.showToast(result);
				
				break;
				
			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	};

	private void toUploadFile()
	{
		//uploadImageResult.setText("正在上传中...");
		//CommonUtil.showToast("正在上传文件...");
		progressDialog.setMessage("正在上传文件...");
		progressDialog.show();
		String fileKey = "img";
		UploadUtil uploadUtil = UploadUtil.getInstance();
		uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderId", "11111");
		uploadUtil.uploadFile( picPath,fileKey, requestURL,params);
	}
	//上传完成
	@Override
	public void onUploadDone(int responseCode, String message) {
		progressDialog.dismiss();
		Message msg = Message.obtain();
		msg.what = UPLOAD_FILE_DONE;
		msg.arg1 = responseCode;
		msg.obj = message;
		handler.sendMessage(msg);
		
		//更新数据库
		if(userInfo.getPhoto()==null){
			String photo=EAPIConsts.DOWNLOADPIC_URL+UploadUtil.filename;
			userInfo.setPhoto(photo);
			UserReqUtil.doChangeUserInfo(PersonMainActivity.this, iBindData, userInfo, null);
		}
	}

	IBindData iBindData = new IBindData() {
		@Override
		public void bindData(int tag, Object object) {
			if (tag == EAPIConsts.UserReqType.CHANGEUSERINFO && object != null) {
				simpleResult = (SimpleResult) object;
				if(simpleResult.isResult()){
					CommonUtil.showToast("头像更换成功");
				}
			}
		}
	};
	@Override
	public void onUploadProcess(int uploadSize) {
//		Message msg = Message.obtain();
//		msg.what = UPLOAD_IN_PROCESS;
//		msg.arg1 = uploadSize;
//		handler.sendMessage(msg);
	}

	@Override
	public void initUpload(int fileSize) {
//		Message msg = Message.obtain();
//		msg.what = UPLOAD_INIT_PROCESS;
//		msg.arg1 = fileSize;
//		handler.sendMessage(msg );
	}
	
}
