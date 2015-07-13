package cn.utils.common;

import cn.xyb.application.MyApplication;
import cn.xyb.ui.main.LoginActivity;
import android.widget.Toast;
/**
 * 提供普通的一些工具
 * @author FYQ
 *
 */
public class CommonUtil {
	// 短显示消息 (传进来的参数没有 Context )
		public static void showToast(String text) {
			Toast.makeText(MyApplication.getApp(), text, Toast.LENGTH_SHORT).show();
		}

		// 长显示消息 (传进来的参数没有 Context )
		public static void showLongToast(String text) {
			Toast.makeText(MyApplication.getApp(), text, Toast.LENGTH_SHORT).show();
		}
		//返回全局的用户ID
		public static int getUserId(){
			int userid=LoginActivity.sharedPreferences.getInt("userid", 0);
			return userid;
		}
		/**
		 * 获取文件后缀名
		 * @param filename
		 * @return
		 */
		public static String getExtensionName(String filename) { 
	        if ((filename != null) && (filename.length() > 0)) { 
	            int dot = filename.lastIndexOf('.'); 
	            if ((dot >-1) && (dot < (filename.length() - 1))) { 
	                return filename.substring(dot + 1); 
	            } 
	        } 
	        return filename;
		}
}
