package com.example.Sprint7Final.mappers;

import java.util.List;

import com.example.Sprint7Final.dtos.CompanyRequestDto;
import org.mapstruct.Mapper;

import com.example.Sprint7Final.dtos.CompanyDto;
import com.example.Sprint7Final.entities.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	CompanyDto entityToDto(Company company);
	
	List<CompanyDto> entitiesToDtos(List<Company> companies);

	Company dtoToEntity(CompanyRequestDto companyRequestDto);
	
}