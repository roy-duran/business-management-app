package com.example.Sprint7Final.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.Sprint7Final.dtos.CompanyRequestDto;
import com.example.Sprint7Final.entities.Company;
import com.example.Sprint7Final.entities.User;
import com.example.Sprint7Final.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import com.example.Sprint7Final.dtos.CompanyDto;
import com.example.Sprint7Final.entities.Company;
import com.example.Sprint7Final.exceptions.NotFoundException;
import com.example.Sprint7Final.mappers.CompanyMapper;
import com.example.Sprint7Final.repositories.CompanyRepository;
import com.example.Sprint7Final.services.CompanyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
	
	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;

	@Override
	public List<CompanyDto> getAllCompanies() {
		return companyMapper.entitiesToDtos(companyRepository.findAllByDeletedFalse());
	}

	@Override
	public CompanyDto getCompanyById(Long companyId) {
		Optional<Company> company = companyRepository.findByIdAndDeletedFalse(companyId);
		if (company.isEmpty()) {
			throw new NotFoundException("Company with id of " + companyId + " was not found.");
		}
		return companyMapper.entityToDto(company.get());

	}

	public CompanyDto createCompany(CompanyRequestDto companyRequestDto) {
		Company companyToAdd = companyMapper.dtoToEntity(companyRequestDto);
		if (companyToAdd.getCompanyName() == null || companyToAdd.getCompanyDescription() == null) {
			throw new BadRequestException("Missing Company name or description");
		}
		return companyMapper.entityToDto(companyRepository.saveAndFlush(companyToAdd));

	}

	@Override
	public CompanyDto deleteCompany(Long companyId) {
		Optional<Company> optionalCompany = companyRepository.findByIdAndDeletedFalse(companyId);
		if (optionalCompany.isEmpty() || optionalCompany.get().isDeleted()) throw new NotFoundException("Company does not exist with ID: " + companyId);

		Company companyToDelete = optionalCompany.get();
		companyToDelete.setDeleted(true);

		return companyMapper.entityToDto((companyRepository.saveAndFlush(companyToDelete)));
	}

	@Override
	public CompanyDto editCompany(CompanyDto companyDto, Long companyId) {

		Optional<Company> optionalCompany = companyRepository.findByIdAndDeletedFalse(companyId);
		if (optionalCompany.isEmpty()) {
			throw new NotFoundException("Company with id: " + companyId + " not found in database");
		}
		Company companyInDatabase = optionalCompany.get();
		if (companyDto.getCompanyName() != null) {
			companyInDatabase.setCompanyName(companyDto.getCompanyName());
		}
		if (companyDto.getCompanyDescription() != null) {
			companyInDatabase.setCompanyDescription(companyDto.getCompanyDescription());
		}
		return companyMapper.entityToDto(companyRepository.saveAndFlush(companyInDatabase));
	}
}
