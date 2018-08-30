package org.spring.springboot.common;

import org.spring.springboot.domain.UserDetailImpl;
import org.springframework.security.core.context.SecurityContextHolder;

/** @version: 1.0 
* @Description: （对类进行功能描述） 
* @author: wushu吴署
* @date: 2018年8月23日
*/
public class UserUtils {
	public static UserDetailImpl getCurrentUser() {
		
        return  (UserDetailImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
