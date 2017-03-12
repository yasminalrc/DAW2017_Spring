package es.urjc.code.practica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import es.urjc.code.practica.user.User;
import es.urjc.code.practica.user.UserRepository;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private UserRepository userRepository;


	@PostConstruct
	private void initDatabase() {

		User user1 = new User("user1", "pass", "ROLE_USER");
		User user2 = new User("user2", "pass", "ROLE_USER");
		User user4 = new User("yasmina", "pass", "ROLE_USER");
		User user5 = new User("txus", "pass", "ROLE_USER");
		User user6 = new User("thais", "pass", "ROLE_USER");
		User user7 = new User("dina", "pass", "ROLE_USER");
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		userRepository.save(user4);
		userRepository.save(user5);
		userRepository.save(user6);
		userRepository.save(user7);
		
		userRepository.save(new User("user3", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN"));

	}

}
