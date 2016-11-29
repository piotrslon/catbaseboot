package com.catbaseboot.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.catbaseboot.dto.RegisterFormDto;
import com.catbaseboot.services.UserService;

@Controller
public class UserController {
	
	//cos zeby w kazdej metodzie nie trzeba bylo pisac '/user'
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/users/new", method = RequestMethod.GET)
	public String addUsersPage (@ModelAttribute("registerFormDto") RegisterFormDto registerFormDto) {
		
		return "/users/new";
	}
	
	@RequestMapping(value="/users/new", method = RequestMethod.POST)
	public String addUsersPageForm (HttpServletRequest request, @ModelAttribute("registerFormDto") @Valid RegisterFormDto registerFormDto, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
		
		if (bindingResult.hasErrors()) {
			return "/users/new";
		} else {
			try {
				userService.saveUser(registerFormDto);
				redirectAttributes.addFlashAttribute("message", "Done, user is created!");
			} catch (DataIntegrityViolationException e) {
				redirectAttributes.addFlashAttribute("errorMessage", "User already exists");
				/*bindingResult.reject("errorMessage", "User already exists");*/
				return "redirect:/users/new";
			}
			
			return "redirect:/login";
		}
	}
	
	//edit/delete?
}