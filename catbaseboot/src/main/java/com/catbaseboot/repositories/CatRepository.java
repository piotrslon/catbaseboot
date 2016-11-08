package com.catbaseboot.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.catbaseboot.model.Cat;

public interface CatRepository extends CrudRepository<Cat, Integer>{
	
	Page<Cat> findAll(Pageable pageable);
	
	List<Cat> findAll();
}