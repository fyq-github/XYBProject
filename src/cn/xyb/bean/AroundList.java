package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;

import cn.xyb.model.around.Around;
/**
 * 周边种类列表
 * @author FYQ
 *
 */
public class AroundList implements Serializable {
private List<Around> aroundList;

public List<Around> getAroundList() {
	return aroundList;
}

public void setAroundList(List<Around> aroundList) {
	this.aroundList = aroundList;
}

}
