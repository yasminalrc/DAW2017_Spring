package es.urjc.code.practica.images;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {

    Image findByTitle(String title);
    
    Image findById(Long id);
    
    Image findByRuta(String ruta);

}
