package com.example.Sprint7Final.repositories;

import java.util.List;
import java.util.Optional;

import com.example.Sprint7Final.dtos.CredentialsDto;
import com.example.Sprint7Final.entities.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Sprint7Final.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByDeletedFalse();

    Optional<User> findByIdAndDeletedFalse(Long id);

    Optional<User> findByCredentialsAndDeletedFalse(Credentials credentials);

    Optional<User> findByCredentialsUsername(String username);
}
