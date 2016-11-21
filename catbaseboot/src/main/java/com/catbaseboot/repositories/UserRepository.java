package com.catbaseboot.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.catbaseboot.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	Page<User> findAll(Pageable pageable);
	
	List<User> findAll();
	
	User findOneByEmail(String email);
	
	User findByUsername(String username);
}
