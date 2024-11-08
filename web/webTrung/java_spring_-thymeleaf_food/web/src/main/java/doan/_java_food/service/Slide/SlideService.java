package doan._java_food.service.Slide;

import doan._java_food.models.Slides;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SlideService {

    List<Slides> getAll();

    Boolean create(Slides career, MultipartFile fileAvatar);
    Slides findById(Long id);

    Boolean update(Slides career, MultipartFile fileAvatar);

    Boolean delete(Long id);

}
