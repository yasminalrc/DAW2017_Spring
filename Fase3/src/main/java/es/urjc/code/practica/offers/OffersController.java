package es.urjc.code.practica.offers;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import es.urjc.code.practica.images.Image;
import es.urjc.code.practica.images.ImageRepository;
import es.urjc.code.practica.product.Product;
import es.urjc.code.practica.user.UserComponent;

@Controller
public class OffersController {
	
	@Autowired
	private OfferRepository repository;
	
	
	@Autowired
	private ImageRepository imageRepository;
	
	private static final String FILES_FOLDER = "files";
	
	@PostConstruct
	public void init() {
		
		Offer offer1 = new Offer("Rayban","offer1", (double) 100);
		Offer offer2 = new Offer("Prada","offer2", (double) 200);	
		Offer offer3 = new Offer("Cartier","offer3", (double) 300);	
		Offer offer4 = new Offer("Ralph Lauren","offer4", (double) 400);	
		Offer offer5 = new Offer("Dolce&Gabanna","offer5", (double) 500);	
		Offer offer6 = new Offer("Carolina Herrera","offer6", (double) 600);
		Offer offer7 = new Offer("D&K","offer7", (double) 700);
		Offer offer8 = new Offer("Massimo Dutti","offer8", (double) 800);
		Offer offer9 = new Offer("Arnette","offer9", (double) 900);
		Offer offer10 = new Offer("Vogue","offer10", (double) 1000);
		
		
		repository.save(offer1);
		repository.save(offer2);
		repository.save(offer3);
		repository.save(offer4);
		repository.save(offer5);
		repository.save(offer6);
		repository.save(offer7);
		repository.save(offer8);
		repository.save(offer9);
		repository.save(offer10);
		
	}
	
	
	@RequestMapping(value = "/offers", method = RequestMethod.GET)
	    public String listOffer(Model model) {
	       // model.addAttribute("offer", new Offer());
		
	        model.addAttribute("offers", repository.findAll());
	        
	        return "user_offers";
	 }
   

	

}

