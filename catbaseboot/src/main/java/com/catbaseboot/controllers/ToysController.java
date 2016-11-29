package com.catbaseboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catbaseboot.dto.RegisterFormDto;

@Controller
public class ToysController {
	
	//to nie ma byc osobna strona..
	/*@RequestMapping(value="/cats/toys", method = RequestMethod.GET)
	public String addToysPage (@ModelAttribute("toysFormDto") ToysFormDto toysFormDto) {
		
		return "/cats/toys";
	}*/
	
		//zabawki w osobnej liscie, i z tej listy dodawane do k?
		//czy kazdy k. a rozne zabawki dodawane indywidualnie?
		//za duzo na raz, testuj/komituj mniejsze kawalki
}