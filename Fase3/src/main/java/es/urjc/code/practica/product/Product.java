package es.urjc.code.practica.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.urjc.code.practica.images.Image;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/*** Main ***/
	private String name;
	private String brand;
	private String model;
	private String reference;
	// ***** Acotar type
	private String type;
	private String colour;
	private String size;
	private String sphere;
	private String radio;
	private Boolean published;
	private Double price;
	private Integer quantity;
	
	/*** Images ***/	
	private String image;
	
	/*** Description ***/
	private String description;

	/*** Category ***/
	
	public Product(){}

	public Product(String name, String brand, String model, String reference, String type, String colour, 
			String size, String sphere, String radio, Boolean published, Double price, Integer quantity, 
			String image, String description) {
		super();
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.reference = reference;
		this.type = type;
		this.colour = colour;
		this.size = size;
		this.sphere = sphere;
		this.radio = radio;
		this.published = published;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.description = description;
	}
	
	/*
	// GAFAS DE SOL
	public Product(String name, String brand, String model, String reference, String type, String colour, 
			String size, Boolean published, Double price, Integer quantity, String image, String description) {
		super();
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.reference = reference;
		this.type = type;
		this.colour = colour;
		this.size = size;
		this.published = published;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.description = description;
	}
	
	// LENTILLAS
	public Product(String name, String brand, String model, String reference, String type, Double sphere, 
			Double radio, Boolean published, Double price, Integer quantity, String image, String description) {
		super();
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.reference = reference;
		this.type = type;
		this.sphere = sphere;
		this.radio = radio;
		this.published = published;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.description = description;
	}
	
	// LIQUIDOS Y ACCESORIOS
	public Product(String name, String brand, String model, String reference, String type, Boolean published, 
			Double price, Integer quantity, String image, String description) {
		super();
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.reference = reference;
		this.type = type;
		this.published = published;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.description = description;
	}
	*/

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
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

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [ id: " + id + "\n" + 
			   "          name: " + name + "\n" + 
			   "	      brand: " + brand + "\n" +
			   " 		  model: " + model + "\n" +
			   "		  reference: " + reference + "\n" +
			   "		  type: " + type + "\n" + 
			   "		  colour: " + colour + "\n" + 
			   "		  size: " + size + "\n" +	
			   "   	 	  sphere: " + sphere + "\n" + 
			   "		  radio: " + radio + "\n" + 
			   "		  published: " + published + "\n" + 
			   " 		  price: " + price + "\n" + 
			   "  		  quantity: " + quantity + "\n" + 
			   " 		  description=" + description + "]";
	}

}
