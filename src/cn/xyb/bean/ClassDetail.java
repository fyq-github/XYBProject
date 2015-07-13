package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;

import cn.xyb.model.clas.ClassImage;
import cn.xyb.model.main.UserInfo;
/**
 * 班级的详细信息  含有简介  相册  成员
 * @author FYQ
 *
 */
public class ClassDetail implements Serializable {
private int classid;
private String classname;
private int subjectid;
private String classintroduct;
private List<ClassImage> classImagelist;
private List<UserInfo> userInfolist;

public List<UserInfo> getUserInfolist() {
	return userInfolist;
}
public void setUserInfolist(List<UserInfo> userInfolist) {
	this.userInfolist = userInfolist;
}
public List<ClassImage> getClassImagelist() {
	return classImagelist;
}
public void setClassImagelist(List<ClassImage> classImagelist) {
	this.classImagelist = classImagelist;
}

public int getClassid() {
	return classid;
}
public void setClassid(int classid) {
	this.classid = classid;
}
public String getClassname() {
	return classname;
}
public void setClassname(String classname) {
	this.classname = classname;
}
public int getSubjectid() {
	return subjectid;
}
public void setSubjectid(int subjectid) {
	this.subjectid = subjectid;
}
public String getClassintroduct() {
	return classintroduct;
}
public void setClassintroduct(String classintroduct) {
	this.classintroduct = classintroduct;
}

}
