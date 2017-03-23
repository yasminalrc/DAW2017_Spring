package es.urjc.code.practica.shoppingcart;

import org.springframework.stereotype.Component;

import es.urjc.code.practica.user.User;

@Component
public class UserCart {

	private Cart cart;
	private User user;

	public UserCart createCart (Cart cart, User user){
		this.cart=cart;
		this.user=user;
		
		return this;
	}
}
