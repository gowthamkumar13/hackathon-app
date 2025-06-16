package com.example.hackathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hackathon.model.User;
import com.example.hackathon.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public boolean userExists(String name) {
		List<User> users = userRepository.findAll();
		for(User user : users) {
			if(name.equalsIgnoreCase(user.getName())) {
				return true;
			}
		}
		return false;
	}
}
