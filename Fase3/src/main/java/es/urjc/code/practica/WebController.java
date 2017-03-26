package es.urjc.code.practica;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.code.practica.images.Image;
import es.urjc.code.practica.images.ImageRepository;
import es.urjc.code.practica.product.Product;
import es.urjc.code.practica.product.ProductsRepository;
import es.urjc.code.practica.shoppingcart.Cart;
import es.urjc.code.practica.shoppingcart.OrderSummary;
import es.urjc.code.practica.shoppingcart.OrderSummaryRepository;
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
	private OrderSummaryRepository orderRepository;
	
	private int productadded =0;
	
	
	
	
   @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, ModelMap m,HttpSession session) {
	   
	   m.put("cart", new Cart());
	   if (userComponent.isLoggedUser()){
   		return home(model,request,session);
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
    public String home(Model model, HttpServletRequest request,HttpSession session) {
    	
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
    
    @RequestMapping("/adminall")
    public String adminAll (Model model, HttpServletRequest request){
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	return "admin";
    	
    	
    }
    
    @RequestMapping("/orderlist")
    public String orderlist (Model model, Pageable page, HttpServletRequest request){
    	
        	model.addAttribute("logueado", userComponent.isLoggedUser());
	    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
	    	
	    	List <OrderSummary> orders = orderRepository.findAll();
	    	
	    	System.out.println(orders);
	    	model.addAttribute("orders",orders);
	    	
	  /*  	//model.addAttribute("products", repository.findAll());
	    	Page<OrderSummary> orders = orderRepository.findAll(page);
	    	model.addAttribute("orders", orders);
	    	
	    	//Parte Paginación
	    	model.addAttribute("showNext",!orders.isLast());
	    	model.addAttribute("showPrev", !orders.isFirst());
	    	model.addAttribute("nextPage", orders.getNumber()+1);
	    	model.addAttribute("prevPage",orders.getNumber()-1);*/
	    	
	    	return "admin_order_list";
    	    
    }
    
    @RequestMapping("/admin")
    public String admin(Model model,Pageable page,HttpServletRequest request) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	//model.addAttribute("products", repository.findAll());
    	Page<Product> products = repository.findAll(page);
    	model.addAttribute("products", products);
    	
    	//Parte Paginación
    	model.addAttribute("showNext",!products.isLast());
    	model.addAttribute("showPrev", !products.isFirst());
    	model.addAttribute("nextPage", products.getNumber()+1);
    	model.addAttribute("prevPage",products.getNumber()-1);
    	
    	return "admin_product_list";
    }
    
    @RequestMapping("/adminadd")
    public String admin_add_product(Model model,HttpServletRequest request) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	return "admin_add_product";
    }
    
    
    
    
    @RequestMapping("/profile")
    public String profile(Model model, HttpServletRequest request, Pageable page) {

    	model.addAttribute("user", userComponent.getLoggedUser());
    	
    	Principal p = request.getUserPrincipal();
    	User user = userComponent.getLoggedUser();
    	
    	
    	model.addAttribute("name", user.getName());
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	
    
    	if (request.isUserInRole("ADMIN")){
    		return admin(model,page,request);
    	}
    	else{
    		return "profile";
    	}
    }
     
    @RequestMapping(value ="/admin/{id}")
    public String admin_add_product(Model model, @PathVariable long id , HttpServletRequest request) {
    
		model.addAttribute("logueado", userComponent.isLoggedUser());
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
		model.addAttribute("id", repository.findOne(id).getId());
		model.addAttribute("producto", repository.findOne(id));
		
    	
    	return "admin_edit_product";
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
    
 
    @RequestMapping("/create_account")
    public String createAccount() {
    	return "create_account";
    }	
    

    
		

}
    