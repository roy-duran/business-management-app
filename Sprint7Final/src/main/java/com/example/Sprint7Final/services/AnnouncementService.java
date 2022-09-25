package com.example.Sprint7Final.services;

import java.util.List;

import com.example.Sprint7Final.dtos.AnnouncementRequestDto;
import com.example.Sprint7Final.dtos.AnnouncementResponseDto;

public interface AnnouncementService {

	List<AnnouncementResponseDto> getAllAnnouncements();

	List<AnnouncementResponseDto> getAllCompanyAnnouncements(Long companyId);

    AnnouncementResponseDto createAnnouncement(AnnouncementRequestDto announcementRequestDto);

    AnnouncementResponseDto deleteAnnouncement(Long announcementId);
}
