package cn.xyb.model.main.user;

import java.io.Serializable;
/**
 * 单个用户图片信息
 * @author FYQ
 *
 */
public class UserImage implements Serializable {
private int userid;
private int albumid;
private String img_path;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public int getAlbumid() {
	return albumid;
}
public void setAlbumid(int albumid) {
	this.albumid = albumid;
}
public String getImg_path() {
	return img_path;
}
public void setImg_path(String img_path) {
	this.img_path = img_path;
}

}
