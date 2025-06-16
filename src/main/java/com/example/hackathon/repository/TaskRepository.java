package com.example.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
