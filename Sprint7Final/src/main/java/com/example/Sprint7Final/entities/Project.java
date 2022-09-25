package com.example.Sprint7Final.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Project {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	@UpdateTimestamp
	private Timestamp timePosted;
	
	@Column(columnDefinition = "TEXT")
	private String description;

	private boolean deleted;

	private Boolean active;
	
	@ManyToOne
	@JoinColumn
	private Team teamOnProject;
	
}
