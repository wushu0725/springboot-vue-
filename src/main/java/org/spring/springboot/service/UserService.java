package org.spring.springboot.service;


import java.util.List;

import org.spring.springboot.common.PagedResult;
import org.spring.springboot.domain.Menu;
import org.spring.springboot.domain.User;
import org.spring.springboot.domain.interfe.TokenDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 
 * @author 吴署
 *
 */

public interface UserService extends UserDetailsService{

    /**
     * 获取城市信息列表
     *
     * @return
     */
	PagedResult<User> findAllUser(Integer pageNumber,Integer pageSize,String username,String enable);

    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Long saveUser(User user);

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    Long updateUser(User user);

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     * @return
     */
    Long deleteUser(Long id);
    
    
    UserDetails loadUserByUsername(String username);
    
    String generateToken(TokenDetail tokenDetail);
    
    List<Menu> getMenusByUserId();
}
