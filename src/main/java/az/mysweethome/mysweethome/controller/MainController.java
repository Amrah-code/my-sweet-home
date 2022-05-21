package az.mysweethome.mysweethome.controller;

import az.mysweethome.mysweethome.data.dto.ImageDto;
import az.mysweethome.mysweethome.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageDto> uploadImage(@RequestBody MultipartFile file,
                                                @RequestParam String headerText,
                                                @RequestParam String footerText){
        return new ResponseEntity<>(imageService.uploadImage(file, headerText, footerText), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteImage(@RequestParam Long id){
        return new ResponseEntity<>(imageService.deleteImage(id), HttpStatus.OK);
    }
}
