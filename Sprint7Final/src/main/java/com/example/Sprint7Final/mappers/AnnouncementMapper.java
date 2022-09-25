package com.example.Sprint7Final.mappers;

import java.util.List;

import com.example.Sprint7Final.dtos.AnnouncementRequestDto;
import org.mapstruct.Mapper;

import com.example.Sprint7Final.dtos.AnnouncementResponseDto;
import com.example.Sprint7Final.entities.Announcement;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

	List<AnnouncementResponseDto> entitiesToDtos(List<Announcement> announcements);

    Announcement dtoToEntity(AnnouncementRequestDto announcementRequestDto);

    AnnouncementResponseDto entityToDto(Announcement announcement);
}
