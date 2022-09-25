package com.example.Sprint7Final.controllers;

import java.util.List;

import com.example.Sprint7Final.dtos.*;
import org.springframework.web.bind.annotation.*;

import com.example.Sprint7Final.services.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {
	
	private final CompanyService companyService;
	
	@GetMapping
	public List<CompanyDto> getAllCompanies() {
		return companyService.getAllCompanies();
	}
	
	@GetMapping("/{companyId}")
	public CompanyDto getCompanyById(@PathVariable Long companyId) {
		return companyService.getCompanyById(companyId);
	}

	@PostMapping("/create")
	public CompanyDto createCompany(@RequestBody CompanyRequestDto companyRequestDto) {
		return companyService.createCompany(companyRequestDto);
	}

	@DeleteMapping("/delete/{companyId}")
	public CompanyDto deleteCompany(@PathVariable Long companyId) {
		return companyService.deleteCompany(companyId);
	}

	@PutMapping("/{companyId}")
	public CompanyDto editCompany(@RequestBody CompanyDto companyDto, @PathVariable Long companyId) {
		return companyService.editCompany(companyDto, companyId);
	}
}
