package com.example.Sprint7Final.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
	
	private Long id;

	private String firstName;

	private String lastName;
	
	private String email;
	
	private String phone;
	
	private Boolean Active;
	
	private Boolean Admin;
	
	private String status;

	private TeamDto team;
	
	private CompanyDto company;

	private CredentialsDto credentials;

}
