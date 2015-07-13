package cn.xyb.model.fun;

import java.io.Serializable;
/**
 * 趣吧类别
 * @author FYQ
 *
 */
public class Fun implements Serializable {
private int funid;
private String funname;
private String funsummary;
public int getFunid() {
	return funid;
}
public void setFunid(int funid) {
	this.funid = funid;
}
public String getFunname() {
	return funname;
}
public void setFunname(String funname) {
	this.funname = funname;
}
public String getFunsummary() {
	return funsummary;
}
public void setFunsummary(String funsummary) {
	this.funsummary = funsummary;
}

}
