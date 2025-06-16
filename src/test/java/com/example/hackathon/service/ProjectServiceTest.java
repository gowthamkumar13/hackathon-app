package com.example.hackathon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hackathon.model.Project;
import com.example.hackathon.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
	@Mock
	private ProjectRepository projectRepository;
	
	@InjectMocks
	private ProjectService projectService;
	
	private Project projectOne;
	private Project projectTwo;
	
	@BeforeEach
	public void setup() {
		projectOne = new Project();
		projectOne.setTitle("Backend");
		projectOne.setDescription("Used for storing info in db");
		projectOne.setStartDate(LocalDate.of(2025, 06, 05));
		projectOne.setEndDate(LocalDate.of(2025, 06, 10));
		
		projectTwo = new Project();
		projectTwo.setTitle("Frontend");
		projectTwo.setDescription("Used for developing UI");
		projectTwo.setStartDate(LocalDate.of(2025, 06, 11));
		projectTwo.setEndDate(LocalDate.of(2025, 06, 18));
	}
	
	@Test
	public void testSaveProject() {
		when(projectRepository.save(projectOne)).thenReturn(projectOne);
		
		Project project = projectService.saveProject(projectOne);
		
		assertThat(project).isNotNull();
		assertThat(project.getTitle()).isEqualTo("Backend");
		verify(projectRepository,times(1)).save(projectOne);
	}
	
	@Test
	public void testGetAllProjects() {
		when(projectRepository.findAll()).thenReturn(Arrays.asList(projectOne,projectTwo));
		
		List<Project> projects = projectService.getAllProjects();
		
		assertThat(projects).hasSize(2);
		verify(projectRepository,times(1)).findAll();
	}
	
	@Test
	public void testGetProjectById() {
		when(projectRepository.findById(1L)).thenReturn(Optional.of(projectOne));
		
		Project retrievedProject = projectService.getProject(1L);
		
		assertThat(retrievedProject).isNotNull();
		assertThat(retrievedProject.getTitle()).isEqualTo("Backend");
		verify(projectRepository,times(1)).findById(1L);
	}
	
	
}
