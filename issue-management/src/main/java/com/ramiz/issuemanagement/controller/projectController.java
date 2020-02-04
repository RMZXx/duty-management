 package com.ramiz.issuemanagement.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ramiz.issuemanagement.Dto.*;
import com.ramiz.issuemanagement.entity.Project;
import com.ramiz.issuemanagement.service.*;
import com.ramiz.issuemanagement.util.ApiPaths;
import com.ramiz.issuemanagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;;


@SuppressWarnings("unused")
@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value =ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
@Slf4j
@CrossOrigin
public class projectController {
	
	private final ProjectServiceImpl projectServiceImpl;
	
	public projectController(ProjectServiceImpl projectServiceImpl) {
		 this.projectServiceImpl=projectServiceImpl;
	}
	
	@GetMapping("/pagination")
	@ApiOperation(value ="Get By pagination Operation", response = ProjectDto.class)
	public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable) {
		
		TPage<ProjectDto> data = projectServiceImpl.getAllPageable(pageable);
		return ResponseEntity.ok(data);
		
	}
	
	@GetMapping()
	@ApiOperation(value ="Get All Operation", response = ProjectDto.class, responseContainer="List")
	public ResponseEntity<List<ProjectDto>> getAll() {
		List<ProjectDto> data = projectServiceImpl.getAll();
		return ResponseEntity.ok(data);
		
	}
	
	
	
	@GetMapping("/{id}")
	@ApiOperation(value ="Get By Id Operation", response = ProjectDto.class)
	public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id", required = true) Long id) {
		log.info("ProjectController-> GetByID ");
        log.debug("ProjectController-> GetByID -> PARAM:" + id);
		ProjectDto projectDto = projectServiceImpl.getById(id);
		return ResponseEntity.ok(projectDto);
		
	}
	
	@PostMapping
	@ApiOperation(value ="Create Operation",response = ProjectDto.class)
	public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto project){
	return	ResponseEntity.ok(projectServiceImpl.save(project));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value ="Update Operation",response = ProjectDto.class)
	public ResponseEntity<ProjectDto> updateProject(@PathVariable("id") Long id, @Valid @RequestBody ProjectDto project){
		
		return ResponseEntity.ok(projectServiceImpl.update(id,project));
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value ="Delete Operation",response = ProjectDto.class)
	public ResponseEntity<Boolean> delete(@PathVariable(value ="id", required = true) long id){
		
		return ResponseEntity.ok(projectServiceImpl.delete(id));
	}
	

}
