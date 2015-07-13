package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;

import cn.xyb.model.info.InfoDetail;

public class InfoDetList implements Serializable {
private int info;
private List<InfoDetail> infoDetailList;
public int getInfo() {
	return info;
}
public void setInfo(int info) {
	this.info = info;
}
public List<InfoDetail> getInfoDetailList() {
	return infoDetailList;
}
public void setInfoDetailList(List<InfoDetail> infoDetailList) {
	this.infoDetailList = infoDetailList;
}

}
