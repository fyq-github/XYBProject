package cn.xyb.model.around;

import java.io.Serializable;
import java.util.List;
/**
 * 周边每个条目的详细信息
 * @author FYQ
 *
 */
public class AroundDetail implements Serializable {
private int merchantsid;//商家ID
private int aroundid;
private float price;
private String merchantinfo;
private String recommend;
private String prompt;
private List<AroundComments> commentlist; //这商家的所有评论
public List<AroundComments> getCommentlist() {
	return commentlist;
}
public void setCommentlist(List<AroundComments> commentlist) {
	this.commentlist = commentlist;
}

public int getMerchantsid() {
	return merchantsid;
}
public void setMerchantsid(int merchantsid) {
	this.merchantsid = merchantsid;
}
public int getAroundid() {
	return aroundid;
}
public void setAroundid(int aroundid) {
	this.aroundid = aroundid;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public String getMerchantinfo() {
	return merchantinfo;
}
public void setMerchantinfo(String merchantinfo) {
	this.merchantinfo = merchantinfo;
}
public String getRecommend() {
	return recommend;
}
public void setRecommend(String recommend) {
	this.recommend = recommend;
}
public String getPrompt() {
	return prompt;
}
public void setPrompt(String prompt) {
	this.prompt = prompt;
}


}
