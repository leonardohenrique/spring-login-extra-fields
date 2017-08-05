package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username);
	}
	
	public UserDetails loadUserByCompanyAndUsername(String company, String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByCompanyAndUsername(company, username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return user;
		
	}

}
