package com.catbaseboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catbaseboot.model.CatsToys;

@Controller
public class ToysController {
	
	//to nie ma byc osobna strona..
	/*@RequestMapping(value="/cats/toys", method = RequestMethod.GET)
	public String addToysPage (@ModelAttribute("toysFormDto") ToysFormDto toysFormDto) {
		
		return "/cats/toys";
	}*/
	/*@RequestMapping(value="/cats/toys/add", method = RequestMethod.GET)
	public String addCats (@ModelAttribute("catsToys") CatsToys catsToys) {
		
		return "/cats/show";
	}*/
	
	@RequestMapping(value="/cats/toys/add", method = RequestMethod.POST)
	public String addToy (@ModelAttribute("catsToys") CatsToys catsToys, RedirectAttributes redirectAttributes) {
		
		System.out.println("addToy");
		
		return "redirect:/cats/show";
	}
	
		//zabawki w osobnej liscie, i z tej listy dodawane do k?
		//czy kazdy k. a rozne zabawki dodawane indywidualnie?
		//za duzo na raz, testuj/komituj mniejsze kawalki
}