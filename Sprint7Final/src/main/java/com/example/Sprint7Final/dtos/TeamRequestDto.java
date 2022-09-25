package com.example.Sprint7Final.dtos;


import com.example.Sprint7Final.entities.Company;
import com.example.Sprint7Final.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamRequestDto {

	private String teamName;

	private String teamDescription;

	private Long companyID;

	private List<String> usernames;

}