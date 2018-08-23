package org.spring.springboot.domain;

/** @version: 1.0 
* @Description: （对类进行功能描述） 
* @author: wushu吴署
* @date: 2018年8月16日
*/
public class Permission {
	private int id;
	private String name;
	private String url;
	private String desc;
	private int pid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
