package es.urjc.code.practica.shoppingcart;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cart {
	
		
		private long id;

		/*** Main ***/
		private String name;
		private Double price;
		private Integer quantity;
		private String size;
		private String sphere;
		private String radio;
		private String diameter;
		private String eye;
		
		/*** Images ***/	
		private String image;
		

		public Cart(){}
		
		
		

		public Cart(long id, String name, Double price, Integer quantity, String size, String sphere, String radio,
				String diameter, String eye, String image) {
			super();
			this.id = id;
			this.name = name;
			this.price = price;
			this.quantity = quantity;
			this.size = size;
			this.sphere = sphere;
			this.radio = radio;
			this.diameter = diameter;
			this.eye = eye;
			this.image = image;
			
		}


	

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((diameter == null) ? 0 : diameter.hashCode());
			result = prime * result + ((eye == null) ? 0 : eye.hashCode());
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((image == null) ? 0 : image.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((price == null) ? 0 : price.hashCode());
			result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
			result = prime * result + ((radio == null) ? 0 : radio.hashCode());
			result = prime * result + ((size == null) ? 0 : size.hashCode());
			result = prime * result + ((sphere == null) ? 0 : sphere.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cart other = (Cart) obj;
			if (diameter == null) {
				if (other.diameter != null)
					return false;
			} else if (!diameter.equals(other.diameter))
				return false;
			if (eye == null) {
				if (other.eye != null)
					return false;
			} else if (!eye.equals(other.eye))
				return false;
			if (id != other.id)
				return false;
			if (image == null) {
				if (other.image != null)
					return false;
			} else if (!image.equals(other.image))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (price == null) {
				if (other.price != null)
					return false;
			} else if (!price.equals(other.price))
				return false;
			if (quantity == null) {
				if (other.quantity != null)
					return false;
			} else if (!quantity.equals(other.quantity))
				return false;
			if (radio == null) {
				if (other.radio != null)
					return false;
			} else if (!radio.equals(other.radio))
				return false;
			if (size == null) {
				if (other.size != null)
					return false;
			} else if (!size.equals(other.size))
				return false;
			if (sphere == null) {
				if (other.sphere != null)
					return false;
			} else if (!sphere.equals(other.sphere))
				return false;
			return true;
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

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}


		@Override
		public String toString() {
			return "Product [ id: " + id + "\n" + 
				   "          name: " + name + "\n" + 
				   "          size: " + size + "\n" + 
				   "   	 	  sphere: " + sphere + "\n" + 
				   "		  radio: " + radio + "\n" + 
				   " 		  price: " + price + "\n" + 
				   "  		  quantity: " + quantity + "]";
		} 

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

}
