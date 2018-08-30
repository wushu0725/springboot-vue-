package org.spring.springboot.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @version: 1.0
 * @Description: （对类进行功能描述）
 * @author: wushu吴署
 * @date: 2018年8月20日
 */
@Configuration
public class GlobalCorsConfig {


	@Bean
	public CorsFilter corsFilter() {
		
		//添加CORS配置信息
		CorsConfiguration config = new CorsConfiguration();
		//放行那些原始域
		config.addAllowedOrigin("*");
		//是否发送COOKIE信息
		config.setAllowCredentials(true);
		//放行哪些原始域（请求方式）
//		config.addAllowedMethod(HttpMethod.POST);
//		config.addAllowedMethod(HttpMethod.GET);
//		config.addAllowedMethod(HttpMethod.DELETE);
		
		config.addAllowedMethod("*");
		//放行哪些原始域（头部信息）
		config.addAllowedHeader("*");
		//暴露哪些头部信息  （因为跨域访问默认不能获取全部头部信息）
		//config.addExposedHeader("*");
		
		System.out.println("来到跨域设置");
		
		//添加映射路径
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		configSource.registerCorsConfiguration("/**", config);
		
		return new CorsFilter(configSource);
	}

}
