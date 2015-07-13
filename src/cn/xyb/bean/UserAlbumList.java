package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;
import cn.xyb.model.main.user.UserAlbum;

public class UserAlbumList implements Serializable {
private int userid;
private List<UserAlbum> userAlbumList;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public List<UserAlbum> getUserAlbumList() {
	return userAlbumList;
}
public void setUserAlbumList(List<UserAlbum> userAlbumList) {
	this.userAlbumList = userAlbumList;
}

}
