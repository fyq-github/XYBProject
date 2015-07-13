package cn.xyb.model.main;

import java.io.Serializable;
/**
 * 用户登录  返回的结果状态
 * @author FYQ
 *
 */
public class UserLoginResult implements Serializable {
private boolean isuser;
private String reason;
private int userid;

public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public boolean isIsuser() {
	return isuser;
}
public void setIsuser(boolean isuser) {
	this.isuser = isuser;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}

}
