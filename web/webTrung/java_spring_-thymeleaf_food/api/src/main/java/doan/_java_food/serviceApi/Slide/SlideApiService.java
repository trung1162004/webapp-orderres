package doan._java_food.serviceApi.Slide;

import doan._java_food.requestDto.SlideRequestDto;
import doan._java_food.response.Slide.ListSlideResponse;
import doan._java_food.response.Slide.SlideResponse;

public interface SlideApiService {

    SlideResponse findById(Long id);
    ListSlideResponse findAll(int page, int page_size);
    SlideRequestDto save(SlideRequestDto data);
    SlideRequestDto update(Long id, SlideRequestDto data);
    void delete(Long data_id);

}
