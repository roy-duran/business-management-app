package com.example.Sprint7Final.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ProjectResponseDto {
	
	private Long id;
	
	private String name;
	
	private String description;

	private Timestamp timePosted;
	
	private Boolean active;
	
	private TeamDto teamOnProject;
}