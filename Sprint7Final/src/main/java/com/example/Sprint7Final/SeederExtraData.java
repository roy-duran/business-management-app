package com.example.Sprint7Final;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Sprint7Final.entities.*;
import com.example.Sprint7Final.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisNoOpBindingRegistry;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j

/**
 * This is extra seeder data to populate the application with more entities and
 * more real world examples.
 * 
 * These are intended to be added to the Seeder.java and not stand alone
 *
 *
 */
public class SeederExtraData {

	private final UserRepository userRepository;
	private final CompanyRepository companyRepository;
	private final TeamRepository teamRepository;
	private final AnnouncementRepository announcementRepository;
	private final ProjectRepository projectRepository;

	public void run(String... args) throws Exception {
		// --Company 3 ---
		Company company3 = new Company();
		company3.setCompanyName("765 Productions");
		company3.setCompanyDescription("Specializing in Advanced Media Creations");

		companyRepository.saveAndFlush(company3);

		// Company 3 Teams

		Team staffTeam = new Team();
		staffTeam.setTeamName("Staff Team");
		staffTeam.setTeamCompany(company3);
		staffTeam.setTeamDescription("Manages day to day business and supports the Production team");

		Team productionTeam = new Team();
		productionTeam.setTeamName("Production Team");
		productionTeam.setTeamCompany(company3);
		productionTeam.setTeamDescription("Produces Promotions, Auditions, Festivals and Live Concert events");

		Team idolTeam = new Team();
		idolTeam.setTeamName("Idol Team");
		idolTeam.setTeamCompany(company3);
		idolTeam.setTeamDescription("Performance members for jobs assigned by Production team");

		teamRepository.saveAndFlush(staffTeam);
		teamRepository.saveAndFlush(productionTeam);

		// --- President User ---
		// Credentials
		Credentials PresidentCred = new Credentials();
		PresidentCred.setUsername("President");
		PresidentCred.setPassword("Password");

		User President = new User();
		President.setCredentials(PresidentCred);

		// Profile
		Profile PresidentProfile = new Profile();
		PresidentProfile.setFirstName("Junichirou");
		PresidentProfile.setLastName("Takagi");
		PresidentProfile.setEmail("president@765pro.com");
		PresidentProfile.setPhone("898-65-99876");
		President.setProfile(PresidentProfile);
		President.setCompany(company3);
		President.setTeam(staffTeam);
		President.setStatus("JOINED");

		// --- Secretary 1 User ---
		// Credentials
		Credentials Secretary1Cred = new Credentials();
		Secretary1Cred.setUsername("piyopiyo");
		Secretary1Cred.setPassword("ilovesake765");

		User Secretary1 = new User();
		Secretary1.setCredentials(Secretary1Cred);

		// Profile
		Profile Secretary1Profile = new Profile();
		Secretary1Profile.setFirstName("Kotori");
		Secretary1Profile.setLastName("Otonashi");
		Secretary1Profile.setEmail("kotori@765Pro.com");
		Secretary1Profile.setPhone("184-53-7777");
		Secretary1.setProfile(Secretary1Profile);
		Secretary1.setCompany(company3);
		Secretary1.setAdmin(true);
		Secretary1.setTeam(staffTeam);
		Secretary1.setStatus("JOINED");
		// Active
		Secretary1.setActive(true);

		// ---Secretary 2 User ---
		// Credentials
		Credentials Secretary2Cred = new Credentials();
		Secretary2Cred.setUsername("misamisa");
		Secretary2Cred.setPassword("imakeoutfits765");

		User Secretary2 = new User();
		Secretary2.setCredentials(Secretary2Cred);

		// Profile
		Profile Secretary2Profile = new Profile();
		Secretary2Profile.setFirstName("Misaki");
		Secretary2Profile.setLastName("Aoba");
		Secretary2Profile.setEmail("misaki@765Pro.com");
		Secretary2Profile.setPhone("567-67-1239");
		Secretary2.setProfile(Secretary2Profile);
		Secretary2.setAdmin(false);
		Secretary2.setCompany(company3);
		Secretary2.setTeam(staffTeam);
		Secretary2.setStatus("JOINED");
		Secretary2.setActive(true);

		// --- Producer 1 user ---
		// Credentials
		Credentials Producer1Cred = new Credentials();
		Producer1Cred.setUsername("producer");
		Producer1Cred.setPassword("765prouducer");

		User Producer1 = new User();
		Producer1.setCredentials(Producer1Cred);

		// Profile
		Profile Producer1Profile = new Profile();
		Producer1Profile.setFirstName("Kenji");
		Producer1Profile.setLastName("AKabane");
		Producer1Profile.setEmail("producer@765Pro.com");
		Producer1Profile.setPhone("765-76-7651");
		Producer1.setProfile(Producer1Profile);
		Producer1.setCompany(company3);
		Producer1.setAdmin(true);
		Producer1.setTeam(productionTeam);
		Producer1.setStatus("JOINED");

		// --- Producer 2 user ---
		// Credentials
		Credentials Producer2Cred = new Credentials();
		Producer2Cred.setUsername("producer");
		Producer2Cred.setPassword("765prouducer");

		User Producer2 = new User();
		Producer1.setCredentials(Producer2Cred);

		// Profile
		Profile Producer2Profile = new Profile();
		Producer2Profile.setFirstName("Yuutarou");
		Producer2Profile.setLastName("Takagi");
		Producer2Profile.setEmail("takagi@765Pro.com");
		Producer2Profile.setPhone("765-76-7651");
		Producer2.setProfile(Producer2Profile);
		Producer2.setCompany(company3);
		Producer2.setActive(false);
		Producer2.setAdmin(false);
		Producer2.setTeam(productionTeam);
		Producer2.setStatus("JOINED");

		// --- Idol 1 user ---
		// Credentials
		Credentials idol1Cred = new Credentials();
		idol1Cred.setUsername("harukaka");
		idol1Cred.setPassword("worldismine");

		User idol1 = new User();
		idol1.setCredentials(idol1Cred);

		// Profile
		Profile idol1Profile = new Profile();
		idol1Profile.setFirstName("Haruka");
		idol1Profile.setLastName("Amami");
		idol1Profile.setEmail("amami_h@765Pro.com");
		idol1Profile.setPhone("423-87-2356");
		idol1.setProfile(idol1Profile);
		idol1.setCompany(company3);
		idol1.setTeam(idolTeam);
		idol1.setStatus("JOINED");

		// --- Idol 2 user ---
		// Credentials
		Credentials idol2Cred = new Credentials();
		idol2Cred.setUsername("chihya");
		idol2Cred.setPassword("singingislife");

		User idol2 = new User();
		idol2.setCredentials(idol2Cred);

		// Profile
		Profile idol2Profile = new Profile();
		idol2Profile.setFirstName("Chihaya");
		idol2Profile.setLastName("Kisaragi");
		idol2Profile.setEmail("kisaragi_c@765Pro.com");
		idol2Profile.setPhone("678-23-1190");
		idol2.setProfile(idol2Profile);
		idol2.setCompany(company3);
		idol2.setTeam(idolTeam);
		idol2.setStatus("JOINED");

		// --- Idol 3 user ---
		// Credentials
		Credentials idol3Cred = new Credentials();
		idol3Cred.setUsername("mikinano");
		idol3Cred.setPassword("afuuafuu");

		User idol3 = new User();
		idol3.setCredentials(idol3Cred);

		// Profile
		Profile idol3Profile = new Profile();
		idol3Profile.setFirstName("Miki");
		idol3Profile.setLastName("Hoshii");
		idol3Profile.setEmail("hoshii_m@765Pro.com");
		idol3Profile.setPhone("961-43-0965");
		idol3.setProfile(idol3Profile);
		idol3.setCompany(company3);
		idol3.setTeam(idolTeam);
		idol3.setStatus("JOINED");

		// --- Idol 4 user ---
		// Credentials
		Credentials idol4Cred = new Credentials();
		idol4Cred.setUsername("yayoihifive");
		idol4Cred.setPassword("cookingisfun");

		User idol4 = new User();
		idol4.setCredentials(idol4Cred);

		// Profile
		Profile idol4Profile = new Profile();
		idol4Profile.setFirstName("Yayoi");
		idol4Profile.setLastName("Takatsuki");
		idol4Profile.setEmail("takatsuki_y@765Pro.com");
		idol4Profile.setPhone("762-14-9843");
		idol4.setProfile(idol4Profile);
		idol4.setCompany(company3);
		idol4.setTeam(idolTeam);
		idol4.setStatus("JOINED");

		// --- Idol 5 user ---
		// Credentials
		Credentials idol5Cred = new Credentials();
		idol5Cred.setUsername("moonhidesall");
		idol5Cred.setPassword("secrets961");

		User idol5 = new User();
		idol5.setCredentials(idol5Cred);

		// Profile
		Profile idol5Profile = new Profile();
		idol5Profile.setFirstName("Takane");
		idol5Profile.setLastName("Shijou");
		idol5Profile.setEmail("shijou_t@765Pro.com");
		idol5Profile.setPhone("961-39-2349");
		idol5.setProfile(idol5Profile);
		idol5.setCompany(company3);
		idol5.setTeam(idolTeam);
		idol5.setStatus("JOINED");

		userRepository.saveAndFlush(President);
		userRepository.saveAndFlush(Secretary1);
		userRepository.saveAndFlush(Secretary2);
		userRepository.saveAndFlush(Producer1);
		userRepository.saveAndFlush(Producer2);
		userRepository.saveAndFlush(idol1);
		userRepository.saveAndFlush(idol2);
		userRepository.saveAndFlush(idol3);
		userRepository.saveAndFlush(idol4);
		userRepository.saveAndFlush(idol5);

		// --Staff Team ---
		List<User> staffTeamUsers = new ArrayList<>();
		staffTeamUsers.add(President);
		staffTeamUsers.add(Secretary1);
		staffTeamUsers.add(Secretary2);

		Team staffTeamFromRepo = teamRepository.findByTeamName(staffTeam.getTeamName());
		staffTeamFromRepo.setUsersOnTheTeam(staffTeamUsers);
		teamRepository.saveAndFlush(staffTeamFromRepo);

		// --Production Team ---
		List<User> productionTeamUsers = new ArrayList<>();
		productionTeamUsers.add(Producer1);
		productionTeamUsers.add(Producer2);

		teamRepository.findByTeamName(productionTeam.getTeamName()).setUsersOnTheTeam(productionTeamUsers);

		// --Idol Team ---
		List<User> idolTeamUsers = new ArrayList<>();
		idolTeamUsers.add(idol1);
		idolTeamUsers.add(idol2);
		idolTeamUsers.add(idol3);
		idolTeamUsers.add(idol4);
		idolTeamUsers.add(idol5);

		teamRepository.findByTeamName(idolTeam.getTeamName()).setUsersOnTheTeam(idolTeamUsers);

		teamRepository.saveAndFlush(staffTeam);
		teamRepository.saveAndFlush(productionTeam);

		// projects
		Project office1Project = new Project();
		office1Project.setName("Office Renovation Planning");
		office1Project.setDescription(
				"Discuss plans for improving the office environment. Suggestions are a new air conditioner (old one is broken), a new refrigerator (old one is too small), and more seating for visitorsF");
		office1Project.setActive(true);
		office1Project.setTeamOnProject(staffTeam);
		projectRepository.saveAndFlush(office1Project);

		Project promo1Project = new Project();
		promo1Project.setName("Shopping Mall Promotion");
		promo1Project.setDescription(
				"Greet fans at the Atre Shopping Mall and pose for photos and shake fans' hands and sign autographs");
		promo1Project.setActive(true);
		promo1Project.setTeamOnProject(idolTeam);
		projectRepository.saveAndFlush(promo1Project);

		Project live1Project = new Project();
		live1Project.setName("Beach Live Concert");
		live1Project.setDescription(
				"A highly anticipated performance by Makuhari Beach next month on the 15th. Lots of fans are expected to attend, ~3,000");
		live1Project.setActive(true);
		live1Project.setTeamOnProject(idolTeam);
		projectRepository.saveAndFlush(live1Project);

		// Announcements
		Announcement staffAnnouncement1 = new Announcement();
		staffAnnouncement1.setAuthor(President);
		staffAnnouncement1.setCompanyMakingAnnouncement(company3);
		staffAnnouncement1.setTitle("Be Sure to Hydrate!");
		staffAnnouncement1.setMessage(
				"Due to the air conditioner currently being out of service, except it to be hot in the office! We'll have several fans running during the day, but please take care of yourselves and drink plenty of water and bring an extra water bottle when you come to work this week! Don't worry, we expect to get the air conditioner repaired or replaced on Saturday! Keep up the good work! ");
		staffAnnouncement1.setTimePosted(Timestamp.valueOf(LocalDateTime.now()));

		Announcement staffAnnouncement2 = new Announcement();
		staffAnnouncement2.setAuthor(President);
		staffAnnouncement2.setCompanyMakingAnnouncement(company3);
		staffAnnouncement2.setTitle("Beach Concert Next Month!");
		staffAnnouncement2.setMessage(
				"I hope everyone's training is going well for the beach concert next month! We expect a lot of people to show up, so put in that extra effort and reach out to your Producer, Otonashi-kun or Aoba-kun if you have anything you need to discuss!  ");
		staffAnnouncement2.setTimePosted(Timestamp.valueOf(LocalDateTime.now()));

		Announcement staffAnnouncement3 = new Announcement();
		staffAnnouncement3.setAuthor(Secretary1);
		staffAnnouncement3.setCompanyMakingAnnouncement(company3);
		staffAnnouncement3.setTitle("New Outfits Next Week!");
		staffAnnouncement3.setMessage(
				"We're expecting the our new outfits to be coming in by the middle of next week! I hope everyone is excited as I am!");
		staffAnnouncement3.setTimePosted(Timestamp.valueOf(LocalDateTime.now()));

		Announcement producerAnnouncement1 = new Announcement();
		producerAnnouncement1.setAuthor(Producer1);
		producerAnnouncement1.setCompanyMakingAnnouncement(company3);
		producerAnnouncement1.setTitle("Singing lessons tomorrow @ 14:00");
		producerAnnouncement1.setMessage(
				"Hey everyone. Just a reminder that singing lessons will be held at the usual studio across from the train station tomorrow afternoon at 14:00. If you have any trouble getting there on time, send me a text and I'll come pick you up!");
		producerAnnouncement1.setTimePosted(Timestamp.valueOf(LocalDateTime.now()));

		announcementRepository.saveAndFlush(staffAnnouncement1);
		announcementRepository.saveAndFlush(staffAnnouncement2);
		announcementRepository.saveAndFlush(staffAnnouncement3);
		announcementRepository.saveAndFlush(producerAnnouncement1);

	}

}
