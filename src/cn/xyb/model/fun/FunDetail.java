package cn.xyb.model.fun;

import java.io.Serializable;
import java.util.List;

public class FunDetail implements Serializable {
private int fundetailid;
private int funid;
private String funcontent;
private List<FunComment> funComments;

public List<FunComment> getFunComments() {
	return funComments;
}
public void setFunComments(List<FunComment> funComments) {
	this.funComments = funComments;
}
public int getFundetailid() {
	return fundetailid;
}
public void setFundetailid(int fundetailid) {
	this.fundetailid = fundetailid;
}
public int getFunid() {
	return funid;
}
public void setFunid(int funid) {
	this.funid = funid;
}
public String getFuncontent() {
	return funcontent;
}
public void setFuncontent(String funcontent) {
	this.funcontent = funcontent;
}

}
