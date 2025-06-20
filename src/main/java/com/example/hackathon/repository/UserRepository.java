package com.example.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
