package com.ramiz.issuemanagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ramiz.issuemanagement.Dto.IssueHistoryDto;
import com.ramiz.issuemanagement.entity.Issue;
import com.ramiz.issuemanagement.util.TPage;;

public interface IssueHistoryService {
	
	IssueHistoryDto save(IssueHistoryDto issueHistory);
	
	IssueHistoryDto getById(Long id);
	
	List<IssueHistoryDto> getByIssueIdAndOrderById(Long id);
	
	TPage<IssueHistoryDto> getAllPageable(Pageable pageable);
	
	boolean delete(IssueHistoryDto issueHistory);
	
	void addHistory(Long id, Issue issue);
}
