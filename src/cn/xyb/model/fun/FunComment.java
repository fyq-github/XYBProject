package cn.xyb.model.fun;

import java.io.Serializable;

public class FunComment implements Serializable {
private int id;
private int fundetailid;
private String fundetailcomment;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getFundetailid() {
	return fundetailid;
}
public void setFundetailid(int fundetailid) {
	this.fundetailid = fundetailid;
}
public String getFundetailcomment() {
	return fundetailcomment;
}
public void setFundetailcomment(String fundetailcomment) {
	this.fundetailcomment = fundetailcomment;
}

}
