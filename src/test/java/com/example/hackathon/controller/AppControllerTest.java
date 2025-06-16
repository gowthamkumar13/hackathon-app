package com.example.hackathon.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hackathon.model.Project;
import com.example.hackathon.model.Task;
import com.example.hackathon.model.User;
import com.example.hackathon.service.ProjectService;
import com.example.hackathon.service.TaskService;
import com.example.hackathon.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Collections;

@WebMvcTest(AppController.class)
class AppControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private ProjectService projectService;
	
	@MockBean
	private TaskService taskService;
	
	@Autowired
	private ObjectMapper objectMapper;

	
	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setName("Manoj");
		user.setEmail("manoj@gmail.com");
		
		Mockito.when(userService.saveUser(any(User.class))).thenReturn(user);
		
		mockMvc.perform(post("/api/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		User user = new User();
		user.setName("Manoj");
		user.setEmail("manoj@gmail.com");
		
		Mockito.when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));
		
		mockMvc.perform(get("/api/users/all")).andExpect(status().isOk());
	}
	
	@Test
	public void testSaveProject() throws Exception {
		Project project = new Project();
		project.setTitle("Backend");
		project.setDescription("Used for storing info in db");
		project.setStartDate(LocalDate.of(2025, 06, 05));
		project.setEndDate(LocalDate.of(2025, 06, 10));
		
		Mockito.when(projectService.saveProject(any(Project.class))).thenReturn(project);
		
		mockMvc.perform(post("/api/projects/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(project)))
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetAllProjects() throws Exception {
		Project project = new Project();
		project.setTitle("Backend");
		project.setDescription("Used for storing info in db");
		project.setStartDate(LocalDate.of(2025, 06, 05));
		project.setEndDate(LocalDate.of(2025, 06, 10));
		
		Mockito.when(projectService.getAllProjects()).thenReturn(Collections.singletonList(project));
		
		mockMvc.perform(get("/api/users/all")).andExpect(status().isOk());
	}
	
	@Test
	public void testAssignUser() throws Exception{
		Long projectId = 1L;
		
		Project oldProject = new Project();
		oldProject.setTitle("Backend");
		oldProject.setDescription("Used for storing info in db");
		oldProject.setStartDate(LocalDate.of(2025, 06, 05));
		oldProject.setEndDate(LocalDate.of(2025, 06, 10));
		
		Project newProject = new Project();
		newProject.setId(projectId);
		newProject.setTitle("Backend");
		newProject.setDescription("Used for storing infos");
		newProject.setStartDate(LocalDate.of(2025, 06, 10));
		newProject.setEndDate(LocalDate.of(2025, 06, 15));
		newProject.setUser(new User("Manoj","manoj@gmail.com"));
		
		Mockito.when(projectService.getProject(projectId)).thenReturn(oldProject);
		Mockito.when(projectService.saveProject(any(Project.class))).thenReturn(newProject);
		
		mockMvc.perform(put("/api/projects/{id}/users",projectId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newProject)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testSaveTask() throws Exception {
		Task task = new Task();
		task.setTitle("Learn Java");
		task.setDescription("Used for backend");
		task.setStatus("DONE");
		
		Mockito.when(taskService.saveTask(any(Task.class))).thenReturn(task);
		
		mockMvc.perform(post("/api/tasks/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(task)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllTasks() throws Exception{
		Task task = new Task();
		task.setTitle("Learn Java");
		task.setDescription("Used for backend");
		task.setStatus("DONE");
		
		Mockito.when(taskService.getAllTasks()).thenReturn(Collections.singletonList(task));
		
		mockMvc.perform(get("/api/tasks/all")).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateTask() throws Exception{
		Long taskId = 1L;

		User user = new User();
		user.setId(1L);
		user.setName("Manoj");
		user.setEmail("manoj@gmail.com");

		Project project = new Project();
		project.setId(1L);
		project.setTitle("Backend");
		project.setDescription("Used for storing info in db");
		project.setStartDate(LocalDate.of(2025, 6, 5));
		project.setEndDate(LocalDate.of(2025, 6, 10));
		
		Task updatedTask = new Task();
		updatedTask.setId(taskId);
		updatedTask.setTitle("Learn Spring");
		updatedTask.setDescription("Used for backend");
		updatedTask.setStatus("IN_PROGRESS");
		updatedTask.setUser(user);
		updatedTask.setProject(project);
		
		Mockito.when(taskService.updateTask(eq(taskId), any(Task.class))).thenReturn(updatedTask);

		mockMvc.perform(put("/api/tasks/update/{id}", taskId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedTask)))
		.andExpect(status().isOk());


	}
	
	

}
