package cn.xyb.model.main;

import java.io.Serializable;
/**
 * 简单的结果返回 用户注册或者登录之类
 * @author FYQ
 *
 */
public class SimpleResult implements Serializable {
private boolean result;
private String content;
public boolean isResult() {
	return result;
}
public void setResult(boolean result) {
	this.result = result;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
}
