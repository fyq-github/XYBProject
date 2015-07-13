package cn.xyb.model.info;

import java.io.Serializable;

public class Info implements Serializable {
private int infoid;
private String infocategory;
private String infocontent;
public int getInfoid() {
	return infoid;
}
public void setInfoid(int infoid) {
	this.infoid = infoid;
}
public String getInfocategory() {
	return infocategory;
}
public void setInfocategory(String infocategory) {
	this.infocategory = infocategory;
}
public String getInfocontent() {
	return infocontent;
}
public void setInfocontent(String infocontent) {
	this.infocontent = infocontent;
}

}
