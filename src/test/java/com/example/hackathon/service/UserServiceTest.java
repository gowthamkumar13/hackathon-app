package com.example.hackathon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hackathon.model.User;
import com.example.hackathon.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	private User userOne;
	private User userTwo;
	
	@BeforeEach
	public void setup() {
		userOne = new User();
		userOne.setName("Manoj");
		userOne.setEmail("manoj@gmail.com");
		
		userTwo = new User();
		userTwo.setName("Kumar");
		userTwo.setEmail("kumar@gmail.com");
	}
	
	@Test
	public void testSaveUser() {
		when(userRepository.save(userOne)).thenReturn(userOne);
		
		User user = userService.saveUser(userOne);
		
		assertThat(user).isNotNull();
		assertThat(user.getName()).isEqualTo("Manoj");
		verify(userRepository,times(1)).save(userOne);
	}
	
	@Test
	public void testGetAllUsers() {
		when(userRepository.findAll()).thenReturn(Arrays.asList(userOne,userTwo));
		
		List<User> users = userRepository.findAll();
		
		assertThat(users).hasSize(2);
		verify(userRepository,times(1)).findAll();
	}
	

}
