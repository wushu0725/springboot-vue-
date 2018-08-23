package org.spring.springboot.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @version: 1.0
 * @Description: （对类进行功能描述）
 * @author: wushu吴署
 * @date: 2018年8月20日
 */
@Component
public class CorsFilterx implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletResponse response = (HttpServletResponse) res;


		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Allow-Credentials", "true");

		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

		response.setHeader("Access-Control-Max-Age", "3600");

		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		
		HttpServletRequest request = (HttpServletRequest) req;
		

		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
