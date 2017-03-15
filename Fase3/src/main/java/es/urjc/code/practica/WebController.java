package es.urjc.code.practica;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.code.practica.images.Image;
import es.urjc.code.practica.images.ImageRepository;
import es.urjc.code.practica.product.Product;
import es.urjc.code.practica.product.ProductsRepository;
import es.urjc.code.practica.user.User;
import es.urjc.code.practica.user.UserComponent;
import es.urjc.code.practica.user.UserRepository;

@Controller
public class WebController {
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ProductsRepository repository;
	
	@Autowired
	private ImageRepository imageReporsitory;
	
	private static final String FILES_FOLDER = "files";
	
   @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
	   
	   if (userComponent.isLoggedUser()){
   		return home(model,request);
   		}
    	return "index";
    } 
    

    @RequestMapping("/login")
    public String login() {
    	return "login_form";
    }
    
    @RequestMapping("/loginerror")
    public String loginerror() {
    	return "login_form_error";
    }
    

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
    	
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	Principal p = request.getUserPrincipal();
    	User user = userRepository.findByName(p.getName());
    	
    	User user2 = userComponent.getLoggedUser();
    	
    	model.addAttribute("user", user2.getName());
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	
    	System.out.println("User: "+user);
    	System.out.println("User2: "+user2);
    	
    
	
    	return "index";
    }
    
    @RequestMapping("/admin")
    public String admin(Model model) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	
    	return "admin_product";
    }
    
    
    @RequestMapping("/adminadd")
    public String admin_add_product(Model model) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	
    	return "admin_add_product";
    }
    
    
    
    @RequestMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {


    	model.addAttribute("user", request.isUserInRole("USER"));
    	
    	Principal p = request.getUserPrincipal();
    	User user = userRepository.findByName(p.getName());
    	
    	
    	model.addAttribute("user", user.getName());
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	
    
    	if (request.isUserInRole("ADMIN")){
    		return admin(model);
    	}
    	
    	
    	return "profile";
    	
    }
    
    @RequestMapping("/usercart")
    public String usercart(Model model) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
	   
    	return "user_cart_shopping";
    } 
    
    
    @RequestMapping("/payment")
    public String payment1(Model model) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
	   
    	return "payment_gateway";
    } 
    
    
    @RequestMapping("/user_orders")
    public String user_orders(Model model) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
	   
    	return "user_orders";
    } 
    
    @RequestMapping("/user_orders_sum")
    public String user_orders_summary(Model model) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
	   
    	return "user_order_summary";
    } 
    
 
 		
 	//Añadir un producto como Administrador	

 		@RequestMapping(value="/admin/add/", method = RequestMethod.POST)
 		//@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
 		public String handleFileUpload(Model model, 
 				@RequestParam("imageTitle") String imageTitle,
 				@RequestParam("file") MultipartFile file, Product product) throws IllegalStateException, IOException {
 			
 			//TITULO DE LA IMAGEN
 			String imageName = imageTitle + ".jpg";
 			
 			//SI SE HA SELECCIONADO LA FOTO
 			if (!file.isEmpty()) {
 				
 					//Insertamos la imagen en la carpeta files
 					File filesFolder = new File(FILES_FOLDER);
 					if (!filesFolder.exists()) {
 						filesFolder.mkdirs();
 			
 					}
 					
 			File uploadedFile = new File(filesFolder.getAbsolutePath(), imageName);
 			file.transferTo(uploadedFile);
 				
 			Image image = new Image(imageTitle, filesFolder.getPath());
 			imageReporsitory.save(image); 	
 			product.setImage(filesFolder.getAbsolutePath());
 			}
 			
 			repository.save(product);
 			return "product_added";	
 			
 		}
 		
 		

}
    