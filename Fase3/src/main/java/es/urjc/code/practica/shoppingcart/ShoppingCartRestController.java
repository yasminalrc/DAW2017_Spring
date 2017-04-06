package es.urjc.code.practica.shoppingcart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
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


import es.urjc.code.practica.images.Image;
import es.urjc.code.practica.images.ImageRepository;

@RestController
public class ShoppingCartRestController {
	
	interface ShoppingCartView extends OrderSummary.OrderSummaryAttribute{};
	
	@Autowired
	private OrderSummaryRepository repository;
	
	@Autowired
	private OrderCartRepository cartrepository;

	interface CartDetail extends OrderSummary.OrderSummaryAttribute, OrderSummary.OrderCarts, OrderCart.OrderCartAttribute  {
	}
	
	
	@RequestMapping(value = "/api/orders/", method= RequestMethod.GET)
	public Collection<OrderSummary> getOrderCartsPage(Pageable page){
		
		
		return repository.findAll();
		
		
	}
	
	@RequestMapping(value = "/api/orders/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public OrderSummary createOrder(@RequestBody OrderSummary order) {
		
/*	if (order != null) {
		if (listcart !=null){
			for (OrderCart cart: listcart){
				cartrepository.saveAndFlush(cart);
				order.getOrder().add(cart);
			}
		}	
	}	*/
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
	public ResponseEntity<OrderSummary> deleteOrder(@PathVariable long id) {

		repository.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}

