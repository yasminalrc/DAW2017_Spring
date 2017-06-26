package es.urjc.code.practica.user;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private UserRepository userRepository;


	@PostConstruct
	private void initDatabase() {
		
		User user1 = new User("user1", "pig", "megustacomer@zanahorias.com", "pass","01/01/2000", 
				"En tu barrio Nº69 bajo A ", 69000, "Madrid", "Madrid", "España","612345678", "A12345678", "ROLE_USER");
		User user2 = new User("user2", "pig","megustacomer@zanahorias.com", "pass","01/01/2000", 
				"En tu barrio Nº69 bajo A ", 69000, "Madrid", "Madrid", "España","612345678", "A12345678" ,"ROLE_USER");
		User user4 = new User("yas", "pig", "megustacomer@zanahorias.com", "pass","01/01/2000", 
				"En tu barrio Nº69 bajo A ", 69000, "Madrid", "Madrid", "España", "612345678", "A12345678", "ROLE_USER");
		User user5 = new User("txus", "pig", "megustacomer@zanahorias.com", "pass","01/01/2000", 
				"En tu barrio Nº69 bajo A ", 69000, "Madrid", "Madrid", "España", "612345678", "A12345678", "ROLE_USER");
		User user6 = new User("thais", "pig", "megustacomer@zanahorias.com", "pass","01/01/2000", 
				"En tu barrio Nº69 bajo A ", 69000, "Madrid", "Madrid", "España", "612345678", "A12345678",  "ROLE_USER");
		User user7 = new User("dina", "pig", "megustacomer@zanahorias.com", "pass","01/01/2000", 
				"En tu barrio Nº69 bajo A ", 69000, "Madrid", "Madrid", "España", "612345678", "A12345678", "ROLE_USER");
		
		User user8 = new User("peppa", "pig", "megustacomer@zanahorias.com", "pass","01/01/2000", 
				"En tu barrio Nº69 bajo A ", 69000, "Madrid", "Madrid", "España", "612345678", "A12345678", "ROLE_USER");
		
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		userRepository.save(user4);
		userRepository.save(user5);
		userRepository.save(user6);
		userRepository.save(user7);
		
		userRepository.save(user8);
		
		userRepository.save(new User("user3", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN"));

	
	
	}
}
