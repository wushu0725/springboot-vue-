package org.spring.springboot.common;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/** @version: 1.0 
* @Description: （对类进行功能描述） 
* @author: wushu吴署
* @date: 2018年8月23日
*/
public class UserUtils {
	public static User getCurrentHr() {
        return  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
