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
@RequestMapping(value = "/")
public class ShoppingCartController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private ProductsRepository repository;

	@Autowired
	private OrdersUserRepository ordersrepository;

	private int productadded = 0;

	@RequestMapping("product_add_contactlens")
	public String productContactLens(Model model, HttpServletRequest request) {

		model.addAttribute("logueado", userComponent.isLoggedUser());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));

		model.addAttribute("isLent");

		// Obtenemos nuestro producto del repositorio y lo pasamos al modelo
		Product product = repository.findByBrand("Acuvue");
		model.addAttribute("product_acuvue", product);

		return "product_add_contactlens";

	}

	@RequestMapping("product_add_general")
	public String productGeneral(Model model, HttpServletRequest request) {

		model.addAttribute("logueado", userComponent.isLoggedUser());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));

		// Obtenemos nuestro producto del repositorio y lo pasamos al modelo
		
		// Obtenemos nuestro producto del repositorio y lo pasamos al modelo
		Product product = repository.findByBrand("rayban");
		model.addAttribute("product_rayban", product);
		
		model.addAttribute ("pepe","pepe");
		
		System.out.println(product);

		return "product_add_general";
	}

	/*
	 * @RequestMapping(value="addcart", method=RequestMethod.POST) public String
	 * addCart (@ModelAttribute("cart") CartContactLens c, HttpSession session){
	 * 
	 * List <CartContactLens> lst = (List<CartContactLens>)
	 * session.getAttribute("cart");
	 * 
	 * if (lst==null){
	 * 
	 * lst= new ArrayList <>(); lst.add(c); } else { boolean flag= false; for
	 * (CartContactLens cart: lst){
	 * 
	 * if (cart.getId()== c.getId()){ cart.setQuantity(cart.getQuantity()+1);
	 * flag=true; break; } } if (flag==false){ lst.add(c); }
	 * 
	 * session.setAttribute("cart", lst);
	 * session.setAttribute("total",getTotal(lst));
	 * 
	 * }
	 * 
	 * return "user_cart_shopping";
	 * 
	 * }
	 * 
	 * 
	 * public float getTotal (List <CartContactLens> lst){
	 * 
	 * float total=0; for (CartContactLens cart: lst){ total +=
	 * (cart.getQuantity()*cart.getPrice()); }
	 * 
	 * return total; }
	 * 
	 * @RequestMapping (value="remove", method=RequestMethod.GET) public String
	 * remove (@RequestParam (value="id") int id, HttpSession session){
	 * 
	 * List <CartContactLens> lst = (List<CartContactLens>)
	 * session.getAttribute("cart");
	 * 
	 * if (lst != null){ for (CartContactLens cart: lst){ if (cart.getId()==
	 * id){ lst.remove(cart); break; } } }
	 * 
	 * session.setAttribute("cart", lst); session.setAttribute("total",
	 * getTotal(lst));
	 * 
	 * 
	 * return null;
	 * 
	 * 
	 * }
	 */
	
	

	@RequestMapping("usercart")
	public String usercart(Model model, HttpServletRequest request, HttpSession session) {

		model.addAttribute("logueado", userComponent.isLoggedUser());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));

		// Utilizamos este método para cuando mapeamos desde la cabecera a
		// usercart, sin utilizar el método post
		// Redigirimos a la vista según ya se haya añadido un producto desde
		// product_add_contactlens, o no
		if (productadded >= 1) {
			Boolean newproduct = true;
			model.addAttribute("newproduct", newproduct);
		}
		
		model.addAttribute("cartm", session.getAttribute("cart"));

		return "user_cart_shopping";
	}

	@RequestMapping(value = "usercart_post", method = RequestMethod.POST)
	public String usercartpost(Model model, @ModelAttribute("cart") Cart c, @RequestParam("quantity") Integer quantity,
			HttpServletRequest request, HttpSession session) {

		List<Cart> lst = (List<Cart>) session.getAttribute("cart");
		model.addAttribute("logueado", userComponent.isLoggedUser());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));

		productadded = productadded + 1;

		if (lst == null) {

			lst = new ArrayList<>();
			lst.add(c);
		} else {
			boolean flag = false;
			for (Cart cart : lst) {

				if (cart.getId() == c.getId()) {
					cart.setQuantity(cart.getQuantity() + quantity);
					flag = true;
					break;
				}
			}
			if (flag == false) {
				lst.add(c);
			}
		}

		session.setAttribute("cart", lst);

		session.setAttribute("total", getTotal(lst));
		
		System.out.println(lst);

		return "redirect:usercart";
	}

	// Método que necesitamos para el carritp, para calcular el total
	public Double getTotal(List<Cart> lst) {

		double total = 0;
		for (Cart cart : lst) {
			total += (cart.getQuantity() * cart.getPrice());
		}

		return total;
	}
	
	
	@RequestMapping("cleanglasses")
	public String sunglasses(Model model, HttpServletRequest request) {

		model.addAttribute("logueado", userComponent.isLoggedUser());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));

		// Obtenemos nuestro producto del repositorio y lo pasamos al modelo
		
		// Obtenemos nuestro producto del repositorio y lo pasamos al modelo
		Product product = repository.findByBrand("visionlynx");
		model.addAttribute("product_cleanglasses", product);
		
		model.addAttribute ("pepe","pepe");
		
		System.out.println(product);

		return "product_add_cleanglasses";
	}

	@RequestMapping("payment")
	public String payment1(Model model, HttpSession session) {

		model.addAttribute("logueado", userComponent.isLoggedUser());
		model.addAttribute("user", userComponent.getLoggedUser());

		List<Cart> lst = (List<Cart>) session.getAttribute("cart");
		session.setAttribute("cart", lst);
		session.setAttribute("total", getTotal(lst));

		return "payment_gateway";
	}

	@RequestMapping(value = "confirm", method = RequestMethod.POST)
	public String orderconfirm(Model model, @RequestParam("paymentmethod") String payment, HttpSession session) {

		model.addAttribute("logueado", userComponent.isLoggedUser());
		model.addAttribute("user", userComponent.getLoggedUser());

		List<Cart> lst = (List<Cart>) session.getAttribute("cart");
		session.setAttribute("cart", lst);
		session.setAttribute("total", getTotal(lst));

		return "payment_gateway";
	}

}
