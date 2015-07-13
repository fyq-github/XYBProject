package cn.xyb.model.info;

import java.io.Serializable;

public class InfoDetail implements Serializable {
private int infoitemid;
private int infoid;
private String introduce;
private String introimg;
public int getInfoitemid() {
	return infoitemid;
}
public void setInfoitemid(int infoitemid) {
	this.infoitemid = infoitemid;
}
public int getInfoid() {
	return infoid;
}
public void setInfoid(int infoid) {
	this.infoid = infoid;
}
public String getIntroduce() {
	return introduce;
}
public void setIntroduce(String introduce) {
	this.introduce = introduce;
}
public String getIntroimg() {
	return introimg;
}
public void setIntroimg(String introimg) {
	this.introimg = introimg;
}

}
