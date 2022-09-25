package com.example.Sprint7Final.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "user_table")
@Entity
@NoArgsConstructor
@Data
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private Credentials credentials;
	
	@Embedded
	private Profile profile;
	
	private boolean Active = true;
	
	private boolean Admin;
	
	private String status = "PENDING";

	private boolean deleted;
	
	@ManyToOne
	@JoinColumn
	private Team team;
	
	@ManyToOne
	private Company company;

	@OneToMany(mappedBy = "Author", cascade = CascadeType.ALL)

	private List<Announcement> userAnnouncements;
	
}

