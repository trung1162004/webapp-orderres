package doan._java_food.controller.fe;

import doan._java_food.models.Category;
import doan._java_food.models.Restaurant;
import doan._java_food.repository.RestaurantRepository;
import doan._java_food.repository.UserRestaurantRepository;
import doan._java_food.service.Category.CategoryService;
import doan._java_food.service.Order.OrderService;
import doan._java_food.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/")

public class FeCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRestaurantRepository userRestaurantRepository;

    @GetMapping("/category/{id}")
    public String getListCategory(Model model, @PathVariable("id") Integer id,
                           @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                  @RequestParam(name = "page_size", required = false, defaultValue = "10") String page_size)
    {
        Category category = this.categoryService.findById(Long.valueOf(id));

        List<Restaurant> restaurants = this.restaurantRepository.findAndCountByCategory(
                Integer.parseInt(page)- 1,
                Integer.parseInt(page_size),
                "", "", "", id);

        Integer total = this.restaurantRepository.countByConditionByCategory(
                "", "", "", id);
        String params = "";
//        if(!location_id.isEmpty()) params += "&location_id="+location_id;
//        if(!name.isEmpty()) params += "&name="+name;
//        if(!price_id.isEmpty()) params += "&price_id="+price_id;
        int totalPage = 0;
        if(total != null) {
            totalPage = (int) Math.ceil((double) total / Integer.parseInt(page_size));;
        }

        System.out.println(totalPage + "-------" + total);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("total", total);
        model.addAttribute("page", parseInt(page));
        model.addAttribute("page_size", page_size);

        model.addAttribute("url", "/category/"+id);
        model.addAttribute("params", params);
        model.addAttribute("category", category);
        model.addAttribute("restaurants", restaurants);
        return "pages/fe/category";
    }
}
