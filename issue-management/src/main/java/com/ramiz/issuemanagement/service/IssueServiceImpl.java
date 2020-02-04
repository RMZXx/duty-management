package com.ramiz.issuemanagement.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramiz.issuemanagement.Dto.IssueDetailDto;
import com.ramiz.issuemanagement.Dto.IssueDto;
import com.ramiz.issuemanagement.Dto.IssueHistoryDto;
import com.ramiz.issuemanagement.Dto.IssueUpdateDto;
import com.ramiz.issuemanagement.entity.Issue;
import com.ramiz.issuemanagement.entity.IssueStatus;
import com.ramiz.issuemanagement.entity.User;
import com.ramiz.issuemanagement.repository.IssueRepository;
import com.ramiz.issuemanagement.repository.ProjectRepository;
import com.ramiz.issuemanagement.repository.UserRepository;
import com.ramiz.issuemanagement.service.IssueHistoryService;

import com.ramiz.issuemanagement.util.TPage;

@Service
public class IssueServiceImpl implements IssueService {

	private final IssueRepository issueRepository;
	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final IssueHistoryService issueHistoryService;
	
	public IssueServiceImpl(IssueRepository issueRepository,ModelMapper modelMapper,
			IssueHistoryService issueHistoryService,UserRepository userRepository,ProjectRepository projectRepository
			 ) {
		this.issueRepository = issueRepository;
		this.modelMapper=modelMapper;
		this.issueHistoryService=issueHistoryService;
		this.userRepository=userRepository;
		this.projectRepository=projectRepository;
	}

	@Override
	public IssueDto save(IssueDto issue) {
		
		
		issue.setDate(new Date());
		issue.setIssueStatus(IssueStatus.OPEN);
		
	     Issue IssueEntity =  modelMapper.map(issue,Issue.class);
	    
	     IssueEntity.setProject(projectRepository.getOne(issue.getProjectId()));
	     
	     IssueEntity =issueRepository.save(IssueEntity);
	     
	     issue.setId(IssueEntity.getId());
	     return issue;
	     
	}
	
	@Transactional
	public IssueDetailDto update(Long id, IssueUpdateDto issue) {
		Issue issueDb = issueRepository.getOne(id);
		User user= userRepository.getOne(issue.getAssignee_id());
		issueHistoryService.addHistory(id, issueDb);
		
		
		issueDb.setAssignee(user);
        issueDb.setDate(issue.getDate());
        issueDb.setDescription(issue.getDescription());
        issueDb.setDetails(issue.getDetails());
        issueDb.setIssueStatus(issue.getIssueStatus());
        issueDb.setProject(projectRepository.getOne(issue.getProject_id()));
        issueRepository.save(issueDb);
        return getByIdWithDetails(id);
		
	}

	@Override
	public IssueDto getById(Long id) {
		Issue issue = issueRepository.getOne(id);
        return modelMapper.map(issue, IssueDto.class);
	}
	
	public IssueDetailDto getByIdWithDetails(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto detailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueIdAndOrderById(issue.getId());
        detailDto.setIssueHistories(issueHistoryDtos);
        return detailDto;
    }

	@Override
	public TPage<IssueDto> getAllPageable(Pageable pageable) {
		
	   Page<Issue> data = issueRepository.findAll(pageable);
	   TPage<IssueDto> response=new TPage<IssueDto>();
	   response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueDto[].class)));
		return response;
	   
		
	}
	
	public List<IssueDto> getAll(){
		List<Issue> data = issueRepository.findAll();
		return Arrays.asList(modelMapper.map(data, IssueDto[].class));
	}

	@Override
	public Boolean delete(Long issueId) {
		issueRepository.deleteById(issueId);
		return true;
	}


	
	
	
	
}
