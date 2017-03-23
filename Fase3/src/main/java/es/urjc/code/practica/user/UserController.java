package es.urjc.code.practica.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.code.practica.images.Image;
import es.urjc.code.practica.product.Product;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	
	
	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	public String UserAdd (Model model, User user, HttpServletRequest request) throws ServletException {
		
		String rol= "ROLE_USER";
		List<String> roles = new ArrayList<>(Arrays.asList(rol));
		user.setRoles(roles);

		
		userRepository.save(user);
		
		request.login(user.getName(), user.getPasswordHash());
		
	    return "index";
	}
	
	
	@RequestMapping(value = "/user/editDatos/", method = RequestMethod.POST)
	public String UserEdit (Model model, User user, HttpServletRequest request) throws ServletException {
		
		User usuario= userComponent.getLoggedUser();
		
		usuario.setName(user.getName());
		usuario.setSurnames(user.getSurnames());
		usuario.setEmail(user.getEmail());
		usuario.setAddress(user.getAddress());
		usuario.setZipcode(user.getZipcode());
		
		userRepository.save(usuario);
		if (userComponent.equals(usuario)){
			userComponent.setLoggedUser(usuario);
		}
	
	    return "index";
	}
	
	
	@RequestMapping(value = "/user/editPassword/", method = RequestMethod.POST)
	public String UserEditPass  (Model model, 
			@RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword) {
		
		User usuario = userComponent.getLoggedUser();
		
		
		System.out.println("password:"+password);
		System.out.println("Confirm password:"+confirmPassword);
		
		if (password.equals(confirmPassword)){
			usuario.setPasswordHash(password);
			userRepository.save(usuario);
		}
		return "user_updated";
	
	}
	
	
}
	
	

