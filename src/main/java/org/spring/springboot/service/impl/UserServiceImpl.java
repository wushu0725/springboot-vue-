package org.spring.springboot.service.impl;

import org.spring.springboot.dao.CityDao;
import org.spring.springboot.dao.PermissionDao;
import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.Permission;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.CityService;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市业务逻辑实现类
 *
 * Created by 吴署.
 */
@Service
public class UserServiceImpl implements UserService {

   
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PermissionDao permissionDao;
    
 

    public List<User> findAllUser(){
        return userDao.findAllUser();
    }

    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Long saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public Long updateUser(User User) {
        return userDao.updateUser(User);
    }

    @Override
    public Long deleteUser(Long id) {
        return userDao.deleteUser(id);
    }


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.loadUserByUsername(username);
		
		System.out.println("用户：="+username);
		
		if(user!=null) {  //查询不到用户怎么处理
			List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
			List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
			for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {

                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
                }
            }
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
	}

}
