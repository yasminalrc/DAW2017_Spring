package es.urjc.code.practica.offers;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Long> {
 
    Offer findById(Long id);  
    
    Offer findByName(String name); 


}
