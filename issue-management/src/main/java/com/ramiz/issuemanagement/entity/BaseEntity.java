package com.ramiz.issuemanagement.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("serial")
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "created_by", length=50)
	private String createdBy;
	 
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(name = "updated_by", length = 100)
	private String updatedBy;
	
	@Column(name = "status")
	private Boolean status;
	
	

}
