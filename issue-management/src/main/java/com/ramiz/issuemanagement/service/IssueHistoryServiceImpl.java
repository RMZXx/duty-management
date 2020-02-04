package com.ramiz.issuemanagement.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ramiz.issuemanagement.Dto.IssueHistoryDto;
import com.ramiz.issuemanagement.entity.Issue;
import com.ramiz.issuemanagement.entity.IssueHistory;
import com.ramiz.issuemanagement.repository.IssueHistoryRepository;
import com.ramiz.issuemanagement.util.TPage;


@Service
public class IssueHistoryServiceImpl implements IssueHistoryService  {

	public final IssueHistoryRepository issueHistoryRepository;
	
	private final ModelMapper modelMapper;
	
	public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository, ModelMapper modelMapper) {
		this.issueHistoryRepository= issueHistoryRepository;
        this.modelMapper=modelMapper;
	}

	@Override
	public IssueHistoryDto save(IssueHistoryDto issueHistory) {
		
		IssueHistory ih = modelMapper.map(issueHistory, IssueHistory.class);
        ih = issueHistoryRepository.save(ih);
        issueHistory.setId(ih.getId());
        return issueHistory;
	}

	@Override
	public IssueHistoryDto getById(Long id) {
		
		IssueHistory ih = issueHistoryRepository.getOne(id);
        return modelMapper.map(ih, IssueHistoryDto.class);
	}

	@Override
	public TPage<IssueHistoryDto> getAllPageable(Pageable pageable) {
		
		Page<IssueHistory> data = issueHistoryRepository.findAll(pageable);
        TPage<IssueHistoryDto> respnose = new TPage<IssueHistoryDto>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueHistoryDto[].class)));
        return respnose;
		
	}

	@Override
	public boolean delete(IssueHistoryDto issueHistory) {
		
		issueHistoryRepository.deleteById(issueHistory.getId());
        return Boolean.TRUE;
	}

	@Override
	public List<IssueHistoryDto> getByIssueIdAndOrderById(Long id) {
		return Arrays.asList(modelMapper.map(issueHistoryRepository.getByIssueIdOrderById(id), IssueHistoryDto[].class));
	}

	@Override
	public void addHistory(Long id, Issue issueDb) {
		IssueHistory history=new IssueHistory();
        history.setIssue(issueDb);
        history.setAssignee(issueDb.getAssignee());
        history.setDate(issueDb.getDate());
        history.setDescription(issueDb.getDescription());
        history.setDetails(issueDb.getDetails());
        history.setIssuestatus(issueDb.getIssueStatus());
        issueHistoryRepository.save(history);
		
	}

	
	
	
	
	
	

}
