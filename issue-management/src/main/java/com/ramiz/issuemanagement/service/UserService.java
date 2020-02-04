package com.ramiz.issuemanagement.service;


import org.springframework.data.domain.Pageable;

import com.ramiz.issuemanagement.Dto.UserDto;
import com.ramiz.issuemanagement.util.TPage;

public interface UserService {
	
	UserDto save(UserDto user);
	
	UserDto getById(Long Id);
	
	UserDto getByuserName(String username);
	
	
	TPage<UserDto> getAllPageAble(Pageable pageable);

}
