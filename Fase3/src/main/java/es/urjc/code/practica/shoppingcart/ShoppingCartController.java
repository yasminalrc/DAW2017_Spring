package es.urjc.code.practica.shoppingcart;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import es.urjc.code.practica.user.User;
import es.urjc.code.practica.user.UserComponent;
import es.urjc.code.practica.user.UserRepository;

@Controller
@RequestMapping (value="/")
public class ShoppingCartController {
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ProductsRepository repository;
	
	private int productadded =0;
	
    
    @RequestMapping("product_add_contactlens")
    public String productContactLens(Model model, HttpServletRequest request) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	model.addAttribute("isLent");
 
		return "product_add_contactlens";
    	
    	
    }
     
    
    @RequestMapping("product_add_general")
    public String productGeneral (Model model, HttpServletRequest request){
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
     	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	return "product_add_general";
    }
    
    
    @RequestMapping(value="addcart", method=RequestMethod.POST)
    public String addCart (@ModelAttribute("cart") Cart c, HttpSession session){
    	
    	List <Cart> lst = (List<Cart>) session.getAttribute("cart");
    	
    	if (lst==null){
    		
    		lst= new ArrayList <>();
    		lst.add(c);	
    	} 
    	else {
    		boolean flag= false;
    		for (Cart cart: lst){
    			
    			if (cart.getId()== c.getId()){
    					cart.setQuantity(cart.getQuantity()+1);
    					flag=true;
    					break;
    			}
    		}
    	if (flag==false){
    			lst.add(c);
    	}
    	
    	session.setAttribute("cart", lst);
    	session.setAttribute("total",getTotal(lst));
    		
    	}
    	
		return null;
    	
    }
    
	
    public float getTotal (List <Cart> lst){
    	
    	float total=0;
    	for (Cart cart: lst){
    		total += (cart.getQuantity()*cart.getPrice());
    	}
    	
    	return total;
    }
    
    @RequestMapping (value="remove", method=RequestMethod.GET)
    public String remove (@RequestParam (value="id") int id, HttpSession session){
		
    	List <Cart> lst = (List<Cart>) session.getAttribute("cart");
    	
    	if (lst != null){
    		for (Cart cart: lst){
    			if (cart.getId()== id){
    				lst.remove(cart);
    				break;
    			}
    		}	
    	}
    	
    	session.setAttribute("cart", lst);
    	session.setAttribute("total", getTotal(lst));
    	
    	
    	return null;
    	
    	
    }
    
    
    @RequestMapping("usercart")
    public String usercart(Model model,HttpServletRequest request, HttpSession session) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	
    	//Utilizamos este método para cuando mapeamos desde la cabecera a usercart, sin utilizar el método post
    	//Redigirimos a la vista según ya se haya añadido un producto desde product_add_contactlens, o no
    	if (productadded>=1){
    		Boolean newproduct= true;
    		model.addAttribute("newproduct", newproduct);
    	} 
    	
    	return "user_cart_shopping";
    } 
    
    @RequestMapping(value="usercart_post",method = RequestMethod.POST)
    public String usercartpost(Model model,HttpServletRequest request, HttpSession session) {
    	
    	model.addAttribute("logueado", userComponent.isLoggedUser());
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
     
    	/*Boolean newproduct =true;
    	model.addAttribute("newproduct",newproduct); */ 
    	productadded=productadded+1;
  
    	return "product_add_contactlens_cart";
    } 
    
    
}    
    
   
 	
    