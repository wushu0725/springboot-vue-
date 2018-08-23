package org.spring.springboot.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.springboot.common.UserUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

/** @version: 1.0 
* @Description: （对类进行功能描述） 
* @author: wushu吴署
* @date: 2018年8月23日
*/
@Service
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:82");
		
		response.setHeader("Access-Control-Allow-Credentials", "true");

		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

		response.setHeader("Access-Control-Max-Age", "3600");

		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		
		String s = "{\"status\":\"success\",\"msg\":" + objectMapper.writeValueAsString(UserUtils.getCurrentHr()) + "}";
		
		out.write(s);
        out.flush();
        out.close();
	}

}
