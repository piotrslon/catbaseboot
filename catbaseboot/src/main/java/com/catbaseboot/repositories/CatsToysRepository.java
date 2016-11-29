package com.catbaseboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.catbaseboot.model.Cat;
import com.catbaseboot.model.CatsToys;

public interface CatsToysRepository extends CrudRepository<CatsToys, Integer>{
	
	
	List<CatsToys> findAll();
	
	List<CatsToys> findAllByCat(Cat cat);
	/*List<CatsToys> findAllByName(String name);*/
	
}
