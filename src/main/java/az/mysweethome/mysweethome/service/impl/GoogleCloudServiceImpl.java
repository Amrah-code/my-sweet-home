package az.mysweethome.mysweethome.service.impl;

import az.mysweethome.mysweethome.service.GoogleCloudService;
import az.mysweethome.mysweethome.exception.WrongFileFormatException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class GoogleCloudServiceImpl implements GoogleCloudService {
    @Value("${project-id}")
    private String projectId;
    @Value("${bucket-name}")
    private String bucketName;
    @Value("${show-image-url}")
    private String showImageUrl;

    @Override
    public Map<String,String> upload(MultipartFile file) {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/gcp-cred-file.json"))
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).setCredentials(credentials).build().getService();

            Map<String, String> imageInfo = new HashMap<>();
            String extension = checkFileExtensionAndReturn(file.getOriginalFilename());
            String finalFileName = Objects.requireNonNull(file.getOriginalFilename()).replace(extension, "") + UUID.randomUUID() + extension;
            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder(bucketName, finalFileName)
                            .setContentType(getFilesType(file))
                            .setContentDisposition("inline")
                                             .build(),
                                               file.getBytes(),
                    Storage.BlobTargetOption.predefinedAcl(Storage.PredefinedAcl.PUBLIC_READ)
            );
            String imageUrl = showImageUrl + finalFileName;
            imageInfo.put("ImageName", finalFileName);
            imageInfo.put("ImageUrl", imageUrl);
            return imageInfo;
        }catch(IllegalStateException | IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteImage(String objectName) {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/gcp-cred-file.json"))
                    .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            Storage storage = StorageOptions.newBuilder().setProjectId(projectId).setCredentials(credentials).build().getService();
            boolean isDeleted = storage.delete(bucketName, objectName);

            return isDeleted;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String checkFileExtensionAndReturn(String fileName) {

        if (fileName != null && fileName.contains(".")) {
            String[] imageExtensions = {".jpg", ".jpeg", ".png"};
            for (String extension : imageExtensions) {
                if (fileName.endsWith(extension)) {
                    return extension;
                }
            }
        }
        throw new WrongFileFormatException();
    }

    private String getFilesType(MultipartFile multipartFile) throws IOException {
        File file = convertMultiPartToFile(multipartFile);
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String result = fileNameMap.getContentTypeFor(file.getName());
        file.delete();
        return result;
    }

    private File convertMultiPartToFile(MultipartFile file ) throws IOException
    {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        return convFile;
    }
}
