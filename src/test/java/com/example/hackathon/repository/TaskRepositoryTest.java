package com.example.hackathon.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.example.hackathon.model.Project;
import com.example.hackathon.model.Task;
import com.example.hackathon.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class TaskRepositoryTest {

	@Autowired
	private TaskRepository taskRepository;
	
	private Task task;
//	private Project project;
//	private User user;
	
	@BeforeEach
	public void setup() {
//		user = new User();
//		user.setName("Manoj");
//		user.setEmail("manoj@gmail.com");
//		
//		project = new Project();
//		project.setTitle("Backend");
//		project.setDescription("Used for storing info in db");
//		project.setStartDate(LocalDate.of(2025, 06, 05));
//		project.setEndDate(LocalDate.of(2025, 06, 10));
		
		task = new Task();
		task.setTitle("Learn Java");
		task.setDescription("Used for backend");
		task.setStatus("DONE");
//		task.setUser(user);
//		task.setProject(project);
		
	}
	
	@Test
	public void testSaveTask() {
		Task savedTask = taskRepository.save(task);
		
		assertThat(savedTask).isNotNull();
		assertThat(savedTask.getTitle()).isEqualTo("Learn Java");
	}
	
	@Test
	public void testGetAllTasks() {
		taskRepository.save(task);
		List<Task> tasks = taskRepository.findAll();
		
		assertThat(tasks).isNotNull();
		assertThat(tasks).hasSize(1);
		assertThat(tasks.get(0).getTitle()).isEqualTo("Learn Java");
	}
	
	
}
