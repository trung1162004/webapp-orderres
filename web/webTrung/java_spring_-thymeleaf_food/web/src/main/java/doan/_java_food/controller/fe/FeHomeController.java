package doan._java_food.controller.fe;

import doan._java_food.models.Category;
import doan._java_food.models.Location;
import doan._java_food.models.Restaurant;
import doan._java_food.models.User;
import doan._java_food.service.Category.CategoryService;
import doan._java_food.service.Restaurant.RestaurantService;
import doan._java_food.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class FeHomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String homeIndex(Model model, @RequestParam(value = "error", required = false) String error, Principal principal) {
        List<Category> categories = this.categoryService.getAll();
        List<Restaurant> restaurants = this.restaurantService.getAllByCondition(0, 4,
                "ACTIVE", "", "", "RESTAURANT",
                "", null, "",
                "", "", "");

        List<Restaurant> restaurantDiscount = this.restaurantService.getAllByCondition(0, 4,
                "ACTIVE", "", "", "RESTAURANT", "", null, "",
                "HOT", "", "");


        Integer total = this.restaurantService.countAllByCondition("ACTIVE", "", "",
                "RESTAURANT", "", null, "", "",
                "", "");


        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        List<Location> locations = this.restaurantService.getAllLocation();
        model.addAttribute("locations", locations);
        model.addAttribute("categories", categories);
        model.addAttribute("total", total);
        model.addAttribute("restaurants", restaurants);

        model.addAttribute("location_id", "");
        model.addAttribute("name", "");
        model.addAttribute("price_id", "");

        model.addAttribute("restaurantDiscounts", restaurantDiscount);
        return "pages/fe/home";
    }

    @RequestMapping("/home.html")
    public String getHome(Model model, @RequestParam(value = "error", required = false) String error) {
        String message = null;
        model.addAttribute("errorMsg", message);

        return "pages/fe/home";
    }

    @GetMapping("/location-seed.html")
    public String seedProvince() {
        try {

            String[] locations = {"Ha Noi", "Ho Chi Minh", "Da Nang", "Can Tho"};
            for (String item: locations
                 ) {
                this.restaurantService.createOrUpdateLocation(item);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "pages/fe/home";
    }
}
