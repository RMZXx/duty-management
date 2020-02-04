 package com.ramiz.issuemanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ramiz.issuemanagement.entity.User;

public interface UserRepository extends JpaRepository <User, Long> {
	User findByUsername(String username);
	
	
	

}
