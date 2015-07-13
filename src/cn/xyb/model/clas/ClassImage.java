package cn.xyb.model.clas;

import java.io.Serializable;
/**
 * 班级单个相片
 * @author FYQ
 *
 */
public class ClassImage implements Serializable {
private int id;
private int classid;
private String classimgpath;
private String imgtime;

public String getImgtime() {
	return imgtime;
}
public void setImgtime(String imgtime) {
	this.imgtime = imgtime;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getClassid() {
	return classid;
}
public void setClassid(int classid) {
	this.classid = classid;
}
public String getClassimgpath() {
	return classimgpath;
}
public void setClassimgpath(String classimgpath) {
	this.classimgpath = classimgpath;
}

}
