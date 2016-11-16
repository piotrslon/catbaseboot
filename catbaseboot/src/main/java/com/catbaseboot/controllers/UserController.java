package com.catbaseboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	//cos zeby w kazdej metodzie nie trzeba bylo pisac '/user'
	
	@RequestMapping("/users/new")
	public String UserPage () {
		
		return "login"; //if ok
	}
	
	//edit/delete?
}