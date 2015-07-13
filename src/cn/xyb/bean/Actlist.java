package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;

import cn.xyb.model.activities.Activities;
/**
 * 活动列表
 * @author FYQ
 *
 */
public class Actlist implements Serializable {

private List<Activities> actList;

public List<Activities> getActList() {
	return actList;
}
public void setActList(List<Activities> actList) {
	this.actList = actList;
}

}
