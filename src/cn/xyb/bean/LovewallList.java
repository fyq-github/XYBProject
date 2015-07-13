package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;

import cn.xyb.model.fun.Lovewall;

public class LovewallList implements Serializable {
private List<Lovewall> lovelist;

public List<Lovewall> getLovelist() {
	return lovelist;
}

public void setLovelist(List<Lovewall> lovelist) {
	this.lovelist = lovelist;
}

}
