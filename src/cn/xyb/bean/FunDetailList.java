package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;
import cn.xyb.model.fun.FunDetail;

public class FunDetailList implements Serializable {
private int funid;
private List<FunDetail> funDetaillist;
public int getFunid() {
	return funid;
}
public void setFunid(int funid) {
	this.funid = funid;
}
public List<FunDetail> getFunDetaillist() {
	return funDetaillist;
}
public void setFunDetaillist(List<FunDetail> funDetaillist) {
	this.funDetaillist = funDetaillist;
}

}
