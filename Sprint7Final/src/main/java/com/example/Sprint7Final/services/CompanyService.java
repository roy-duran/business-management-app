package com.example.Sprint7Final.services;

import java.util.List;

import com.example.Sprint7Final.dtos.CompanyDto;
import com.example.Sprint7Final.dtos.CompanyRequestDto;

public interface CompanyService {

	List<CompanyDto> getAllCompanies();


	CompanyDto getCompanyById(Long companyId);

  CompanyDto createCompany(CompanyRequestDto companyRequestDto);

	CompanyDto deleteCompany(Long companyId);

	CompanyDto editCompany(CompanyDto companyDto, Long companyId);
}
