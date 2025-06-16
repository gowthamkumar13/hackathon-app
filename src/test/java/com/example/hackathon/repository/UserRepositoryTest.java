package com.example.hackathon.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.example.hackathon.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@BeforeEach
	public void setup() {
		user = new User();
		user.setName("Manoj");
		user.setEmail("manoj@gmail.com");
	}
	
	@Test
	public void testSaveUser() {
		User savedUser = userRepository.save(user);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getName()).isEqualTo("Manoj");
	}
	
	@Test
	public void testGetAllUsers() {
		userRepository.save(user);
		List<User> users = userRepository.findAll();
		
		assertThat(users).isNotNull();
		assertThat(users).hasSize(1);
		assertThat(users.get(0).getName()).isEqualTo("Manoj");
	}
	
}
