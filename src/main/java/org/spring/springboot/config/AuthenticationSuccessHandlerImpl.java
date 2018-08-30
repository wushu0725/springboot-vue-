package org.spring.springboot.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.springboot.common.UserUtils;
import org.spring.springboot.domain.Data;
import org.spring.springboot.domain.ResultMap;
import org.spring.springboot.domain.TokenDetailImpl;
import org.spring.springboot.domain.interfe.LoginDetail;
import org.spring.springboot.domain.interfe.TokenDetail;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

/** @version: 1.0 
* @Description: （对类进行功能描述） 
* @author: wushu吴署
* @date: 2018年8月23日
*/
@Service
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	
	@Value("${token.header}")
    private String tokenHeader;
	
	
	@Autowired
	private UserService userservice;
	
	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:82");
		
		response.setHeader("Access-Control-Allow-Credentials", "true");

		response.setHeader("Access-Control-Allow-Methods", "POST, GET");

		
		
		
		response.setHeader("Access-Control-Max-Age", "3600");

		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

		out.write(JSON.toJSONString(new ResultMap().success().message("")
				.data(new Data().addObj(tokenHeader, userservice.generateToken(new TokenDetailImpl(UserUtils.getCurrentUser().getUsername())))
						        )));
        out.flush();
        out.close();
	}
	


}
