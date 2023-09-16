package it.uniroma3.siw.service;


import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.repository.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Transactional
	public Image getImage(Long id) {
		if (id==0) return null;
		else return this.imageRepository.findById(id).get();
	}
	
	@Transactional
	public void saveImage(Image image) {
		this.imageRepository.save(image);
	}
	
	@Transactional
	public void newImagesProd(MultipartFile file, Prodotto prodotto) throws IOException {

		byte[] imageData = file.getBytes();
		String imageName = file.getOriginalFilename();

		Image image = new Image();
		image.setName(imageName);
		image.setBytes(imageData);

		prodotto.setImage(image);

		this.saveImage(image);

	}
	

}
