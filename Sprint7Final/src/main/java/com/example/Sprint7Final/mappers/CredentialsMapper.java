package com.example.Sprint7Final.mappers;

import com.example.Sprint7Final.dtos.CredentialsDto;
import com.example.Sprint7Final.entities.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    Credentials dtoToEntity(CredentialsDto credentialsDto);
}
