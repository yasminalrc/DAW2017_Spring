package es.urjc.code.practica.shoppingcart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import es.urjc.code.practica.user.User;

@Entity
public class OrderCart{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Atributos de Cart ***/
	private String name;
	private Double price;
	private Integer quantity;
	private String size;
	private String sphere;
	private String radio;
	private String diameter;
	private String eye;
	
	/*@ManyToOne
	private OrderSummary productscart;*/
	
	
	/*public OrderSummary getProductscart() {
		return productscart;
	}

	public void setProductscart(OrderSummary productscart) {
		this.productscart = productscart;
	} */

	public OrderCart (){
			
	}

	public OrderCart( String name, Double price, Integer quantity, String size, String sphere,
			String radio, String diameter, String eye) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.size = size;
		this.sphere = sphere;
		this.radio = radio;
		this.diameter = diameter;
		this.eye = eye;
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSphere() {
		return sphere;
	}

	public void setSphere(String sphere) {
		this.sphere = sphere;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getEye() {
		return eye;
	}

	public void setEye(String eye) {
		this.eye = eye;
	}



	@Override
	public String toString() {
		return "OrderCart [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", size="
				+ size + ", sphere=" + sphere + ", radio=" + radio + ", diameter=" + diameter + ", eye=" + eye + "]";
	}


	
	
	

}
