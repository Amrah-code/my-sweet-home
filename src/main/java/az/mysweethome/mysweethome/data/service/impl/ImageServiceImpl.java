package az.mysweethome.mysweethome.data.service.impl;

import az.mysweethome.mysweethome.data.dto.ImageDto;
import az.mysweethome.mysweethome.data.entity.Image;
import az.mysweethome.mysweethome.data.mapper.ImageMapper;
import az.mysweethome.mysweethome.data.repository.ImageRepository;
import az.mysweethome.mysweethome.data.service.GoogleCloudService;
import az.mysweethome.mysweethome.data.service.ImageService;
import az.mysweethome.mysweethome.exception.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final GoogleCloudService googleCloudService;
    private final ImageRepository imageRepository;

    @Override
    public ImageDto uploadImage(MultipartFile file, String headerText, String footerText){
        Map<String, String> imageInfo = googleCloudService.upload(file);
        Image image = Image.builder().imageName(imageInfo.get("ImageName"))
                .imageUrl(imageInfo.get("ImageUrl"))
                .headerText(headerText)
                .footerText(footerText)
                .uploadDate(new Date()).build();
        return ImageMapper.mapper.imageToImageDto(imageRepository.save(image));
    }

    @Override
    public String deleteImage(Long id){
        Optional<Image> image = imageRepository.findById(id);
        if(image.isPresent()){
            googleCloudService.deleteImage(image.get().getImageName());
            imageRepository.delete(image.get());
            return "Image with ID - " + id + " is deleted";
        }else{
            throw new ImageNotFoundException();
        }
    }
}
