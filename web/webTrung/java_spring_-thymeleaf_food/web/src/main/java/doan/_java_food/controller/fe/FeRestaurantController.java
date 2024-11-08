package doan._java_food.controller.fe;

import doan._java_food.models.*;
import doan._java_food.repository.UserRestaurantRepository;
import doan._java_food.service.Order.OrderService;
import doan._java_food.service.Restaurant.RestaurantService;
import doan._java_food.service.User.UserService;
import doan._java_food.service.UserService.UsersServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/")
public class FeRestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private UsersServices cooperationService;

    @Autowired
    private OrderService orderService;


    @Autowired
    private UserRestaurantRepository userRestaurantRepository;




    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//    @GetMapping("/search.html")
//    public String getListsSearch(Model model, Principal principal,
//                                 @RequestParam(name = "page", required = false, defaultValue = "1") String page,
//                                 @RequestParam(name = "page_size", required = false, defaultValue = "200") String page_size,
//                                 @RequestParam(name = "location_id", required = false, defaultValue = "") String location_id,
//                                 @RequestParam(name = "name", required = false, defaultValue = "") String name
//    )
//    {
//
//        if(principal != null) {
//            String username = principal.getName();
//            User user = this.userService.findByUsername(username);
//            model.addAttribute("user", user);
//        }
//        List<Restaurant> lists = this.restaurantService.getAllByCondition(Integer.parseInt(page )- 1,
//                Integer.parseInt(page_size), "ACTIVE", name, "",
//                "RESTAURANT", "", null, location_id, "");
//
//        Integer total = this.restaurantService.countAllByCondition("ACTIVE", name, "", "RESTAURANT",
//                "", null, location_id, "");
//        model.addAttribute("lists", lists);
//        model.addAttribute("total", total);
//        model.addAttribute("type", "SEARCH");
//        model.addAttribute("page", Integer.parseInt(page ));
//        model.addAttribute("page_size", Integer.parseInt(page_size));
//        return "pages/fe/restaurants/restaurant";
//    }
    @GetMapping("/restaurant")
    public String getLists(Model model, Principal principal,
    		
                           @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                           @RequestParam(name = "page_size", required = false, defaultValue = "12") String page_size,
                           @RequestParam(name = "location_id", required = false, defaultValue = "") String location_id,
                           @RequestParam(name = "name", required = false, defaultValue = "") String name,
                           @RequestParam(name = "price_id", required = false, defaultValue = "") String price_id,
                           @RequestParam(value = "utilities", required = false) Set<String> utilities
                           )
     {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        String price_from = "";
        String price_to = "";

        // Chuyển đổi price_id sang khoảng giá tương ứng
        if(price_id.equals("1")) {
            price_from = "0";
            price_to = "100";
        } else if(price_id.equals("2")) {
            price_from = "100";
            price_to = "250";
        } else if(price_id.equals("3")) {
            price_from = "250";
            price_to = "500";
        } else if(price_id.equals("4")) {
            price_from = "500";
            price_to = "10000";
        }

        System.out.println(price_from + "----" + price_to);

        List<Restaurant> lists = this.restaurantService.getAllByCondition(
            Integer.parseInt(page) - 1,
            Integer.parseInt(page_size), "ACTIVE", name, "",
            "RESTAURANT", "", null, location_id, "",
            price_from, price_to
        );

        Integer total = this.restaurantService.countAllByCondition("ACTIVE", name, "", "RESTAURANT",
                "", null, location_id, "", price_from, price_to);
        List<Location> locations = this.restaurantService.getAllLocation();
        model.addAttribute("locations", locations);
        model.addAttribute("lists", lists);
        model.addAttribute("type", "RESTAURANT");
        model.addAttribute("location_id", location_id);
        model.addAttribute("name", name);
        model.addAttribute("price_id", price_id);
        

        String params = "";
        if(!location_id.isEmpty()) params += "&location_id=" + location_id;
        if(!name.isEmpty()) params += "&name=" + name;
        if(!price_id.isEmpty()) params += "&price_id=" + price_id;
        int totalPage = 0;
        if(total != null) {
            totalPage = (int) Math.ceil((double) total / Integer.parseInt(page_size));
        }

        System.out.println(totalPage + "-------" + total);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("total", total);
        model.addAttribute("page", Integer.parseInt(page));
        model.addAttribute("page_size", page_size);

        model.addAttribute("url", "/restaurant");
        model.addAttribute("params", params);
        model.addAttribute("utilities", utilities);

        return "pages/fe/restaurants/restaurant";
    }


    @GetMapping("/hot-restaurant.html")
    public String getListsHot(Model model, Principal principal,
                           @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                           @RequestParam(name = "page_size", required = false, defaultValue = "12") String page_size
    )
    {

        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        List<Restaurant> lists = this.restaurantService.getAllByCondition(Integer.parseInt(page )- 1,
                Integer.parseInt(page_size), "ACTIVE", "", "",
                "RESTAURANT", "", null, "HOT", "", "", "");

        Integer total = this.restaurantService.countAllByCondition("ACTIVE", "", "", "RESTAURANT",
                "", null,"HOT", "", "", "");
        System.out.println(total);
        model.addAttribute("lists", lists);
        model.addAttribute("type", "HOT-RESTAURANT");

        String params = "";
//        if(!location_id.isEmpty()) params += "&location_id="+location_id;
//        if(!name.isEmpty()) params += "&name="+name;
//        if(!price_id.isEmpty()) params += "&price_id="+price_id;
        int totalPage = 0;
        if(total != null) {
            totalPage = (int) Math.ceil((double) total / Integer.parseInt(page_size));;
        }
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("total", total);
        model.addAttribute("page", parseInt(page));
        model.addAttribute("page_size", page_size);

        model.addAttribute("url", "/hot-restaurant.html");
        model.addAttribute("params", params);

        return "pages/fe/restaurants/restaurant";
    }

    @GetMapping("/discount-restaurant.html")
    public String getListsDiscount(Model model, Principal principal,
                           @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                           @RequestParam(name = "page_size", required = false, defaultValue = "12") String page_size,
                           @RequestParam(value = "utilities", required = false) Set<String> utilities
    )
    {
        // if(principal != null) {
        //     String username = principal.getName();
        //     User user = this.userService.findByUsername(username);
        //     if(user != null && !user.getType().equals("ADMIN")) {
        //         return "redirect:/";
        //     }
        // } else return "redirect:/";
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        List<Restaurant> lists = this.restaurantService.getAllByCondition(Integer.parseInt(page )- 1,
                Integer.parseInt(page_size), "ACTIVE", "", "",
                "RESTAURANT", "", null, "", "HOT", "", "");

        Integer total = this.restaurantService.countAllByCondition("ACTIVE", "", "", "RESTAURANT",
                "", null, "", "HOT", "", "");
        System.out.println(total);
        model.addAttribute("lists", lists);
        model.addAttribute("type", "DISCOUNT-RESTAURANT");
        model.addAttribute("location_id", "");
        model.addAttribute("price_id", "");
        
        String params = "";
//        if(!location_id.isEmpty()) params += "&location_id="+location_id;
//        if(!name.isEmpty()) params += "&name="+name;
//        if(!price_id.isEmpty()) params += "&price_id="+price_id;
        int totalPage = 0;
        if(total != null) {
            totalPage = (int) Math.ceil((double) total / Integer.parseInt(page_size));;
        }
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("total", total);
        model.addAttribute("page", parseInt(page));
        model.addAttribute("page_size", page_size);

        model.addAttribute("url", "/discount-restaurant.html");
        model.addAttribute("params", params);
        model.addAttribute("utilities", utilities);
        
        return "pages/fe/restaurants/restaurant";
    }

    @GetMapping("/restaurant-{id}.html")
    public String showData(Model model, Principal principal,
                           @PathVariable("id") Integer id,
                           HttpServletRequest request,
                           @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                           @RequestParam(name = "page_size", required = false, defaultValue = "3") String page_size,
                           @RequestParam(value = "utilities", required = false) Set<String> utilities
    )
    {
        Order orderData = new Order();
        orderData.setStatus("PENDING");
        orderData.setPaymentStatus("UNPAID");
        model.addAttribute("type", "RESTAURANT");
         if(principal != null) {
             String username = principal.getName();
             User user = this.userService.findByUsername(username);
             if(user != null) {
                 orderData.setReceiverName(user.getName());
                 orderData.setReceiverPhone(user.getPhone());
                 orderData.setReceiverEmail(user.getEmail());
                 orderData.setReceiverAddress(user.getAddress());
                 orderData.setUserId(user.getId());
             }
         }
        Restaurant data = this.restaurantService.findById(Long.parseLong(id + ""));
        List<String> categories = new ArrayList<>();
        if(data != null ) {
            orderData.setRestaurantName(data.getName());
            orderData.setPrice(data.getPrice());
            orderData.setLogo(data.getLogo());

            if(!data.getCategories().isEmpty()) {
                for (RestaurantCategory item: data.getCategories()) {
                    if(item.getCategory() != null) {
                        Integer d = item.getCategory().getId();
                        categories.add(d + "" );
                    }
                }
            }
        }
        String joinedString = categories.stream().collect(Collectors.joining(","));
        System.out.println(joinedString);
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        List<Restaurant> lists = this.restaurantService.getAllByCondition(Integer.parseInt(page )- 1,
                Integer.parseInt(page_size), "ACTIVE", "", "", "RESTAURANT", "",
                joinedString, "", "", "", "");

        Integer total = this.restaurantService.countAllByCondition("ACTIVE", "", "", "RESTAURANT", "",
                null, "", "", "", "");


        model.addAttribute("data", data);
        System.out.println("Data: ------------" + data.getDiscountRegulation());
        model.addAttribute("lists", lists);
        model.addAttribute("total", total);


        model.addAttribute("page", Integer.parseInt(page ));

        model.addAttribute("page_size", Integer.parseInt(page_size));
        model.addAttribute("orderData", orderData);

        model.addAttribute("timeDataBooking", null);
        model.addAttribute("dateBooking", null);
        model.addAttribute("page_size", Integer.parseInt(page_size));
        if(data.getUtilities() == null) {
        	System.out.println("dataa----"+ data.getUtilities());
        }
        model.addAttribute("utilities", data.getUtilities());
        model.addAttribute("error", null);
        String currentUrl = request.getRequestURL().toString();
        model.addAttribute("currentUrl", currentUrl);
//        model.addAttribute("today", LocalDate.now());

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        model.addAttribute("today", formattedDate);

        return "pages/fe/restaurants/restaurant-detail";
    }

    @PostMapping("/restaurant-booking-{id}.html")
    public String booking(Model model, Principal principal,
                           @PathVariable("id") Integer id,
                          @ModelAttribute("orderData") Order data,
                          @RequestParam("dateBooking") String dateBooking,
                          @RequestParam("timeDataBooking") String timeDataBooking,
                          @RequestParam("discount") Optional<Float> discount
                          
    ) {
        String error = null;
        Restaurant restaurant = this.restaurantService.findById(Long.parseLong(id + ""));
        try {
            model.addAttribute("type", "RESTAURANT");
            data.setStatus("PENDING");
            data.setPaymentStatus("UNPAID");
            data.setType("BOOKING");
            data.setRestaurantId(id);
            data.setCreatedAt(new Date());
            data.setUpdatedAt(new Date());
            if(principal != null) {
                String username = principal.getName();
                User user = this.userService.findByUsername(username);
                model.addAttribute("user", user);
                if(user != null) {
                    data.setUserId(user.getId());
                }
            }

         

            if(restaurant != null ) {
                data.setRestaurantName(restaurant.getName());
                data.setPrice(restaurant.getPrice());
                data.setTotalPrice(restaurant.getPrice());
                data.setLogo(restaurant.getLogo());
            }

            String dateData = dateBooking + " " + timeDataBooking;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalDateTime dateTime = LocalDateTime.parse(dateData, formatter);
            System.out.println("Parsed LocalDateTime: " + dateTime);

            data.setTimeBooking(dateTime);

            // Lưu discount vào Order nếu có
            if (discount.isPresent()) {
                data.setDiscount(discount.get());
            }
            
            if (this.orderService.create(data)) {
                return "redirect:/restaurant-" + id+".html";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", "Booking failed: " + e.getMessage());
        }
        List<String> categories = new ArrayList<>();
        if (data != null) {

            if (!restaurant.getCategories().isEmpty()) {
                for (RestaurantCategory item : restaurant.getCategories()) {
                    if (item.getCategory() != null) {
                        Integer d = item.getCategory().getId();
                        categories.add(d + "");
                    }
                }
            }
        }
        String joinedString = categories.stream().collect(Collectors.joining(","));
        System.out.println(joinedString);
        List<Restaurant> lists = this.restaurantService.getAllByCondition(0,
                5, "ACTIVE", "", "", "RESTAURANT", "", joinedString,
                "", "", "", "");

        Integer total = this.restaurantService.countAllByCondition("ACTIVE", "", "",
                "RESTAURANT", "",
                null, "", "", "", "");


        model.addAttribute("data", restaurant);
        model.addAttribute("lists", lists);
        model.addAttribute("total", total);
        model.addAttribute("orderData", data);
        model.addAttribute("timeDataBooking", timeDataBooking);
        model.addAttribute("dateBooking", dateBooking);

        return "pages/fe/restaurants/restaurant-detail";
    }

    @GetMapping("/restaurant-favorite/{id}")
    public String favorite(Model model, @PathVariable("id") Long id,
                           Principal principal, HttpServletRequest request
    )
    {
        System.out.println("id-----------> "+ id);
        model.addAttribute("type", "RESTAURANT");
         if(principal != null) {
             String username = principal.getName();
             User user = this.userService.findByUsername(username);
             if(user != null) {
                 model.addAttribute("user", user);
                 Restaurant restaurant = this.restaurantService.findById(id);
                 UserRestaurant data = this.userRestaurantRepository.findByUserRestaurant(id, user.getId());

                 if(data == null) {
                     UserRestaurant newData = new UserRestaurant();
                     newData.setRestaurant(restaurant);
                     newData.setUser(user);
                     this.userRestaurantRepository.save(newData);
                 }else {
                     this.userRestaurantRepository.delete(data);
                 }
                 String referer = request.getHeader("Referer");
                 return "redirect:/favourite.html";
             }
         }
        return "redirect:/login.html";

    }

    @GetMapping("/favourite.html")
    public String getJobFavourite(Model model, Principal principal, HttpServletRequest request) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null) {
                List<UserRestaurant> dataList = this.userRestaurantRepository.getByUser(user.getId());
                model.addAttribute("dataList", dataList);
                model.addAttribute("user", user);
                Integer totalCooperation = this.cooperationService.countByCondition(user.getId() + "",  "",  "APPROVED");
                model.addAttribute("totalCooperation", totalCooperation);
                return "pages/fe/user/favorite";
            }
        }
        return "redirect:/login.html";
    }





}