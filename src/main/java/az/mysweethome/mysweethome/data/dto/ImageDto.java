package az.mysweethome.mysweethome.data.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ImageDto {
    private Long id;
    private Date uploadDate;
    private String imageName;
    private String imageUrl;
    private String headerText;
    private String footerText;
}
