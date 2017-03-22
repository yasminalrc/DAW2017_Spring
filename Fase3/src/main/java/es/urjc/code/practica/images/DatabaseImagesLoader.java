package es.urjc.code.practica.images;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.urjc.code.practica.images.Image;
import es.urjc.code.practica.images.ImageRepository;
import es.urjc.code.practica.user.User;
import es.urjc.code.practica.user.UserRepository;

@Component
public class DatabaseImagesLoader {

	@Autowired
	private ImageRepository imageRepository;


	@PostConstruct
	private void initDatabase() {

		Image image1 = new Image("imagen1", "files");
		Image image2 = new Image("imagen2", "files");
	
		imageRepository.save(image1);
		imageRepository.save(image2);

		/* imageRepository.save(new Image("user3", "/files/imagen3"));
		imageRepository.save(new Image("admin", "/files/imagen4"));*/

	}

}
