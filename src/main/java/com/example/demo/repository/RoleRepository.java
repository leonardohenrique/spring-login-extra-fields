package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

}
