package az.mysweethome.mysweethome.data.mapper;


import az.mysweethome.mysweethome.data.dto.ImageDto;
import az.mysweethome.mysweethome.data.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageMapper {
    ImageMapper mapper = Mappers.getMapper(ImageMapper.class);
    ImageDto imageToImageDto(Image image);

}
