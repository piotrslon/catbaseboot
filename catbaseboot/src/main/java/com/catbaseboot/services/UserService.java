package com.catbaseboot.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.catbaseboot.model.User;
import com.catbaseboot.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/*@Transactional
    public User saveUser(User user) throws IOException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    } wlasnie dlatego dto, jest osobno od modelu*/
	
	public User saveUser(User user) throws IOException {
		String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}
	
	public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}
}