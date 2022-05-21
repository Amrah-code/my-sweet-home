package az.mysweethome.mysweethome.data.service;

import az.mysweethome.mysweethome.data.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageDto uploadImage(MultipartFile file, String headerText, String footerText);
    String deleteImage(Long id);
}
