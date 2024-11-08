package doan._java_food.response.Restauran;

import doan._java_food.response.Slide.SlideResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ListRestaurantResponse {
    private Long total;
    private List<RestaurantResponse> data;
}
