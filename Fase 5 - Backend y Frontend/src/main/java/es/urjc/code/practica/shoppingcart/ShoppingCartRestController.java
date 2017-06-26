package es.urjc.code.practica.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import es.urjc.code.practica.user.UserComponent;
import es.urjc.code.practica.shoppingcart.OrderCart;

@RestController
public class ShoppingCartRestController {
	
	interface ShoppingCartView 
	extends OrderSummary.OrderSummaryAttribute,OrderCart.OrderCartAttribute{};
	
	
	@Autowired
	private OrderSummaryRepository repository;
	
	@Autowired
	private OrderCartRepository cartrepository;
	
	@Autowired
	private UserComponent userComponent;
	

	
	@JsonView(ShoppingCartView.class)
	@RequestMapping(value = "/api/carts/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderCart> getCart(@PathVariable long id) {

		OrderCart cart = cartrepository.findOne(id);
		if (cart != null) {
			return new ResponseEntity<>(cart, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(ShoppingCartView.class)
	@RequestMapping(value = "/api/carts/", method = RequestMethod.GET)
	public List<OrderCart> getListCarts(Pageable page) {

		List<OrderCart> listCarts = cartrepository.findAll(page).getContent();
		return listCarts;
	}
	
	
	@RequestMapping(value = "/api/carts/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public OrderCart createCart(@RequestBody OrderCart cart, HttpSession session) {
		
		
		String st= "hola";
		session.setAttribute("model",st);
		
		cartrepository.save(cart);
		return cart;
	}
	
	
	
	@JsonView(ShoppingCartView.class)
	@RequestMapping(value = "/api/orders/", method= RequestMethod.GET)
	public @ResponseBody List<OrderSummary> getOrderCartsPage(Pageable page){
		
		
		//List<OrderSummary> listOrders = repository.findAll(page).getContent();
		List<OrderSummary> listOrders2 = new ArrayList<OrderSummary>();
		
		listOrders2= repository.findAll(page).getContent();
		return listOrders2;
		
		//List<OrderSummary> listOrders = repository.findAll();
		/*if (listOrders != null) {
			return new List<>ResponseEntity><>(listOrders2, HttpStatus.OK);//ResponseBody<>(listOrders2, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} */			
		//return repository.findAll();
	}
	
	@RequestMapping(value = "/api/orders/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public OrderSummary createOrder(@RequestBody OrderSummary order) {
		
	if (order != null) {
		if (userComponent.getListProducts().size() > 0){
			for (OrderCart cart: userComponent.getListProducts()){
				cartrepository.saveAndFlush(cart);
				order.getOrder().add(cart);
			}
		}	
	}	
		
		repository.save(order);
		
		userComponent.setListProducts(new ArrayList<OrderCart>());
		//Ponemos la lista a null una vez que ya se ha guardado el pedido en base de datos 
		return order;
	}
	
	
	@RequestMapping(value = "/api/ordersp/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public OrderSummary createOrderpost(@RequestBody OrderSummary order) {
		
		repository.save(order);
		return order;
	}
	
	@RequestMapping(value = "/api/orders/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderSummary> getOrder(@PathVariable long id) {

		OrderSummary orderget = repository.findOne(id);
		if (orderget != null) {
			return new ResponseEntity<>(orderget, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@RequestMapping(value = "/api/orders/{id}", method = RequestMethod.PUT)
	public ResponseEntity<OrderSummary> updateUser(@PathVariable long id, @RequestBody OrderSummary updateOrder) {
		
		OrderSummary order = repository.findOne(id);
		if (order != null) {

			updateOrder.setId(id);
			repository.save(updateOrder);

			return new ResponseEntity<>(updateOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/orders/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteOrder(@PathVariable long id) {

		repository.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	
	/* Controlar Cart en memoria */
	
	@JsonView(ShoppingCartView.class)
	@RequestMapping("/api/listcart/")
	public @ResponseBody List<OrderCart> getMemoryListCart(Pageable page) {

		System.out.println(userComponent.getListProducts());
		return userComponent.getListProducts();
	}
	
	
	@RequestMapping("/api/pruebaModel/")
	public String getModel(HttpSession session) {	
		
		return (String) session.getAttribute("model");
	}
	
	@RequestMapping (value="/api/cart/addList",method = RequestMethod.POST)
	public String addListCart (@RequestBody OrderCart cart){
		
		/* Código igual que en Controller normal - susceptible de pasar a Servicio - solo esta parte */
		if (userComponent.getListProducts().size() == 0) {
			userComponent.getListProducts().add(cart);
		} else {
			boolean flag = false;
			for (OrderCart ocart : userComponent.getListProducts()) {

				if (ocart.getName().equals(cart.getName())) {
					ocart.setQuantity(ocart.getQuantity() + cart.getQuantity());
					flag = true;
					break;
				}
			}
			if (flag == false) {
				userComponent.getListProducts().add(cart);
			}
		}
		/*Fin código repetido*/
		
		return "Producto añadido";
	}
	
    @RequestMapping (value="/api/cart/remove/{name}",method = RequestMethod.DELETE)
    public String removeCart (@PathVariable String name){
		
    	//List <Cart> lst = (List<Cart>) session.getAttribute("cart");	
    	if (userComponent.getListProducts() != null){
    		for (OrderCart cart: userComponent.getListProducts()){
    			if (cart.getName().equals(name)){
    				userComponent.getListProducts().remove(cart);
    				break;
    			}
    		}	
    	}
    	
    	return "Producto Eliminado";
    	
    	
    }

    /*Fin métodos controlar Cart en memoria */
}
