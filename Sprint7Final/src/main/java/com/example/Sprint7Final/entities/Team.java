package com.example.Sprint7Final.entities;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Team {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String teamName;
	
	@Column(nullable = false)
	private String teamDescription;

	private boolean deleted;

	@ManyToOne
	private Company teamCompany;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<User> usersOnTheTeam;

	@OneToMany(mappedBy = "teamOnProject", cascade = CascadeType.ALL)
	private List<Project> teamProjects;
}
