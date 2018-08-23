package org.spring.springboot.dao;

import java.util.List;

import org.spring.springboot.domain.Permission;

/** @version: 1.0 
* @Description: （对类进行功能描述） 
* @author: wushu吴署
* @date: 2018年8月17日
*/
public interface PermissionDao {
	List<Permission>  findAll();
	
	List<Permission>  findByAdminUserId(Long userId);
}
