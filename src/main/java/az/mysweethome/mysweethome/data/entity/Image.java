package az.mysweethome.mysweethome.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date uploadDate;
    private String imageName;
    @Column(length = 2000)
    private String imageUrl;
    private String headerText;
    private String footerText;
}
