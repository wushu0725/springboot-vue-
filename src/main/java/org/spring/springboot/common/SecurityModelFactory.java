package org.spring.springboot.common;

import org.spring.springboot.domain.User;
import org.spring.springboot.domain.UserDetailImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.Date;

/**
 * @version V1.0.0
 * @Description 用于将 LoginUser 转换成 UserDetail对象
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017年8月13日17:04:08
 */
public class SecurityModelFactory {

    public static UserDetailImpl create(User user) {
        Date lastPasswordReset = new Date();
        lastPasswordReset.setTime(user.getLastPasswordChange());
        return new UserDetailImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                lastPasswordReset,
                user.getAuthorities(),
                user.enable()
        );
    }

}
