package com.example.Sprint7Final.dtos;

import java.sql.Timestamp;

import com.example.Sprint7Final.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnnouncementResponseDto {
	
	private Timestamp timePosted;

	private String title;

	private String message;

	private CompanyDto companyMakingAnnouncement;

	private UserResponseDto Author;

}
