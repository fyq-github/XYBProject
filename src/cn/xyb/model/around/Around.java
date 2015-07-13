package cn.xyb.model.around;

import java.io.Serializable;
/**
 * 周边的一个种类
 * @author FYQ
 *
 */
public class Around implements Serializable {
private int aroundid;
private String aroundname;
public int getAroundid() {
	return aroundid;
}
public void setAroundid(int aroundid) {
	this.aroundid = aroundid;
}
public String getAroundname() {
	return aroundname;
}
public void setAroundname(String aroundname) {
	this.aroundname = aroundname;
}

}
