package doan._java_food.serviceApi.Slide;

import doan._java_food.models.Slides;
import doan._java_food.repository.SlideRepository;
import doan._java_food.requestDto.SlideRequestDto;
import doan._java_food.response.MappingResponseDto;
import doan._java_food.response.Slide.ListSlideResponse;
import doan._java_food.response.Slide.SlideResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SlideApiServiceImpl implements SlideApiService {

    @Autowired
    private SlideRepository repository;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    private MappingResponseDto responseDto;

    public Slides createOrUpdateData(SlideRequestDto dataRequest, Slides oldData) {
        Slides newData = oldData;
        if(oldData == null) {
            newData = new Slides();
        }
        if(dataRequest.getName() != null) {
            newData.setName(dataRequest.getName());
        }
        if(dataRequest.getStatus() != null) {
            newData.setStatus(dataRequest.getStatus());
        }
        if(dataRequest.getImage() != null) {
            newData.setImage(dataRequest.getImage());

        }
        return newData;
    }
    @Override
    public SlideResponse findById(Long id) {
        return responseDto.getSlideInfo(repository.getById(id));

    }

    @Override
    public ListSlideResponse findAll(int page, int page_size) {
        Pageable pageable = PageRequest.of(page, page_size);
        Page<Slides> results = repository.findAll(pageable);
        ListSlideResponse dataListResponse = new ListSlideResponse();
        dataListResponse.setTotal(results.getTotalElements());
        if(results.isEmpty()) {
            dataListResponse.setData(new ArrayList<>());
            return dataListResponse;
        }
        List<SlideResponse> data = new ArrayList<>();
        for (Slides item: results) {
            data.add(responseDto.getSlideInfo(item));
        }
        dataListResponse.setData(data);
        return dataListResponse;
    }

    @Override
    public SlideRequestDto save(SlideRequestDto data) {
        Slides o = this.createOrUpdateData(data, null);
        Slides newRank = repository.save(o);
        return data;
    }

    @Override
    public SlideRequestDto update(Long id, SlideRequestDto data) {
        var c = repository.getById(id);
        if(c != null) {
            Slides o = this.createOrUpdateData(data, c);
            Slides newRank = repository.save(o);
            return data;
        }
        return null;
    }

    @Override
    public void delete(Long data_id) {
        repository.deleteById(data_id);
    }
}
