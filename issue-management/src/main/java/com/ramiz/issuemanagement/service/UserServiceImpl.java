package com.ramiz.issuemanagement.service;



import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import com.ramiz.issuemanagement.Dto.RegistrationRequest;
import com.ramiz.issuemanagement.Dto.UserDto;
import com.ramiz.issuemanagement.entity.User;
import com.ramiz.issuemanagement.repository.UserRepository;
import com.ramiz.issuemanagement.util.TPage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	public final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.userRepository=userRepository;
		this.modelMapper=modelMapper;
	}
	
	
	@Override
	public UserDto save(UserDto user) {
		
		 User u = modelMapper.map(user, User.class);
	        u = userRepository.save(u);
	        user.setId(u.getId());
	        return user;
	}

	@Override
	public UserDto getById(Long Id) {
		
		User u= userRepository.getOne(Id);
		return modelMapper.map(u, UserDto.class);
	}
	
	 public List<UserDto> getAll() {
	        List<User> data = userRepository.findAll();
	        return Arrays.asList(modelMapper.map(data, UserDto[].class));
	    }
	
	

	 @Override
	    public TPage<UserDto> getAllPageAble(Pageable pageable) {
	        Page<User> data = userRepository.findAll(pageable);
	        TPage<UserDto> respnose = new TPage<UserDto>();
	        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), UserDto[].class)));
	        return respnose;
	    }


	

	@Override
	public UserDto getByuserName(String username) {
		User u = userRepository.findByUsername(username);
        return modelMapper.map(u, UserDto.class);
		
	}

	
	@Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setNameSurname(registrationRequest.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUsername(registrationRequest.getUsername());
            userRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            return Boolean.FALSE;
        }
    }

	


	


	
	
	

}
