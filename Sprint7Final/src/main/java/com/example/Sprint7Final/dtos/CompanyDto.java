package com.example.Sprint7Final.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyDto {
	
	private Long id;
	
	private String companyName;
	
	private String companyDescription;

}