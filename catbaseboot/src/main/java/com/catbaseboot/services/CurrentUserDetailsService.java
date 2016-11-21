package com.catbaseboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.catbaseboot.model.CurrentUser;
import com.catbaseboot.model.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
	private final UserService userService;
	
	@Autowired
	public CurrentUserDetailsService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
        return new CurrentUser(user);
	}
	
}