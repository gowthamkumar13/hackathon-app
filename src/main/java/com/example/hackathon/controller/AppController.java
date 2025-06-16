package com.example.hackathon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hackathon.exception.UserAlreadyExistsException;
import com.example.hackathon.model.Project;
import com.example.hackathon.model.Task;
import com.example.hackathon.model.User;
import com.example.hackathon.service.ProjectService;
import com.example.hackathon.service.TaskService;
import com.example.hackathon.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/users/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception{
		try {
			if(userService.userExists(user.getName())) {
				throw new UserAlreadyExistsException("User already exists!");
			}
			User result = userService.saveUser(user);
			return new ResponseEntity<User>(result,HttpStatus.OK);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GetMapping("/users/all")
	public ResponseEntity<List<User>> getAllUsers() throws Exception{
		try {
			List<User> users = userService.getAllUsers();
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@PostMapping("/projects/save")
	public ResponseEntity<Project> saveProject(@RequestBody Project project) throws Exception{
		try {
			Project result = projectService.saveProject(project);
			return new ResponseEntity<Project>(result,HttpStatus.OK);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GetMapping("/projects/all")
	public ResponseEntity<List<Project>> getAllProjects() throws Exception{
		try {
			List<Project> projects = projectService.getAllProjects();
			return new ResponseEntity<List<Project>>(projects,HttpStatus.OK);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@PutMapping("/projects/{id}/users")
	public ResponseEntity<Project> assignUserInProject(@PathVariable (value="id") Long id,@RequestBody Project project) throws Exception{
		try {
			Project oldProject = projectService.getProject(id);
			oldProject.setUser(project.getUser());
			projectService.saveProject(oldProject);
			return new ResponseEntity<Project>(oldProject,HttpStatus.OK);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@PostMapping("/tasks/save")
	public ResponseEntity<Task> saveTask(@RequestBody Task task) throws Exception{
		try {
			Task result = taskService.saveTask(task);
			return new ResponseEntity<Task>(result,HttpStatus.OK);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GetMapping("/tasks/all")
	public ResponseEntity<List<Task>> getAllTasks() throws Exception{
		try {
			List<Task> tasks = taskService.getAllTasks();
			return new ResponseEntity<List<Task>>(tasks,HttpStatus.OK);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@GetMapping("/tasks/project/{id}")
	public ResponseEntity<List<Task>> getTasksForProject(@PathVariable (value="id") Long id) throws Exception{
		try {
			List<Task> tasks = taskService.getAllTasks();
			List<Task> tasksPerProject = new ArrayList<Task>();
			
			for(Task task : tasks) {
				if(task.getProject().getId() == id) {
					tasksPerProject.add(task);
				}
			}
			return new ResponseEntity<List<Task>>(tasksPerProject,HttpStatus.OK);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@PutMapping("/tasks/update/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable (value="id") Long id,@RequestBody Task updatedTask) throws Exception{
		try {
			Task task = taskService.updateTask(id, updatedTask);
			return new ResponseEntity<Task>(task,HttpStatus.OK);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
