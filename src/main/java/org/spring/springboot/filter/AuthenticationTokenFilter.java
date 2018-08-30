package org.spring.springboot.filter;

import org.spring.springboot.common.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @version V1.0.0
 * @Description 解析 token 的过滤器
 * 配置在 Spring Security 的配置类中
 * 用于解析 token ，将用户所有的权限写入本次 Spring Security 的会话中
 * @Author l
 * @Date 
 */
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * json web token 在请求头的名字
     */
    @Value("${token.header}")
    private String tokenHeader;

    /**
     * 辅助操作 token 的工具类
     */
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * Spring Security 的核心操作服务类
     * 在当前类中将使用 UserDetailsService 来获取 userDetails 对象
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 将 ServletRequest 转换为 HttpServletRequest 才能拿到请求头中的 token
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 尝试获取请求头的 token
        String authToken = httpRequest.getHeader(this.tokenHeader);
        
        //OPTIONS方法不做检验，直接跳过返回
        if(httpRequest.getMethod().equals("OPTIONS")) {
        	System.out.println("OPTIONS方法跳过");
        
        	chain.doFilter(request, response);
        	return ;
        }
        
//        if(1==1) {
//        	 chain.doFilter(request, response);
//         	return ;
//        }
       
        System.out.println(authToken);
        
        // 尝试拿 token 中的 username
        // 若是没有 token 或者拿 username 时出现异常，那么 username 为 null
        String username = this.tokenUtils.getUsernameFromToken(authToken);

        
        
        
        // 如果上面解析 token 成功并且拿到了 username 并且本次会话的权限还未被写入
        if (username != null) {
            // 用 UserDetailsService 从数据库中拿到用户的 UserDetails 类
            // UserDetails 类是 Spring Security 用于保存用户权限的实体类
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // 检查用户带来的 token 是否有效
            // 包括 token 和 userDetails 中用户名是否一样， token 是否过期， token 生成时间是否在最后一次密码修改时间之前
            // 若是检查通过
            if (this.tokenUtils.validateToken(authToken, userDetails)) {
                // 生成通过认证
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                // 将权限写入本次会话
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                chain.doFilter(request, response);
            }else {
            	response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println("{\"code\":401,\"message\":\"没有携带 token 或者 token 无效！\",\"data\":\"\"}");
                return;
            }
            if (!userDetails.isEnabled()){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print("{\"code\":\"452\",\"data\":\"\",\"message\":\"账号处于黑名单\"}");
                return;
            }
            
        }
        //判断SecurityContextHolder.getContext().getAuthentication()被写入的情况没做即站内跳转
        else {
        	
        	
        	httpResponse.setCharacterEncoding("UTF-8");
        	httpResponse.setContentType("application/json;charset=UTF-8");
             
        	httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:82");
     		
        	httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        	httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

        	httpResponse.setHeader("Access-Control-Max-Age", "3600");

        	httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
             
        	httpResponse.getWriter().println("{\"code\":401,\"message\":\"没有携带 token 或者 token 无效！\",\"data\":\"\"}");
             return;
        }
    }
    
    

}
