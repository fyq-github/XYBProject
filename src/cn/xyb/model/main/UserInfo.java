package cn.xyb.model.main;

import java.io.Serializable;
/**
 * 用户的详细信息
 * @author FYQ
 *
 */
public class UserInfo implements Serializable {
private int userid;
private String qq;
private String vname;
private String rname;
private String lovestatus;
private String sex;
private String position;
private String hobby;
private String honor;
private String photo;
private String experience;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getQq() {
	return qq;
}
public void setQq(String qq) {
	this.qq = qq;
}
public String getVname() {
	return vname;
}
public void setVname(String vname) {
	this.vname = vname;
}
public String getRname() {
	return rname;
}
public void setRname(String rname) {
	this.rname = rname;
}
public String getLovestatus() {
	return lovestatus;
}
public void setLovestatus(String lovestatus) {
	this.lovestatus = lovestatus;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
public String getHobby() {
	return hobby;
}
public void setHobby(String hobby) {
	this.hobby = hobby;
}
public String getHonor() {
	return honor;
}
public void setHonor(String honor) {
	this.honor = honor;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}

}
