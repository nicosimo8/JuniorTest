package com.juniortest.taskapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "task")
public class TaskModel {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(columnDefinition = "varchar(50)", nullable = false)
	private String description;

	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean status;
	
	public TaskModel() {
  }

	public TaskModel(Integer id, String description, Boolean status) {
    this.id = id;
    this.description = description;
    this.status = status;
  }

  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
