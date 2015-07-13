package cn.xyb.model.activities;

import java.io.Serializable;
/**
 * 活动报名信息
 * @author FYQ
 *
 */
public class ActPeople implements Serializable {
private int actid;
private int userid;
private String rname;
private String sex;
private String school;
private String department;
private String subject;
private String stunum;
private String phonenum;
public int getActid() {
	return actid;
}
public void setActid(int actid) {
	this.actid = actid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getRname() {
	return rname;
}
public void setRname(String rname) {
	this.rname = rname;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getSchool() {
	return school;
}
public void setSchool(String school) {
	this.school = school;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getStunum() {
	return stunum;
}
public void setStunum(String stunum) {
	this.stunum = stunum;
}
public String getPhonenum() {
	return phonenum;
}
public void setPhonenum(String phonenum) {
	this.phonenum = phonenum;
}

}
