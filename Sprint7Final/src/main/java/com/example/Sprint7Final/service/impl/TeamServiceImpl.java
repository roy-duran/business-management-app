package com.example.Sprint7Final.service.impl;

import com.example.Sprint7Final.dtos.*;
import com.example.Sprint7Final.entities.Company;
import com.example.Sprint7Final.entities.Team;
import com.example.Sprint7Final.entities.User;
import com.example.Sprint7Final.exceptions.BadRequestException;
import com.example.Sprint7Final.exceptions.NotFoundException;
import com.example.Sprint7Final.mappers.TeamMapper;
import com.example.Sprint7Final.repositories.CompanyRepository;
import com.example.Sprint7Final.repositories.TeamRepository;
import com.example.Sprint7Final.repositories.UserRepository;
import com.example.Sprint7Final.services.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

	private final TeamRepository teamRepository;
	private final TeamMapper teamMapper;

	private final CompanyRepository companyRepository;
	private final UserRepository userRepository;

	//Private Validation Methods
	@Override
	public List<TeamResponseDto> getTeams() {
		List<Team> teams = teamRepository.findAllByDeletedFalse();
		return teamMapper.entitiesToResponseDtos(teams);
	}

	@Override
	public TeamResponseDto createTeams(TeamRequestDto teamRequestDto) {
		if (teamRequestDto.getTeamName() == null || teamRequestDto.getTeamDescription() == null
				|| teamRequestDto.getCompanyID() == null || teamRequestDto.getUsernames().isEmpty()) {
			throw new BadRequestException("Team name, description, company, or list of users were left blank");
		}

		if (companyRepository.findByIdAndDeletedFalse(teamRequestDto.getCompanyID()).isEmpty()) {
			throw new BadRequestException("Company does not exist");
		}

		List<User> usersToAdd = new ArrayList<>();
		for (String username : teamRequestDto.getUsernames()) {
			Optional<User> user = userRepository.findByCredentialsUsername(username);
			if (user.isEmpty() || user.get().isDeleted()) throw new NotFoundException(username + "not found.");
			usersToAdd.add(user.get());
		}


		Team teamToSave = teamMapper.teamRequestDtoToEntity(teamRequestDto);
		teamToSave.setUsersOnTheTeam(usersToAdd);
		teamToSave.setTeamCompany(companyRepository.findByIdAndDeletedFalse(teamRequestDto.getCompanyID()).get());
		teamRepository.save(teamToSave);
		for (User user : usersToAdd) {
			user.setTeam(teamToSave);
		}
		userRepository.saveAllAndFlush(usersToAdd);
		return teamMapper.entityToResponseDto(teamRepository.save(teamToSave));
	}

	@Override
	public List<TeamResponseDto> getTeamsByCompanyId(Long companyId) {

		List<Team> teamsToReturn = new ArrayList<>();
		for (Team team : teamRepository.findAllByDeletedFalse()) {
			if (team.getTeamCompany().getId().equals(companyId)) {
				teamsToReturn.add(team);
			}
		}
		if (teamsToReturn.size() < 1) {
			throw new BadRequestException("Could not find teams with company id: " + companyId);
		}
		return teamMapper.entitiesToResponseDtos(teamsToReturn);
	}

	public List<Team> getTeamsByCompanyIdReturnEntities(Long companyId) {

		List<Team> teamsToReturn = new ArrayList<>();
		for (Team team : teamRepository.findAllByDeletedFalse()) {
			if (team.getTeamCompany().getId().equals(companyId)) {
				teamsToReturn.add(team);
			}
		}
		if (teamsToReturn.size() < 1) {
			throw new BadRequestException("Could not find teams with company id: " + companyId);
		}
		return teamsToReturn;
	}

	@Override
	public TeamResponseDto deleteTeam(Long teamId) {
		Optional<Team> optionalTeam = teamRepository.findByIdAndDeletedFalse(teamId);
		if (optionalTeam.isEmpty() || optionalTeam.get().isDeleted()) throw new NotFoundException("Team does not exist with ID: " + teamId);

		Team teamToDelete = optionalTeam.get();
		teamToDelete.setDeleted(true);

		return teamMapper.entityToResponseDto(teamRepository.saveAndFlush(teamToDelete));
	}

	@Override
	public TeamResponseDto getTeamById(Long teamId) {
		Optional<Team> optionalTeam = teamRepository.findByIdAndDeletedFalse(teamId);
		if(optionalTeam.isEmpty() || optionalTeam.get().isDeleted()) {
			throw new NotFoundException("Team does not exist or has been deleted");
		}
		return teamMapper.entityToResponseDto(optionalTeam.get());
	}

	@Override
	public TeamResponseDto editTeam(TeamRequestDto teamRequestDto, Long teamId) {
		Optional<Team> optionalTeam = teamRepository.findByIdAndDeletedFalse(teamId);
		if(optionalTeam.isEmpty() || optionalTeam.get().isDeleted()) {
			throw new NotFoundException("Team does not exist or has been deleted");
		}
		Team teamInDatabase = optionalTeam.get();

		if (teamRequestDto.getTeamName() != null) {
			teamInDatabase.setTeamName(teamRequestDto.getTeamName());
		}
		if (teamRequestDto.getTeamDescription() != null) {
			teamInDatabase.setTeamDescription(teamRequestDto.getTeamDescription());
		}
		if (teamRequestDto.getCompanyID() != null) {
			Optional<Company> optionalCompany = companyRepository.findByIdAndDeletedFalse(teamRequestDto.getCompanyID());
			if (optionalCompany.isEmpty()) {
				throw new NotFoundException("Company with company id: " + teamRequestDto.getCompanyID() + " does not exist.");
			}
			teamInDatabase.setTeamCompany(optionalCompany.get());
		}
		if (teamRequestDto.getUsernames() != null) {
			List<User> usersToAdd = new ArrayList<>();
			for (String username : teamRequestDto.getUsernames()) {
				Optional<User> user = userRepository.findByCredentialsUsername(username);
				if (user.isEmpty() || user.get().isDeleted()) throw new NotFoundException(username + "not found.");
				usersToAdd.add(user.get());
			}
			teamInDatabase.setUsersOnTheTeam(usersToAdd);
			teamRepository.save(teamInDatabase);
			for (User user : usersToAdd) {
				user.setTeam(teamInDatabase);
			}
			userRepository.saveAllAndFlush(usersToAdd);
		}
		return teamMapper.entityToResponseDto(teamRepository.save(teamInDatabase));
	}

	@Override
	public TeamsListDto getUniqueThingForDavidP(Long companyId) {
		List<User> usersInCompany = new ArrayList<>();
		for (User user : userRepository.findAllByDeletedFalse()) {
			if (user.getCompany().getId().equals(companyId)) {
				usersInCompany.add(user);
			}
		}
		TeamsListDto teamsListDto = new TeamsListDto();
		List<TeamAndMemberInfoAndProjectAmountDto> teamsToSet = new ArrayList<>();
		for (Team team : getTeamsByCompanyIdReturnEntities(companyId)) {
			List<MemberOfTeamDto> memberInfo = new ArrayList<>();
			for (User user : usersInCompany) {

				if (user.getTeam() != null && user.getTeam().getId().equals(team.getId())) {
					MemberOfTeamDto memberOfTeamDto = new MemberOfTeamDto();
					memberOfTeamDto.setUsername(user.getCredentials().getUsername());
					memberOfTeamDto.setFirstName(user.getProfile().getFirstName());
					memberOfTeamDto.setLastName(user.getProfile().getLastName());
					memberOfTeamDto.setId(user.getId());
					memberInfo.add(memberOfTeamDto);
				}
			}
			TeamAndMemberInfoAndProjectAmountDto teamAndMemberInfoAndProjectAmountDto = new TeamAndMemberInfoAndProjectAmountDto();
			teamAndMemberInfoAndProjectAmountDto.setId(team.getId());
			teamAndMemberInfoAndProjectAmountDto.setNumberOfProjects((long) team.getTeamProjects().size());
			teamAndMemberInfoAndProjectAmountDto.setMembers(memberInfo);
			teamsToSet.add(teamAndMemberInfoAndProjectAmountDto);
			// Requested by David P
			teamAndMemberInfoAndProjectAmountDto.setTeamName(team.getTeamName());

		}
		teamsListDto.setTeams(teamsToSet);
		return teamsListDto;
	}
}