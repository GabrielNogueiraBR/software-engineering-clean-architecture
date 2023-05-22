package com.project.cleanarchitecture.domain.repository;

import com.project.cleanarchitecture.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {  
	
}