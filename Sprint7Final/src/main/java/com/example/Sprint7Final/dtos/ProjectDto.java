package com.example.Sprint7Final.dtos;

import com.example.Sprint7Final.entities.Team;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDto {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Boolean active;
	
	private Long teamOnProject;
}