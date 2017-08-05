package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CompanyUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	private String company;
	
	public CompanyUsernamePasswordAuthenticationToken(String company, Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.company = company;
	}
	
	public CompanyUsernamePasswordAuthenticationToken(String company, Object principal, Object credentials) {
		super(principal, credentials);
		this.company = company;
	}

	public CompanyUsernamePasswordAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public String getCompany() {
		return company;
	}

}
