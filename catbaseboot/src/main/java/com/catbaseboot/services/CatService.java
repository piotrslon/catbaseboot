package com.catbaseboot.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catbaseboot.dto.AddCatFormDto;
import com.catbaseboot.model.Cat;
import com.catbaseboot.model.CatsToys;
import com.catbaseboot.repositories.CatRepository;
import com.catbaseboot.repositories.CatsToysRepository;

@Service
public class CatService {
	
	@Autowired
	private CatRepository catRepository;
	
	@Autowired
	private CatsToysRepository catsToysRepository;
	
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
	
	public Cat saveCat(AddCatFormDto formDto) {
		
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
	
	public List<CatsToys> getAllCatsToys(){
		return catsToysRepository.findAll();
	}
	
	public List<CatsToys> findAllByCat (Cat cat) {
		return catsToysRepository.findAllByCat(cat);
	}
	
	/*public List<CatsToys> getAllCatsToysByName(String name){
		return catsToysRepository.findAllByName(name);
		//ByCatnane? or ByToysName?
	}
	
	public Page<Article> findAllByUser(User user, Pageable page) {
		Page<Article> articlePage = articleRepository.findByAuthor(user, page);
		markFavouriteArticles(articlePage.getContent(), user);
		return articlePage;
	}*/
	
	//to chyba powinno byc w serwisie, albo jakim bootLoaderze..
	public void initCats() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		Cat cat1 = new Cat();
		cat1.setName("Tesla");
		cat1.setBirthDate(sdf.parse("12.04.2013"));
		cat1.setWeight((float) 6.0);
		cat1.setGuardianName("Ania");
		CatsToys catstoys1 = new CatsToys();
		catstoys1.setToyName("Ball");
		catstoys1.setToyColor("Red");
		catstoys1.setToyPrice((float)12.5);
		/*catsToysRepository.save(catstoys1);*/
		cat1.addCatToys(catstoys1);
		catRepository.save(cat1);
		/*List<CatsToys> catstoyslist = findAllByCat(cat1);
		System.out.println("catstoyslist: "+catstoyslist);*/
		/*cat1.setCatstoys(catstoyslist);*/
		Cat cat2 = new Cat();
		cat2.setName("Bonifacy");
		cat2.setBirthDate(sdf.parse("14.01.2011"));
		cat2.setWeight((float) 5.5);
		cat2.setGuardianName("Kuba");
		catRepository.save(cat2);
		
	}
}