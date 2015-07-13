package cn.xyb.bean;

import java.io.Serializable;
import java.util.List;
import cn.xyb.model.info.Info;

public class InfoList implements Serializable {
private List<Info> infoList;

public List<Info> getInfoList() {
	return infoList;
}

public void setInfoList(List<Info> infoList) {
	this.infoList = infoList;
}

}
