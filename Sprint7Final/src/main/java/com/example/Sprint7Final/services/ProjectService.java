package com.example.Sprint7Final.services;

import java.util.List;

import com.example.Sprint7Final.dtos.ProjectDto;
import com.example.Sprint7Final.dtos.ProjectRequestDto;
import com.example.Sprint7Final.dtos.ProjectResponseDto;

public interface ProjectService {

	List<ProjectResponseDto> getAllProjects();

	ProjectResponseDto getProjectById(Long id);

	ProjectResponseDto createProject(ProjectRequestDto projectRequestDto);

	ProjectResponseDto updateProjectById(ProjectRequestDto projectRequestDto, Long projectId);

	List<ProjectResponseDto> getProjectsByTeamId(Long teamId);

	List<ProjectResponseDto> getProjectsByCompanyId(Long companyId);

	ProjectResponseDto deleteProject(Long projectId);
}
