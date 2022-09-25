package com.example.Sprint7Final.mappers;

import com.example.Sprint7Final.dtos.TeamRequestDto;
import com.example.Sprint7Final.dtos.TeamResponseDto;
import com.example.Sprint7Final.entities.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface TeamMapper {

	Team teamRequestDtoToEntity (TeamRequestDto teamRequestDto);

	TeamResponseDto entityToResponseDto(Team team);

	List<TeamResponseDto> entitiesToResponseDtos(List<Team> team);

}
