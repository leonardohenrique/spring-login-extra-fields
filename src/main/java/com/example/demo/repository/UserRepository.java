package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	User findByUsername(String username);
	
	User findByCompanyAndUsername(String company, String username);
	
}
