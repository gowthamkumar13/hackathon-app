package com.example.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
