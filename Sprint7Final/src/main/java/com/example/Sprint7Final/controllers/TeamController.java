package com.example.Sprint7Final.controllers;

import com.example.Sprint7Final.dtos.TeamRequestDto;
import com.example.Sprint7Final.dtos.TeamResponseDto;
import com.example.Sprint7Final.dtos.TeamAndMemberInfoAndProjectAmountDto;
import com.example.Sprint7Final.dtos.TeamsListDto;
import com.example.Sprint7Final.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
@CrossOrigin
public class TeamController {

	private final TeamService teamService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TeamResponseDto> getTeams() {
		return teamService.getTeams();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public TeamResponseDto createTeam(@RequestBody TeamRequestDto teamRequestDto) {
		return teamService.createTeams(teamRequestDto);
	}

	@GetMapping("/company/{companyId}")
	public List<TeamResponseDto> getTeamsByCompanyId(@PathVariable Long companyId) {
		return teamService.getTeamsByCompanyId(companyId);
	}

	@DeleteMapping("/delete/{teamId}")
	public TeamResponseDto deleteTeam(@PathVariable Long teamId) {
		return teamService.deleteTeam(teamId);
	}
	
	@GetMapping("/{teamId}")
	public TeamResponseDto getTeamById(@PathVariable Long teamId) {
		return teamService.getTeamById(teamId);
	}

	@PutMapping("/edit/{teamId}")
	public TeamResponseDto editTeam(@RequestBody TeamRequestDto teamRequestDto, @PathVariable Long teamId) {
		return teamService.editTeam(teamRequestDto, teamId);
	}

	@GetMapping("/teams/{companyId}")
	public TeamsListDto getUniqueThingForDavidP(@PathVariable Long companyId) {
		return teamService.getUniqueThingForDavidP(companyId);
	}

}