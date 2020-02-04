package com.ramiz.issuemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramiz.issuemanagement.entity.*;;

public interface IssueRepository extends JpaRepository <Issue, Long> {
	

}
