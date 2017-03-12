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
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		userRepository.save(new User("user3", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN"));

	}

}
