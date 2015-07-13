package cn.xyb.model.fun;

import java.io.Serializable;
/**
 * 告白墙
 * @author FYQ
 *
 */
public class Lovewall implements Serializable {
private int loveid;
private String vname;
private int userid;
private String lovecontent;
private String lovetime;
public int getLoveid() {
	return loveid;
}
public void setLoveid(int loveid) {
	this.loveid = loveid;
}
public String getVname() {
	return vname;
}
public void setVname(String vname) {
	this.vname = vname;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getLovecontent() {
	return lovecontent;
}
public void setLovecontent(String lovecontent) {
	this.lovecontent = lovecontent;
}
public String getLovetime() {
	return lovetime;
}
public void setLovetime(String lovetime) {
	this.lovetime = lovetime;
}

}
