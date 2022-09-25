package com.example.Sprint7Final.dtos;


import com.example.Sprint7Final.entities.Company;
import com.example.Sprint7Final.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamResponseDto {

	private Long id;

	private String teamName;

	private String teamDescription;

	private CompanyDto teamCompany;

	private List<User> userList;
	
}