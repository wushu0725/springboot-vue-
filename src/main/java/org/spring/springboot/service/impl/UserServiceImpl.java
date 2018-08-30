package org.spring.springboot.service.impl;

import org.spring.springboot.common.BeanUtil;
import org.spring.springboot.common.PagedResult;
import org.spring.springboot.common.SecurityModelFactory;
import org.spring.springboot.common.TokenUtils;
import org.spring.springboot.common.UserUtils;
import org.spring.springboot.dao.MenuDao;
import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.Menu;
import org.spring.springboot.domain.User;
import org.spring.springboot.domain.interfe.TokenDetail;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 吴署
 *
 */
@Service
public class UserServiceImpl implements UserService {

   
    @Autowired
    private UserDao userDao;
    
    private final TokenUtils tokenUtils;
    
    @Autowired
    private MenuDao menuDao;
    
    @Autowired
    public UserServiceImpl( TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }
    
 

    @Override
    public PagedResult<User> findAllUser(Integer pageNumber,Integer pageSize,String username,String enable){
    	PageHelper.startPage(pageNumber, pageSize);
    	User user = new User();
    	if(username!=null&&username.length()>0) {
    		user.setUsername(username);
    	}
    
        return BeanUtil.toPagedResult(userDao.findAllUser(user));
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Long saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public Long updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public Long deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
    
    @Override
    public String generateToken(TokenDetail tokenDetail) {
        return tokenUtils.generateToken(tokenDetail);
    }
    
    


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDao.loadUserByUsername(username);
		
		System.out.println("用户：="+username);
		
		if(user!=null) {  //查询不到用户怎么处理
			return SecurityModelFactory.create(user);
		}else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
	}



	@Override
	public List<Menu> getMenusByUserId() {
		// TODO Auto-generated method stub
		return menuDao.getMenusByUserId(UserUtils.getCurrentUser().getId());
	}

}
