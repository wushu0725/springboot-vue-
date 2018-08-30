package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 
 * @author 吴署
 *
 */
public interface UserDao {

    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<User> findAllUser(User user);

    /**
     * 根据城市 ID，获取城市信息
     *
     * @param id
     * @return
     */
    User findById(@Param("id") Long id);

    Long saveUser(User user);

    Long updateUser(User user);

    Long deleteUser(Long id);
    
    User login(User user);
    
    User loadUserByUsername(String username);
}
