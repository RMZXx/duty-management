package com.ramiz.issuemanagement.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ramiz.issuemanagement.Dto.UserDto;
import com.ramiz.issuemanagement.service.UserServiceImpl;
import com.ramiz.issuemanagement.util.ApiPaths;
import com.ramiz.issuemanagement.util.TPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL, description = "Users APIs")
@CrossOrigin
public class UserController {
	
	private final UserServiceImpl userServiceImpl;
	
	public UserController(UserServiceImpl userServiceImpl) {
		 this.userServiceImpl=userServiceImpl;
	}
	
	
	@GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = UserDto.class)
    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable) {
        TPage<UserDto> data = userServiceImpl.getAllPageAble(pageable);
        return ResponseEntity.ok(data);
    }
	
	
	
	@GetMapping("/{id}")
	@ApiOperation(value ="Get By Id Operation", response = UserDto.class)
	public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
		UserDto userDto = userServiceImpl.getById(id);
		return ResponseEntity.ok(userDto);
		
	}
	
	@PostMapping
	@ApiOperation(value ="Creat Operation", response = UserDto.class)
	public ResponseEntity<UserDto> createProject(@Valid @RequestBody UserDto user){
	return	ResponseEntity.ok(userServiceImpl.save(user));
	}
	
	
	@GetMapping
	@ApiOperation(value="Get all By Operation", response = UserDto.class)
   public ResponseEntity<List<UserDto>> getAll(){
	   
	   List<UserDto> data = userServiceImpl.getAll();
	   return ResponseEntity.ok(data);
	   
   }
	

}
