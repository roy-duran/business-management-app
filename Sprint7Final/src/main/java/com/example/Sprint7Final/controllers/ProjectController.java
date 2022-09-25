package com.example.Sprint7Final.controllers;

import java.util.List;

import com.example.Sprint7Final.services.ProjectService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sprint7Final.dtos.ProjectRequestDto;
import com.example.Sprint7Final.dtos.ProjectResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {
	private final ProjectService projectService;

	@GetMapping
	public List<ProjectResponseDto> getAllProjects() {
		return projectService.getAllProjects();
	}

	@GetMapping("/{id}")
	public ProjectResponseDto getProjectById(@PathVariable Long id) {
		return projectService.getProjectById(id);
	}

	@PostMapping
	public ProjectResponseDto createProject(@RequestBody ProjectRequestDto projectRequestDto) {
		return projectService.createProject(projectRequestDto);
	}

	@GetMapping("/team/{teamId}")
	public List<ProjectResponseDto> getProjectsByTeamId(@PathVariable Long teamId) {
		return projectService.getProjectsByTeamId(teamId);
	}

	@PutMapping("/{projectId}")
	public ProjectResponseDto updateProjectById(@RequestBody ProjectRequestDto projecRequesttDto, @PathVariable Long projectId) {
		return projectService.updateProjectById(projecRequesttDto, projectId);
	}
	
	@GetMapping("/company/{companyId}")
	public List<ProjectResponseDto> getProjectsByCompanyId(@PathVariable Long companyId) {
		return projectService.getProjectsByCompanyId(companyId);
	}

	@DeleteMapping("/delete/{projectId}")
	public ProjectResponseDto deleteProject(@PathVariable Long projectId) {
		return projectService.deleteProject(projectId);
	}

}