package org.spring.springboot.controller;

import java.util.List;

import org.spring.springboot.common.PagedResult;
import org.spring.springboot.common.UserUtils;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.Menu;
import org.spring.springboot.domain.ResultMap;
import org.spring.springboot.domain.TokenDetailImpl;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 吴署
 *
 */
@RestController
@Api("UserRestController相关的api")
public class UserRestController {
	
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "根据用户名密码登陆的get方法", notes = "根据用户名密码登陆")
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResultMap findAllUser(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,@RequestParam(value = "username", defaultValue = "")String username,@RequestParam(value = "enable", defaultValue = "")String enable) {
		System.out.println("findAllUser");
		System.out.println("username="+username);
		System.out.println("enable="+enable);
		return new ResultMap().success().data(userService.findAllUser(pageNumber,pageSize,username,enable)).token(userService.generateToken(new TokenDetailImpl(UserUtils.getCurrentUser().getUsername())));
    }
	
	
	@ApiOperation(value = "得到用户的导航菜单权限", notes = "得到用户的导航菜单权限")
	@RequestMapping(value = "/api/menus", method = RequestMethod.POST)
    public List<Menu> getMenus() {
		System.out.println("/api/menus");
        return userService.getMenusByUserId();
    }
	
	@ApiOperation(value = "增加用户", notes = "得到用户的导航菜单权限")
	@RequestMapping(value = "/api/saveUser", method = RequestMethod.POST)
    public ResultMap saveUser(@RequestBody  User user) {
		if(userService.saveUser(user)==1) {
			return new ResultMap().success().message("增加用户成功").token(userService.generateToken(new TokenDetailImpl(UserUtils.getCurrentUser().getUsername())));
		}else {
			return new ResultMap().fail("500").message("增加用户失败").token(userService.generateToken(new TokenDetailImpl(UserUtils.getCurrentUser().getUsername())));
		}
    }
	
	
	@ApiOperation(value = "删除用户", notes = "删除用户")
	@RequestMapping(value = "/api/deleteUser/{id}", method = RequestMethod.GET)
    public ResultMap deleteUser(@PathVariable("id") Long id) {
		if(userService.deleteUser(id)==1) {
			return new ResultMap().success().message("删除用户成功").token(userService.generateToken(new TokenDetailImpl(UserUtils.getCurrentUser().getUsername())));
		}else {
			return new ResultMap().fail("500").message("删除用户失败").token(userService.generateToken(new TokenDetailImpl(UserUtils.getCurrentUser().getUsername())));
		}
    }
	
	@ApiOperation(value = "修改用户", notes = "修改用户")
	@RequestMapping(value = "/api/updateUser", method = RequestMethod.POST)
    public ResultMap updateUser(@RequestBody  User user) {
		if(userService.updateUser(user)==1) {
			return new ResultMap().success().message("修改用户成功").token(userService.generateToken(new TokenDetailImpl(UserUtils.getCurrentUser().getUsername())));
		}else {
			return new ResultMap().fail("500").message("修改用户失败").token(userService.generateToken(new TokenDetailImpl(UserUtils.getCurrentUser().getUsername())));
		}
    }
	
}
