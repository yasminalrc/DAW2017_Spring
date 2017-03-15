package es.urjc.code.practica.product;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.urjc.code.practica.user.UserComponent;

@Controller
public class ProductController {
	
	@Autowired
	private ProductsRepository repository;
	
	@Autowired
	private UserComponent userComponent;
	
	
	
	@PostConstruct
	public void init() {
		repository.save(new Product("sunglasses1", "brand1", "model1", "reference1", "type1", "red", "M", null, null, false, 0.50, 5, "/images/upload/product1.jgp", "description1"));
		repository.save(new Product("sunglasses2", "brand2", "model2", "reference2", "type1", "black", "S", null, null, false, 0.50, 5, "/images/upload/product2.jgp", "description2"));
		repository.save(new Product("sunglasses3", "brand3", "model3", "reference3", "type1", "blue", "XL", null, null, false, 0.50, 5, "/images/upload/product3.jgp", "description3"));
		repository.save(new Product("sunglasses4", "brand4", "model4", "reference4", "type1", "red", "M", null, null, false, 0.50, 5, "/images/upload/product4.jgp", "description4"));
		repository.save(new Product("sunglasses5", "brand5", "model5", "reference5", "type5", "red", "M", null, null, false, 0.50, 5, "/images/upload/product5.jgp", "description5"));
		repository.save(new Product("lents6", "brand6", "model6", "reference6", "type2", null, null, "0.25", "8.50", false, 0.50, 5, "/images/upload/product6.jgp", "description6"));
		
		//repository.save(new Product("lents7", "brand7", "model7", "reference7", "type2", null, null, 0.25, 8.50, false, 0.50, 5, "/images/upload/product7.jgp", "description7"));
		//repository.save(new Product("lents8", "brand8", "model8", "reference8", "type2", null, null, 0.25, 8.50, false, 0.50, 5, "/images/upload/product8.jgp", "description8"));
		//repository.save(new Product("lents9", "brand9", "model9", "reference9", "type2", null, null, 0.25, 8.50, false, 0.50, 5, "/images/upload/product9.jgp", "description9"));
		//repository.save(new Product("lents10", "brand10", "model10", "reference10", "type2", null, null, 0.25, 8.50, false, 0.50, 5, "/images/upload/product10.jgp", "description10"));
		repository.save(new Product("accesory11", "brand11", "model11", "reference11", "type3", null, null, null, null, false, 0.50, 5, "/images/upload/product11.jgp", "description11"));
		repository.save(new Product("accesory12", "brand12", "model12", "reference12", "type3", null, null, null, null, false, 0.50, 5, "/images/upload/product12.jgp", "description12"));
		repository.save(new Product("accesory13", "brand13", "model13", "reference13", "type3", null, null, null, null, false, 0.50, 5, "/images/upload/product13.jgp", "description13"));
		repository.save(new Product("accesory14", "brand14", "model14", "reference14", "type3", null, null, null, null, false, 0.50, 5, "/images/upload/product14.jgp", "description14"));
		repository.save(new Product("accesory15", "brand15", "model15", "reference15", "type3", null, null, null, null, false, 0.50, 5, "/images/upload/product15.jgp", "description15"));
	}
   
	// VIEW
	@RequestMapping("/admin/product/")
	public String productList(Model model) {

		model.addAttribute("products", repository.findAll());

		return "admin_product_list";
	}

	/*
	// VIEW 
	@RequestMapping("/admin/product/")
	public String products(Model model, Pageable page) {
	
		model.addAttribute("products", repository.findAll(page));
	
		return "admin_product_list";
	}
	*/
	
	// EDIT
	@RequestMapping("/admin/product/{id}")
	public String productView(Model model, @PathVariable long id) {
		
		
		Product product = repository.findOne(id);

		model.addAttribute("product", product);

		return "admin_product_view";
	}

}
