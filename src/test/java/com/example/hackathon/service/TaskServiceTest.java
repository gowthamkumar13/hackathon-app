package com.example.hackathon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hackathon.model.Project;
import com.example.hackathon.model.Task;
import com.example.hackathon.model.User;
import com.example.hackathon.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private TaskService taskService;
	
	private Task taskOne;
	private Task taskTwo;
	
	@BeforeEach
	public void setup() {
		taskOne = new Task();
		taskOne.setTitle("Learn Java");
		taskOne.setDescription("Used for backend");
		taskOne.setStatus("DONE");
		
		taskTwo = new Task();
		taskTwo.setTitle("Learn Angular");
		taskTwo.setDescription("Used for frontend");
		taskTwo.setStatus("IN_PROGRESS");
	}
	
	@Test
	public void testSaveTask() {
		when(taskRepository.save(taskOne)).thenReturn(taskOne);
		
		Task task = taskService.saveTask(taskOne);
		
		assertThat(task).isNotNull();
		assertThat(task.getTitle()).isEqualTo("Learn Java");
		verify(taskRepository,times(1)).save(taskOne);
	}
	
	@Test
	public void testGetAllTasks() {
		when(taskRepository.findAll()).thenReturn(Arrays.asList(taskOne,taskTwo));
		
		List<Task> tasks = taskService.getAllTasks();
		
		assertThat(tasks).hasSize(2);
		verify(taskRepository,times(1)).findAll();
	}
	
	@Test
	public void testUpdateBooking() {
	    Long taskId = 1L;

	    User user = new User();
	    user.setName("Manoj");
	    user.setEmail("manoj@gmail.com");

	    Project project = new Project();
	    project.setTitle("Backend");
	    project.setDescription("Used for storing info in db");
	    project.setStartDate(LocalDate.of(2025, 6, 5));
	    project.setEndDate(LocalDate.of(2025, 6, 10));

	    Task taskOne = new Task();
	    taskOne.setId(taskId);
	    taskOne.setTitle("Old Title");
	    taskOne.setDescription("Old Description");
	    taskOne.setStatus("IN_PROGRESS");
	    taskOne.setUser(user);
	    taskOne.setProject(project);

	    Task updatedOne = new Task();
	    updatedOne.setId(taskId);
	    updatedOne.setTitle("Frontend");
	    updatedOne.setDescription("Used for angular");
	    updatedOne.setStatus("TODO");
	    updatedOne.setUser(user);
	    updatedOne.setProject(project);

	    when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskOne));
	    when(taskRepository.save(any(Task.class))).thenReturn(updatedOne);

	    Task checkUpdatedOne = taskService.updateTask(taskId, updatedOne);

	    assertThat(checkUpdatedOne).isNotNull();
	    assertThat(checkUpdatedOne.getTitle()).isEqualTo("Frontend");

	    // Use ArgumentCaptor to verify the saved object
	    ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
	    verify(taskRepository, times(1)).save(taskCaptor.capture());
	    Task savedTask = taskCaptor.getValue();

	    assertThat(savedTask.getTitle()).isEqualTo("Frontend");
	    assertThat(savedTask.getDescription()).isEqualTo("Used for angular");
	    assertThat(savedTask.getStatus()).isEqualTo("TODO");

	    verify(taskRepository, times(1)).findById(taskId);
	}

}
