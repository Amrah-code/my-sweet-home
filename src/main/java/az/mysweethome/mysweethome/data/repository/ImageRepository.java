package az.mysweethome.mysweethome.data.repository;

import az.mysweethome.mysweethome.data.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
