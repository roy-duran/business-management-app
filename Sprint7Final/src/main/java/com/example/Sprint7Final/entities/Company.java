package com.example.Sprint7Final.entities;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Company {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String companyName;
	
	@Column
	private String companyDescription;

	private boolean deleted;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<User> usersInTheCompany;
	
	@OneToMany(mappedBy = "companyMakingAnnouncement", cascade = CascadeType.ALL)
	private List<Announcement> companyAnnouncements;
	
	@OneToMany(mappedBy = "teamCompany", cascade = CascadeType.ALL)
	private List<Team> companyTeams;

}