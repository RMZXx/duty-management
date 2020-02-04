package com.ramiz.issuemanagement.service;


import org.springframework.data.domain.Pageable;

import com.ramiz.issuemanagement.Dto.IssueDto;

import com.ramiz.issuemanagement.util.*;



public interface IssueService {
	
	IssueDto save(IssueDto issue);
	
	IssueDto getById(Long id);
	
	TPage<IssueDto> getAllPageable(Pageable page);
	
	
	
	Boolean delete(Long issue);

}
