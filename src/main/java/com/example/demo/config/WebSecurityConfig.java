package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.example.demo.security.CompanyUsernamePasswordAuthenticationFilter;
import com.example.demo.security.CustomUserDetailsAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private CustomUserDetailsAuthenticationProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(
                    HttpMethod.GET,
//                    "/",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/**/*.png"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authenticationFilter(), CompanyUsernamePasswordAuthenticationFilter.class)
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authProvider);
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SimpleUrlAuthenticationFailureHandler failureHandler() {
    	SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/login?error");
    	return handler;
	}
    
    @Bean
    public CompanyUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
    	CompanyUsernamePasswordAuthenticationFilter filter = new CompanyUsernamePasswordAuthenticationFilter();
    	filter.setAuthenticationManager(authenticationManagerBean());
    	filter.setAuthenticationFailureHandler(failureHandler());
    	return filter;
    }
}