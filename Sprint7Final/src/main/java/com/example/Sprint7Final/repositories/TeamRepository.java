package com.example.Sprint7Final.repositories;

import com.example.Sprint7Final.entities.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	Team findByTeamName(String name);

	List<Team> findAllByDeletedFalse();

	Optional<Team> findByIdAndDeletedFalse(Long id);

}