package cn.xyb.model.main.user;

import java.io.Serializable;
/**
 * 单个用户相册
 * @author FYQ
 *
 */
public class UserAlbum implements Serializable {
private int albumid;
private String albumname;
private String albumdesc;
private int userid;
public int getAlbumid() {
	return albumid;
}
public void setAlbumid(int albumid) {
	this.albumid = albumid;
}
public String getAlbumname() {
	return albumname;
}
public void setAlbumname(String albumname) {
	this.albumname = albumname;
}
public String getAlbumdesc() {
	return albumdesc;
}
public void setAlbumdesc(String albumdesc) {
	this.albumdesc = albumdesc;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}

}
