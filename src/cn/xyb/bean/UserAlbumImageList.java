package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;
import cn.xyb.model.main.user.UserImage;

public class UserAlbumImageList implements Serializable {
private int albumid;
private List<UserImage> userImageList;
public int getAlbumid() {
	return albumid;
}
public void setAlbumid(int albumid) {
	this.albumid = albumid;
}
public List<UserImage> getUserImageList() {
	return userImageList;
}
public void setUserImageList(List<UserImage> userImageList) {
	this.userImageList = userImageList;
}

}
