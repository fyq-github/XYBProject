package cn.xyb.model.info;

import java.io.Serializable;

public class InfoComment implements Serializable {
private int id;
private int infoitemid;
private String commentcontent;
private String commenttime;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getInfoitemid() {
	return infoitemid;
}
public void setInfoitemid(int infoitemid) {
	this.infoitemid = infoitemid;
}
public String getCommentcontent() {
	return commentcontent;
}
public void setCommentcontent(String commentcontent) {
	this.commentcontent = commentcontent;
}
public String getCommenttime() {
	return commenttime;
}
public void setCommenttime(String commenttime) {
	this.commenttime = commenttime;
}

}
