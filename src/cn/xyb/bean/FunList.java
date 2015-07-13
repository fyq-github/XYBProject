package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;
import cn.xyb.model.fun.Fun;

public class FunList implements Serializable {
private List<Fun> funlist;

public List<Fun> getFunlist() {
	return funlist;
}

public void setFunlist(List<Fun> funlist) {
	this.funlist = funlist;
}

}
