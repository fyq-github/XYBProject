package cn.xyb.model.around;

import java.io.Serializable;
/**
 * 周边条目的评论实体类
 * @author FYQ
 *
 */
public class AroundComments implements Serializable {
private int id;
private int merchantsid;
private String merchantscomment;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getMerchantsid() {
	return merchantsid;
}
public void setMerchantsid(int merchantsid) {
	this.merchantsid = merchantsid;
}
public String getMerchantscomment() {
	return merchantscomment;
}
public void setMerchantscomment(String merchantscomment) {
	this.merchantscomment = merchantscomment;
}


}
