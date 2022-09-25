package com.example.Sprint7Final.controllers;

import java.util.List;

import com.example.Sprint7Final.dtos.AnnouncementRequestDto;
import org.hibernate.cfg.CannotForceNonNullableException;
import org.springframework.web.bind.annotation.*;


import com.example.Sprint7Final.dtos.AnnouncementResponseDto;
import com.example.Sprint7Final.services.AnnouncementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcements")
@CrossOrigin
public class AnnouncementController {
	
	private final AnnouncementService announcementService;
	
	@GetMapping
	public List<AnnouncementResponseDto> getAllAnnouncements() {
		return announcementService.getAllAnnouncements();
	}
	
	@GetMapping("/{companyId}")
	public List<AnnouncementResponseDto> getAllCompanyAnnouncements(@PathVariable Long companyId)  {
		return announcementService.getAllCompanyAnnouncements(companyId);
	}

	@PostMapping("/create")
	public AnnouncementResponseDto createAnnouncement(@RequestBody AnnouncementRequestDto announcementRequestDto) {
		return announcementService.createAnnouncement(announcementRequestDto);
	}

	@DeleteMapping("/delete/{announcementId}")
	public AnnouncementResponseDto deleteAnnouncement(@PathVariable Long announcementId) {
		return announcementService.deleteAnnouncement(announcementId);
	}

}
