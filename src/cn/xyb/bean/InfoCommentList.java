package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;
import cn.xyb.model.info.InfoComment;

public class InfoCommentList implements Serializable {
private int infoitemid;
private List<InfoComment> infoCommentList;
public int getInfoitemid() {
	return infoitemid;
}
public void setInfoitemid(int infoitemid) {
	this.infoitemid = infoitemid;
}
public List<InfoComment> getInfoCommentList() {
	return infoCommentList;
}
public void setInfoCommentList(List<InfoComment> infoCommentList) {
	this.infoCommentList = infoCommentList;
}

}
