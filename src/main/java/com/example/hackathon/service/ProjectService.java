package com.example.hackathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hackathon.exception.ResourceNotFoundException;
import com.example.hackathon.model.Project;
import com.example.hackathon.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}
	
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	public Project getProject(Long id) {
		return projectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Project Not Found!"));
	}
}
