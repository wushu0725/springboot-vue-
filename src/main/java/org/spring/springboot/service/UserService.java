package org.spring.springboot.service;


import java.util.List;

import org.spring.springboot.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 城市业务逻辑接口类
 *
 * Created by 吴署
 */

public interface UserService extends UserDetailsService{

    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<User> findAllUser();

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
}
