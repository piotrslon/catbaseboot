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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catbaseboot.dto.FormDTO;
import com.catbaseboot.model.Cat;
import com.catbaseboot.services.CatService;


@Controller
public class CatsController {
	
	@Autowired
	private CatService catService;
	
	@RequestMapping("")
	public String start (Model model) throws ParseException {
		
		return "index";
	}
	
	
	@RequestMapping(value="/cats/add", method = RequestMethod.GET)
	public String addCats (@ModelAttribute("formDto") FormDTO formDto) {
		
		return "/cats/add";
	}
	
	@RequestMapping(value="/cats/add", method = RequestMethod.POST)
	public String handleAddCatForm (HttpServletRequest request, @ModelAttribute("formDto") @Valid FormDTO formDto, 
									BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "/cats/add";
		} else {
			catService.saveCat(formDto);
			redirectAttributes.addFlashAttribute("message", "Done, cats added to collection!");
			return "redirect:/";
		}
	}
	
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
        
		Cat cat = catService.getCatById(id+1);
		
		model.addAttribute("catName", cat.getName());
		model.addAttribute("catWeight", cat.getWeight());
		model.addAttribute("catBDate", cat.getBirthDate());
		model.addAttribute("catGName", cat.getGuardianName());
		
		return "redirect:/cats/show";
    }
	
}
