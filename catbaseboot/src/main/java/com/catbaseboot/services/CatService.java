package com.catbaseboot.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catbaseboot.dto.FormDTO;
import com.catbaseboot.model.Cat;
import com.catbaseboot.repositories.CatRepository;

@Service
public class CatService {
	
	@Autowired
	private CatRepository catRepository;
	
	/*public Cart saveCart(Cart cart) {
    Cart dbCart = null;
    if(cart.getId()!=null){
        dbCart = cartRepository.findOne(cart.getId());
        dbCart.update(cart);
        cartSessionLogService.logUpdateCart(dbCart, cart);
    }else{
        dbCart = cart;
        cartSessionLogService.logNewCart(cart);
    }
    jesli to samo id, update
    inaczej tworzy nowy

    return cartRepository.save(dbCart);
	}*/
	
	public Cat saveCat(FormDTO formDto) {
		
		Cat dbCat = new Cat();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		try {
			dbCat.setBirthDate(sdf.parse(formDto.getBirthDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dbCat.setName(formDto.getName());
		dbCat.setGuardianName(formDto.getGuardianName());
		dbCat.setWeight(formDto.getWeight());
		
		System.out.println("Done, cats added to collection!");
		
		return catRepository.save(dbCat);
	}
	
	public List<Cat> getAllCats(){
    	return catRepository.findAll();
    }
	
	public Cat getCatById(Integer id) {
        return catRepository.findOne(id);
    }
	
	//to chyba powinno byc w serwisie..
	public void initCats() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		Cat cat1 = new Cat();
		cat1.setName("Tesla");
		cat1.setBirthDate(sdf.parse("12.04.2013"));
		cat1.setWeight((float) 6.0);
		cat1.setGuardianName("Ania");
		catRepository.save(cat1);
		Cat cat2 = new Cat();
		cat2.setName("Bonifacy");
		cat2.setBirthDate(sdf.parse("14.01.2011"));
		cat2.setWeight((float) 5.5);
		cat2.setGuardianName("Kuba");
		catRepository.save(cat2);
		
	}
}