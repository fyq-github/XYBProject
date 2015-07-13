package cn.xyb.model.main;

import java.io.Serializable;
/**
 * 注册时用户的基本信息.。。。
 * @author FYQ
 *
 */
public class User implements Serializable {
private int userid;
private String password;
private String email;
private String phonenum;
private String school;
private int schoolid;
private String department;
private int departmentid;
private String subject;
private int subjectid;
private String clas;
private int clasid;
private String stunum;

public int getSchoolid() {
	return schoolid;
}
public void setSchoolid(int schoolid) {
	this.schoolid = schoolid;
}
public int getDepartmentid() {
	return departmentid;
}
public void setDepartmentid(int departmentid) {
	this.departmentid = departmentid;
}
public int getSubjectid() {
	return subjectid;
}
public void setSubjectid(int subjectid) {
	this.subjectid = subjectid;
}
public int getClasid() {
	return clasid;
}
public void setClasid(int clasid) {
	this.clasid = clasid;
}
public String getClas() {
	return clas;
}
public void setClas(String clas) {
	this.clas = clas;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhonenum() {
	return phonenum;
}
public void setPhonenum(String phonenum) {
	this.phonenum = phonenum;
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

}
