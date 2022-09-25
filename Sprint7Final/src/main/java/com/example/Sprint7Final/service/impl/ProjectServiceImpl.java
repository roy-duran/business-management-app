package com.example.Sprint7Final.service.impl;

import com.example.Sprint7Final.dtos.ProjectDto;
import com.example.Sprint7Final.dtos.ProjectRequestDto;
import com.example.Sprint7Final.dtos.ProjectResponseDto;
import com.example.Sprint7Final.entities.Project;
import com.example.Sprint7Final.entities.Team;
import com.example.Sprint7Final.entities.User;
import com.example.Sprint7Final.exceptions.BadRequestException;
import com.example.Sprint7Final.exceptions.NotFoundException;
import com.example.Sprint7Final.mappers.ProjectMapper;
import com.example.Sprint7Final.repositories.CompanyRepository;
import com.example.Sprint7Final.repositories.ProjectRepository;
import com.example.Sprint7Final.repositories.TeamRepository;
import com.example.Sprint7Final.services.ProjectService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final ProjectMapper projectMapper;
	private final TeamRepository teamRepository;

	@Override
	public List<ProjectResponseDto> getAllProjects() {
		return projectMapper.entitiesToResponseDtos(projectRepository.findAll());
	}

	@Override
	public ProjectResponseDto getProjectById(Long id) {
		Optional<Project> projectToReturn = projectRepository.findByIdAndDeletedFalse(id);
		if(projectToReturn.isEmpty() || projectToReturn.get().isDeleted()) {
			throw new NotFoundException("Project does not exist or has been deleted");
		}
		return projectMapper.entityToResponseDto(projectToReturn.get());
	}

	@Override
	public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto) {
		Project projectToSave = projectMapper.requestDtoToEntity(projectRequestDto);
		if (projectRequestDto.getTeamId() == null) {
			log.warn("Attempt to create Project without a team assigned");
		} else {
			Optional<Team> optionalTeam = teamRepository.findById(projectRequestDto.getTeamId());
			if (optionalTeam.isEmpty()) {
				throw new NotFoundException("Team with id: " + projectRequestDto.getTeamId() + " not found in database.");
			}
			projectToSave.setTeamOnProject(optionalTeam.get());
		}

		projectToSave.setActive(projectRequestDto.getActive());
		return projectMapper.entityToResponseDto(projectRepository.saveAndFlush(projectToSave));
	}

	@Override
	public ProjectResponseDto updateProjectById(ProjectRequestDto projectRequestDto, Long projectId) {
		
		Optional<Project> optionalProject = projectRepository.findByIdAndDeletedFalse(projectId);
		if (optionalProject.isEmpty()) {
			throw new NotFoundException("Project not found with id: " + projectId);
		}
		Project projectToUpdate = optionalProject.get();
		if (projectRequestDto.getName() != null) {
			projectToUpdate.setName(projectRequestDto.getName());
		}
		if (projectRequestDto.getDescription() != null) {
			projectToUpdate.setDescription(projectRequestDto.getDescription());
		}
		if (projectRequestDto.getActive() != null) {
			projectToUpdate.setActive(projectRequestDto.getActive());
		}
		if (projectRequestDto.getTeamId() != null) {

			Optional<Team> optionalTeam = teamRepository.findByIdAndDeletedFalse(projectRequestDto.getTeamId());
			if (optionalTeam.isEmpty()) {
				throw new NotFoundException("Could not find team with id: " + projectRequestDto.getTeamId());
			} else {
				projectToUpdate.setTeamOnProject(optionalTeam.get());
			}
		}
		return projectMapper.entityToResponseDto(projectRepository.save(projectToUpdate));
	}

	@Override
	public List<ProjectResponseDto> getProjectsByTeamId(Long teamId) {
		Optional<Team> optionalTeam = teamRepository.findByIdAndDeletedFalse(teamId);
		if (optionalTeam.isEmpty()) {
			throw new NotFoundException("Team not found or no projects found with id: " + teamId);
		}
		return projectMapper.entitiesToResponseDtos(teamRepository.findByIdAndDeletedFalse(teamId).get().getTeamProjects());
	}

	@Override
	public List<ProjectResponseDto> getProjectsByCompanyId(Long companyId) {
		List<Project> projects = projectRepository.findAll();
		List<Project> projectsToReturn = new ArrayList<>();
		for(Project project : projects) {
			if(project.getTeamOnProject().getTeamCompany().getId().equals(companyId)) {
				projectsToReturn.add(project);
			}
		}
		if (projectsToReturn.size() < 1) {
			throw new BadRequestException("No projects found with company id: " + companyId);
		}
		return projectMapper.entitiesToResponseDtos(projectsToReturn);
	}

	@Override
	public ProjectResponseDto deleteProject(Long projectId) {
		Optional<Project> optionalProject = projectRepository.findByIdAndDeletedFalse(projectId);
		if (optionalProject.isEmpty() || optionalProject.get().isDeleted()) throw new NotFoundException("Project does not exist with ID: " + projectId);

		Project projectToDelete = optionalProject.get();
		projectToDelete.setDeleted(true);

		return projectMapper.entityToResponseDto((projectRepository.saveAndFlush(projectToDelete)));
	}
}
