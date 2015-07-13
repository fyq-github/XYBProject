package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;

import cn.xyb.model.around.AroundDetail;
/**
 * 周边某一类别中全部信息   list
 * @author FYQ
 *
 */
public class AroundDetList implements Serializable {
private int aroundid;
private List<AroundDetail> aroundDeailList;
public int getAroundid() {
	return aroundid;
}
public void setAroundid(int aroundid) {
	this.aroundid = aroundid;
}
public List<AroundDetail> getAroundDeailList() {
	return aroundDeailList;
}
public void setAroundDeailList(List<AroundDetail> aroundDeailList) {
	this.aroundDeailList = aroundDeailList;
}

}
