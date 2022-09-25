package com.example.Sprint7Final.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.Sprint7Final.dtos.ProjectDto;
import com.example.Sprint7Final.dtos.ProjectRequestDto;
import com.example.Sprint7Final.dtos.ProjectResponseDto;
import com.example.Sprint7Final.entities.Project;
import com.example.Sprint7Final.entities.Team;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

	List<ProjectResponseDto> entitiesToResponseDtos(List<Project> projects);
	
	ProjectResponseDto entityToResponseDto(Project project);
	
	Project requestDtoToEntity(ProjectRequestDto projectRequestDto);

}
