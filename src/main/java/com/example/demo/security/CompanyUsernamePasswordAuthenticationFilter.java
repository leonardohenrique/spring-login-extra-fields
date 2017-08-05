package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CompanyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public static final String SPRING_SECURITY_FORM_COMPANY_KEY = "company";

	private String companyParameter = SPRING_SECURITY_FORM_COMPANY_KEY;
	
	private boolean postOnly = true;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String company = obtainCompany(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}
		
		if (company == null) {
			company = "";
		}

		username = username.trim();
		
		company = company.trim();

		CompanyUsernamePasswordAuthenticationToken authRequest = new CompanyUsernamePasswordAuthenticationToken(
				company, username, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	protected String obtainCompany(HttpServletRequest request) {
		return request.getParameter(companyParameter);
	}
	
}
