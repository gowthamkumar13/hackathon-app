package com.example.hackathon.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.example.hackathon.model.Project;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository projectRepository;
	
	private Project project;
	
	@BeforeEach
	public void setup() {
		project = new Project();
		project.setTitle("Backend");
		project.setDescription("Used for storing info in db");
		project.setStartDate(LocalDate.of(2025, 06, 05));
		project.setEndDate(LocalDate.of(2025, 06, 10));
	}
	
	@Test
	public void testSaveProject() {
		Project savedProject = projectRepository.save(project);
		
		assertThat(savedProject).isNotNull();
		assertThat(savedProject.getTitle()).isEqualTo("Backend");
	}
	
	@Test
	public void testGetAllProjects() {
		projectRepository.save(project);
		List<Project> projects = projectRepository.findAll();
		
		assertThat(projects).isNotNull();
		assertThat(projects).hasSize(1);
	}
	
	@Test
	public void testGetProjectById() {
		projectRepository.save(project);
		Optional<Project> retrievedProject = projectRepository.findById(project.getId());
		
		assertThat(retrievedProject).isPresent();
		assertThat(retrievedProject.get().getTitle()).isEqualTo("Backend");
	}
}
