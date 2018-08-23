package org.spring.springboot.domain;

import org.springframework.security.core.GrantedAuthority;

/** @version: 1.0 
* @Description: （对类进行功能描述） 
* @author: wushu吴署
* @date: 2018年8月17日
*/
public class Role implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
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
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.getName();
	}
	
}
