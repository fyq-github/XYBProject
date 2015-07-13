package cn.xyb.model.main.user;

import java.io.Serializable;
/**
 * 用户说说实体类
 * @author FYQ
 *
 */
public class talk implements Serializable {
private int id;
private String vname;
private int userid;
private String talkcontent;
private String time;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getTalkcontent() {
	return talkcontent;
}
public void setTalkcontent(String talkcontent) {
	this.talkcontent = talkcontent;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}

}
