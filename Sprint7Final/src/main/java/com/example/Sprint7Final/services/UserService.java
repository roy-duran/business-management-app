package com.example.Sprint7Final.services;

import java.util.List;

import com.example.Sprint7Final.dtos.CredentialsDto;
import com.example.Sprint7Final.dtos.UserRequestDto;
import com.example.Sprint7Final.dtos.UserResponseDto;

public interface UserService {

	UserResponseDto getUser(CredentialsDto credentialsDto);

	List<UserResponseDto> getUsersInCompany(Long companyId);

	UserResponseDto createUser(UserRequestDto userRequestDto);


    UserResponseDto editUser(UserRequestDto userRequestDto, Long userId);

    UserResponseDto deleteUser(Long userId);

}
