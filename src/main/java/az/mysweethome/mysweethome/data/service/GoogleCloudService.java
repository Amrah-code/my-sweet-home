package az.mysweethome.mysweethome.data.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface GoogleCloudService {
    Map<String,String> upload(MultipartFile file);
    boolean deleteImage(String objectName);
}
