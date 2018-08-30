package org.spring.springboot.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.spring.springboot.domain.interfe.LoginDetail;
import org.spring.springboot.domain.interfe.TokenDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author 吴署
 *
 */
public class User implements LoginDetail,TokenDetail {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -41414852587876849L;
	
	private Long id;
	
	private String remark;

	private String password;
	
	private String username;
	
	private Long lastPasswordChange;
	
	private char enable;
	
	List<Role> roles;
	
	@JsonIgnore
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        if(roles!=null) {
		        for (Role role : roles) {
		            authorities.add(new SimpleGrantedAuthority(role.getName()));
		        }
	        }
	        return authorities;
	    }


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	public Long getLastPasswordChange() {
		return lastPasswordChange;
	}

	public void setLastPasswordChange(Long lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}


	public char getEnable() {
		return enable;
	}

	public void setEnable(char enable) {
		this.enable = enable;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
    public boolean enable() {
        if (this.enable == '1'){
            return true;
        }
        return false;
    }

    
}
