package cn.xyb.model.activities;

import java.io.Serializable;
/**
 * 活动实体类
 * @author FYQ
 *
 */
public class Activities implements Serializable {
private int actid;
private int orgid;
private String actname;
private String actcontent;
private String actstarttime;
private String actendtime;
private String actsummary;
private String actimg;
public int getActid() {
	return actid;
}
public void setActid(int actid) {
	this.actid = actid;
}
public int getOrgid() {
	return orgid;
}
public void setOrgid(int orgid) {
	this.orgid = orgid;
}
public String getActname() {
	return actname;
}
public void setActname(String actname) {
	this.actname = actname;
}
public String getActcontent() {
	return actcontent;
}
public void setActcontent(String actcontent) {
	this.actcontent = actcontent;
}
public String getActstarttime() {
	return actstarttime;
}
public void setActstarttime(String actstarttime) {
	this.actstarttime = actstarttime;
}
public String getActendtime() {
	return actendtime;
}
public void setActendtime(String actendtime) {
	this.actendtime = actendtime;
}
public String getActsummary() {
	return actsummary;
}
public void setActsummary(String actsummary) {
	this.actsummary = actsummary;
}
public String getActimg() {
	return actimg;
}
public void setActimg(String actimg) {
	this.actimg = actimg;
}

}
