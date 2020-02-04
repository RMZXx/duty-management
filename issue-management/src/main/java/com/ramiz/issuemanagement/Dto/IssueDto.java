package com.ramiz.issuemanagement.Dto;

import java.util.Date;

import com.ramiz.issuemanagement.entity.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value ="Issue Data Transfer Object")
public class IssueDto {
	@ApiModelProperty(value ="Project ID")
	private Long id;
	@ApiModelProperty(required = true,value ="Description")
	private String description;
	@ApiModelProperty(required = true,value ="details")
	private String details;
	@ApiModelProperty(required = true,value ="Date")
	private Date date;
	@ApiModelProperty(required = true,value ="Issue Status")
	private IssueStatus issueStatus;
	@ApiModelProperty(required = true,value ="User Assignee")
	private  UserDto assignee;
	@ApiModelProperty(required = true,value ="Project")
	private ProjectDto project;
	private Long projectId;
	
	

}
