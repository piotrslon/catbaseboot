package com.catbaseboot.controllers;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catbaseboot.dao.CatDAO;
import com.catbaseboot.dto.FormDTO;
import com.catbaseboot.model.Cat;
import com.catbaseboot.services.CatService;


@Controller
public class CatsController {
	
	@Autowired
	private CatDAO catDao;
	
	@Autowired
	private CatService catService;
	
	@RequestMapping("")
	public String start (Model model) throws ParseException {
		
		if (catDao.getCats().size() > 2)
			model.addAttribute("name", catDao.getCats().get(2).getName());
		
		return "index";
	}
	
	
	@RequestMapping(value="/cats/add", method = RequestMethod.GET)
	public String addCats (@ModelAttribute("formDto") FormDTO formDto) {
		
		return "/cats/add";
	}
	
	@RequestMapping(value="/cats/add", method = RequestMethod.POST)
	public String handleAddCatForm (HttpServletRequest request, @ModelAttribute("formDto") @Valid FormDTO formDto, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/cats/add";
		} else {
			catService.addCat(formDto);
			catService.saveCat(formDto);
			return "redirect:/";
		}
	}
	
	/*@RequestMapping("/cats/add")
	public String addCats(HttpServletRequest request,  @ModelAttribute("formDto") @Valid FormDTO formDto, BindingResult result) {
		if (request.getMethod().equalsIgnoreCase("post") && !result.hasErrors()) {

			Cat cat = new Cat();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			try {
				cat.setBirthDate(sdf.parse(formDto.getBirthDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cat.setName(formDto.getName());
			cat.setGuardianName(formDto.getGuardianName());
			cat.setWeight(formDto.getWeight());
			
			catDao.addCat(cat);
			return "redirect:/start";
		}
		return "/cats/add";
	}*/
	
	@RequestMapping("/cats/show")
	public String showCats (HttpServletRequest request, Model model) throws ParseException {
		//public String widok(@RequestParam("a") String danaA, @RequestParam(value = "b", required = false, defaultValue = "0") Integer danaB)
		
		String catName = request.getParameter("catName");
		if (catName == null) catName = "";
	    String catWeight = request.getParameter("catWeight");
	    if (catWeight == null) catWeight = "catWeight";
	    String catBDate = request.getParameter("catBDate");
		if (catBDate == null) catBDate = "catBDate";
	    String catGName = request.getParameter("catGName");
		if (catGName == null) catGName = "catGName";

		if (catService.getAllCats().size() <2) catService.initCats();
		/*if (catDao.getCats().size() <2) catService.initCats();*/
		String empty = "Catbase is empty";
		List<Cat> catList = null;
		
		if (catService.getAllCats().size() != 0) {
			catList = catService.getAllCats();
			model.addAttribute("cats", catList);
			model.addAttribute("name", catList.get(0).getName());
		}
		else model.addAttribute("cats", empty);
		
		model.addAttribute("catName", catName);
		model.addAttribute("catWeight", catWeight);
		model.addAttribute("catBDate", catBDate);
		model.addAttribute("catGName", catGName);
		
		return "/cats/show";
	}
	
	@RequestMapping("/cats/show/{id}")
    public String catDetails(@PathVariable("id") Integer id, Model model) {
        
		Cat cat = catDao.getCats().get(id);
		
		model.addAttribute("catName", cat.getName());
		model.addAttribute("catWeight", cat.getWeight());
		model.addAttribute("catBDate", cat.getBirthDate());
		model.addAttribute("catGName", cat.getGuardianName());
		
		return "redirect:/cats/show";
    }
	
}
