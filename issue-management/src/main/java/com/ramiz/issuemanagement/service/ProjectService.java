package com.ramiz.issuemanagement.service;

import java.util.List;


import org.springframework.data.domain.Pageable;

import com.ramiz.issuemanagement.Dto.ProjectDto;
import com.ramiz.issuemanagement.entity.Project;

import com.ramiz.issuemanagement.util.TPage;


public interface ProjectService {
	
	ProjectDto save(ProjectDto project);
	
	ProjectDto getById(Long id);
	
	ProjectDto getByProjectCode(String projectCode);
	
	TPage<ProjectDto> getAllPageable(Pageable pageable);
	
	List<ProjectDto> getByProjectCodeContains(String projectCode);
	
	ProjectDto update(Long id, ProjectDto project);
	
	Boolean delete(Project project);

}
