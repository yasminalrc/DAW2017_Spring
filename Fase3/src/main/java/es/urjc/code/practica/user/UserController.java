package es.urjc.code.practica.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	public String UserAdd (Model model, User user) {
		
		String rol= "ROLE_USER";
		List<String> roles = new ArrayList<>(Arrays.asList(rol));
		user.setRoles(roles);
		
		userRepository.save(user);
		
	    return "user_added";
	}
	
}
