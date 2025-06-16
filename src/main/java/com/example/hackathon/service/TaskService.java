package com.example.hackathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hackathon.exception.ResourceNotFoundException;
import com.example.hackathon.model.Task;
import com.example.hackathon.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}
	
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public Task updateTask(Long id,Task updatedTask) {
		Task oldTask = taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task not found!"));
		
		oldTask.setTitle(updatedTask.getTitle());
		oldTask.setDescription(updatedTask.getDescription());
		oldTask.setStatus(updatedTask.getStatus());
		oldTask.setUser(updatedTask.getUser());
		oldTask.setProject(updatedTask.getProject());
		
		taskRepository.save(oldTask);
		return oldTask;
	}
}
