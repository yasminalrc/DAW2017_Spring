package es.urjc.code.practica.shoppingcart;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import es.urjc.code.practica.user.User;

@Entity
public class OrderSummary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/***Atributos de Cart ***/
	private String ordername;
	private String user;
	private String paymentmethod;
	
	
	@OneToMany(mappedBy="productscart",cascade=CascadeType.ALL)
	private List <OrderCart> order;
	
	
	public OrderSummary (){
			
	}


	public OrderSummary(long id, String ordername, String user, String paymentmethod, List<OrderCart> order) {
		super();
		this.id = id;
		this.ordername = ordername;
		this.user = user;
		this.paymentmethod = paymentmethod;
		this.order = order;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getOrdername() {
		return ordername;
	}


	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPaymentmethod() {
		return paymentmethod;
	}


	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}


	public List<OrderCart> getOrder() {
		return order;
	}


	public void setOrder(List<OrderCart> order) {
		this.order = order;
	}


	@Override
	public String toString() {
		return "OrderSummary [id=" + id + ", ordername=" + ordername + ", user=" + user + ", paymentmethod="
				+ paymentmethod + ", order=" + order + "]";
	}
	
	
	

}
