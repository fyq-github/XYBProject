package cn.utils.http;

public class EAPIConsts {
	private final static int DEVPUBLIC = 1;  //开发（内网）
	private final static int ONLINE=2;      //上线
	
		public static int ENVIRONMENT = DEVPUBLIC; // api接口地址
		public static int UPLOADPIC = DEVPUBLIC; // 图片上传接口地址
		public static int DOWNLOADPIC = DEVPUBLIC; // 图片下载接口地址
		
		// 内网开发环境地址    192.168.211.204
		public static final String LAN_URL_DEVPUBLIC = "http://192.168.43.66:9999/XYBServer/api"; 
		//外网开发环境地址
		public static final String WAN_URL_DEVPUBLIC = "http://115.28.236.237:9999/XYBServer/api"; 
		//图片下载内网地址
		public static final String lAN_DOWNPIC_URL ="http://192.168.43.66:8080/fileUpload/upload/";
		//图片下载外网地址
		public static final String WAN_DOWNPIC_URL ="http://115.28.236.237:8080/fileUpload/upload/";
		//图片上传内网地址
		public static final String lAN_UPPIC_URL ="http://192.168.43.66:8080/fileUpload/file_upload";
		//图片上传外网地址
		public static final String WAN_UPPIC_URL ="http://115.28.236.237:8080/fileUpload/file_upload";
		
		
		// 普通接口地址（调用方法）
		public static final String COMMON_URL = getCOMMONSUrl();
		//图片上传接口地址
		public static final String UPLOADPIC_URL = getUPLOADPICUrl();
		//图片下载接口地址
		public static final String DOWNLOADPIC_URL = getDOWNLOADPICUrl();
		
		public static String getUPLOADPICUrl(){
			switch (UPLOADPIC) {
			case DEVPUBLIC:  //局域网
				return lAN_UPPIC_URL;
			case ONLINE: //广域网 （上线）
				return WAN_UPPIC_URL;
			default:
				return WAN_UPPIC_URL;
			}
		}
		
		public static String getDOWNLOADPICUrl(){
			switch (DOWNLOADPIC) {
			case DEVPUBLIC:  //局域网
				return lAN_DOWNPIC_URL;
			case ONLINE: //广域网 （上线）
				return WAN_DOWNPIC_URL;
			default:
				return WAN_DOWNPIC_URL;
			}
		}
		
		
		public static String getCOMMONSUrl(){
			switch (ENVIRONMENT) {
			case DEVPUBLIC:  //局域网
				return LAN_URL_DEVPUBLIC;
			case ONLINE: //广域网 （上线）
				return WAN_URL_DEVPUBLIC;
			default:
				return WAN_URL_DEVPUBLIC;
			}
		}
		/**
		 * 请求地址的URL（后面的串）
		 * @author 
		 */
		public static class ReqUrl {
			//用户相关
			public static final String EMAILLOGIN = "/users/checkUserByEmail/"; // 邮箱登录  后加 gson用户信息
			public static final String PHONELOGIN = "/users/checkUserByPhonenum/"; // 手机号登录  后加 gson用户信息
			public static final String GETUSERINFO = "/users/getUserInfoById/"; // 获取用户详细信息  后加 用户id
			public static final String FINDPWD = "/users/findPwdByemail/"; // 找回密码  后加 邮箱
			public static final String CHANGEUSER = "/users/changeUser/"; // 修改用户基本信息   后加 user信息
			public static final String ADDUSER = "/users/addUser/"; // 注册用户    后加 gson用户信息
			public static final String CHANGEUSERINFO = "/users/changeUserInfo/"; //修改用户详细信息    后加 userinfo
			public static final String GETUSERALBUMIMGS = "/users/getUserAlbumImgs/"; //修改用户某相册相片    后加 albumid
			public static final String GETUSERALBUMS = "/users/getUserAlbums/"; //获取用户相册表    后加 userid
			public static final String GETUSER="/users/getUserById/";//获取用户基本信息
			//活动相关
			public static final String GETACTLIST = "/activities/getActList/"; // 获取活动列表
			public static final String SIGNUPACT = "/activities/signUpActivity/"; //报名活动 后加  actpeople
			//班级相关 
			public static final String GETCLASSDETAIL = "/class/getClassDetailById/"; // 班级详细信息  加classid
			//资讯相关
			public static final String GETINFOCOMMENT = "/info/getInfoCommentList/"; // 获取资讯某类别某条目评论列表    后加 infoitemid
			public static final String ADDINFOCOMMENT = "/info/addInfoComment/"; //增加评论 加参数
			public static final String GETINFODETAIL = "/info/getInfoDetailList/"; //获取资讯评论  加infoid
			public static final String GETINFOLIST = "/info/getInfoList/"; //获取资讯类别表 
			//趣吧相关
			public static final String GETLOVEWALLLIST = "/fun/getLoveWallList/"; //获取表白墙 不加参数
			public static final String ADDLOVEWALL = "/fun/addLoveWall/"; //增加表白墙
			public static final String GETFUNDETAILLIST = "/fun/getFunDetailList/"; //获取趣吧某类别详细信息
			public static final String GETFUNLIST = "/fun/getFunList/"; //获取趣吧所有类别
			public static final String ADDFUNCOMMENT = "/fun/addFunComment/"; //获取趣吧所有类别
			//周边相关
			public static final String ADDAROUNDCOMMENT = "/around/addAroundComment/"; //增加周边商家评论
			public static final String AROUNDDETAILLIST = "/around/getAroundDetailList/"; //获取周边某类别商家列表
			public static final String GETAROUNDLLIST = "/around/getAroundList/"; //获取周边种类列表
			
		}
		/**
		 * 用作数据的标记  用户相关
		 * @author 
		 */
		public static class UserReqType{  //1-30
			public static final int EMAILLOGIN=1;//邮箱登录
			public static final int PHONELOGIN=2;//手机号登录
			public static final int GETUSERINFO=3;//获取用户详细信息
			public static final int FINDPWD=4;//找回密码
			public static final int CHANGEUSER=5;//修改用户
			public static final int ADDUSER=6;//注册用户
			public static final int CHANGEUSERINFO=7;//修改用户详细信息
			public static final int GETUSERALBUMIMGS=8;
			public static final int GETUSERALBUMS=9;
			public static final int GETUSER=10;
			
		}
		/**
		 * 活动  相关
		 * @author 
		 */
		public static class ActivityReqType{ //31-50
			public static final int GETACTLIST=31;
			public static final int SIGNUPACT=32;
		}
		
		/**
		 * 班级  相关
		 * @author 
		 */
		public static class ClassReqType{//51-70
			public static final int GETCLASSDETAIL=51;
		}
		
		/**
		 * 资讯  相关
		 * @author 
		 */
		public static class InfoReqType{//71-90
			public static final int GETINFOCOMMENT=71;
			public static final int ADDINFOCOMMENT=72;
			public static final int GETINFODETAIL=73;
			public static final int GETINFOLIST=74;
		}
		
		/**
		 * 趣吧  相关
		 * @author 
		 */
		public static class FunReqType{//91-110
			public static final int GETLOVEWALLLIST=91;
			public static final int ADDLOVEWALL=92;
			public static final int GETFUNDETAILLIST=93;
			public static final int GETFUNLIST=94;
			public static final int ADDFUNCOMMENT=95;
		}
		
		/**
		 * 周边  相关
		 * @author 
		 */
		public static class AroundReqType{//111-131
			public static final int ADDAROUNDCOMMENT=111;
			public static final int AROUNDDETAILLIST=112;
			public static final int GETAROUNDLLIST=113;
		}
}
