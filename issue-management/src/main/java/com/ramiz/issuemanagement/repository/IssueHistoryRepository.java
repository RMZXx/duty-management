package com.ramiz.issuemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramiz.issuemanagement.entity.*;

public interface IssueHistoryRepository extends JpaRepository <IssueHistory, Long>{
	
	List<IssueHistory> getByIssueIdOrderById(Long id);

}
