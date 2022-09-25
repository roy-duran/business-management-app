package com.example.Sprint7Final.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {

	private Long id;
	
	private String username;
	
	private ProfileDto profile;
	
	private Boolean Active;
	
	private Boolean Admin;
	
	private String status;
	
	private TeamDto team;
	
	private CompanyDto company;
}
