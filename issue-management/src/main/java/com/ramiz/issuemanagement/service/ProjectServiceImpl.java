package com.ramiz.issuemanagement.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ramiz.issuemanagement.Dto.ProjectDto;
import com.ramiz.issuemanagement.entity.Project;
import com.ramiz.issuemanagement.entity.User;
import com.ramiz.issuemanagement.repository.ProjectRepository;
import com.ramiz.issuemanagement.repository.UserRepository;
import com.ramiz.issuemanagement.util.TPage;

@Service
public class ProjectServiceImpl implements ProjectService {

	public final ProjectRepository projectRepository;
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	
	public ProjectServiceImpl(ProjectRepository projectRepository,ModelMapper modelMapper,UserRepository userRepository) {
		this.projectRepository=projectRepository;
		this.modelMapper= modelMapper;
		this.userRepository=userRepository;
	}
	
	
	@Override
	public ProjectDto save(ProjectDto project) {
		
		Project projectCheck = projectRepository.getByProjectCode(project.getProjectCode());

        if(projectCheck != null)
            throw new IllegalArgumentException("Project Code Already Exist");
		
		Project p = modelMapper.map(project,Project.class);
		User user = userRepository.getOne(project.getManagerId());
		p.setManager(user);
		
		p = projectRepository.save(p);
		project.setId(p.getId());
		return project;
	}

	@Override
	public ProjectDto getById(Long id) {
		Project p =projectRepository.getOne(id);
		return modelMapper.map(p,ProjectDto.class);
		
	}

	
	

	@Override
	public Boolean delete(Project project) {
		projectRepository.delete(project);
		return Boolean.TRUE;
	}
	
	public Boolean delete(Long id) {
		projectRepository.deleteById(id);
		return true;
		
	}


	@Override
	public ProjectDto getByProjectCode(String projectCode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override 
	public TPage<ProjectDto> getAllPageable(Pageable pageable) {
		  
		Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDto> respnose = new TPage<ProjectDto>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDto[].class)));
        return respnose;
	}
	
	public List<ProjectDto> getAll() {
		  
		List<Project> data = projectRepository.findAll();
       
        return Arrays.asList(modelMapper.map(data, ProjectDto[].class)) ;
	}


	@Override
	public List<ProjectDto> getByProjectCodeContains(String projectCode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ProjectDto update(Long id, ProjectDto project) {
		Project projectDb = projectRepository.getOne(id);
		
		if(projectDb == null)
			throw new IllegalArgumentException("Project Does not  exist" + id);
		
		Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(),id);
        
        if (projectCheck != null)
            throw new IllegalArgumentException("Project Code Already Exist");
		
		projectDb.setProjectCode(project.getProjectCode());
		projectDb.setProjectName(project.getProjectName());
		projectRepository.save(projectDb);
		return modelMapper.map(projectDb,ProjectDto.class);
	}


	

	
	
	
}
