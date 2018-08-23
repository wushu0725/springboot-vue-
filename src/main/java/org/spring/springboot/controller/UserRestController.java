package org.spring.springboot.controller;

import java.util.List;

import org.spring.springboot.domain.City;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("UserRestController相关的api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "根据用户名密码登陆的get方法", notes = "根据用户名密码登陆")
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public List<User> findAllUser() {
		System.out.println("findAllUser");
        return userService.findAllUser();
    }
	
	@ApiOperation(value = "根据用户名密码登陆的post方法", notes = "根据用户名密码登陆")
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public User login(@RequestBody User users) {
		System.out.println("loginPost");
		
        return userService.findAllUser().get(0);
    }
	
}
