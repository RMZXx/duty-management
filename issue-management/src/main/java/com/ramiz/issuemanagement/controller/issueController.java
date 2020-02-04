package com.ramiz.issuemanagement.controller;



import java.util.Arrays;
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

import com.ramiz.issuemanagement.Dto.IssueDetailDto;
import com.ramiz.issuemanagement.Dto.IssueDto;
import com.ramiz.issuemanagement.Dto.IssueUpdateDto;
import com.ramiz.issuemanagement.entity.IssueStatus;
import com.ramiz.issuemanagement.service.IssueServiceImpl;
import com.ramiz.issuemanagement.util.ApiPaths;
import com.ramiz.issuemanagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
@CrossOrigin
public class issueController {
	
	private final IssueServiceImpl issueServiceImpl;
	
	public issueController(IssueServiceImpl issueServiceImpl) {
		 this.issueServiceImpl=issueServiceImpl;
	}
	
	
	@GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = IssueDto.class)
    public ResponseEntity<TPage<IssueDto>> getAllByPagination(Pageable pageable) {
        TPage<IssueDto> data = issueServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }
	
	@GetMapping("/{id}")
	@ApiOperation(value ="Get By Id Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto> getById(@PathVariable("id") Long id) {
		IssueDto projectDto = issueServiceImpl.getById(id);
		return ResponseEntity.ok(projectDto);
		
	}
	
	@GetMapping("/detail/{id}")
	@ApiOperation(value ="Get By Id Operation", response =IssueDto.class)
	public ResponseEntity<IssueDetailDto> getByIdWithDetails(@PathVariable(value="id", required =true) Long id){
		IssueDetailDto issuedetailDto =issueServiceImpl.getByIdWithDetails(id);
		return ResponseEntity.ok(issuedetailDto);
	}
	
	@GetMapping("/statuses")
	@ApiOperation(value ="Get All Issue Statuses Operation", response =String.class, responseContainer="List")
	public ResponseEntity<List<IssueStatus>> getAll(){
		
		return ResponseEntity.ok(Arrays.asList(IssueStatus.values()));
	}
	
	@PostMapping
	@ApiOperation(value ="Creat Operation", response = IssueDto.class)
	public ResponseEntity<IssueDto> createProject(@Valid @RequestBody IssueDto project){
	return	ResponseEntity.ok(issueServiceImpl.save(project));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value ="Update Operation", response = IssueDto.class)
	public ResponseEntity<IssueDetailDto> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody IssueUpdateDto issue){
		 
		return ResponseEntity.ok(issueServiceImpl.update(id, issue));
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value ="Delete Operation", response = IssueDto.class)
	public ResponseEntity<Boolean> delete(@PathVariable(value ="id", required = true) long id){
		
		return ResponseEntity.ok(issueServiceImpl.delete(id));
	}
	

}
