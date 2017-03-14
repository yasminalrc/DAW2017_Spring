package es.urjc.code.practica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.code.practica.images.Image;
import es.urjc.code.practica.images.ImageRepository;

@Controller
public class ImagesAdminController {

	private static final String FILES_FOLDER = "files";

	private List<String> imageTitles = new ArrayList<>();
	
	@Autowired
	private ImageRepository imageRepository;
	
	@RequestMapping(value="/adminadd", method = RequestMethod.POST)
	//@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, 
			@RequestParam("imageTitle") String imageTitle,
			@RequestParam("file") MultipartFile file) {

		String fileName = imageTitles.size() + ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

				imageTitles.add(imageTitle);
				
				
				model.addAttribute("imageTitles", imageTitles);
				
				//Código para guardar la entidad imagen en Base de datos
				Image image = new Image(imageTitle, filesFolder.getPath());
				
				//Prueba - duda entre usar Path o la ruta Absoluta 
				System.out.println("Esto es : getAbsolutePath" + filesFolder.getAbsolutePath());
				System.out.println("Esto es : getPath" + filesFolder.getPath());
				
				imageRepository.save(image);
				model.addAttribute("path", filesFolder.getAbsolutePath());
				
				return "admin_add_product";

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
				
				return "admin_add_product";
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");
			
			return "admin_add_product";
		}
	}
	
	

	@RequestMapping("/image/{fileName}")
	public void handleFileDownload(@PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = new File(FILES_FOLDER, fileName+".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils
					.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath()
					+ ") does not exist");
		}
	}

}