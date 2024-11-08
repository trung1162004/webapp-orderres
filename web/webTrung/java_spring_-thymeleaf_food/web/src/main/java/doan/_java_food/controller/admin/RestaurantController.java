package doan._java_food.controller.admin;

import doan._java_food.models.*;
import doan._java_food.repository.RestaurantCategoryRepository;
import doan._java_food.service.Category.CategoryService;
import doan._java_food.service.Restaurant.RestaurantService;
import doan._java_food.service.Storage.StorageService;
import doan._java_food.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StorageService storageService;


    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String FLOAT_PATTERN = "^-?\\d+(\\.\\d+)?$";

    public static boolean isValidFloat(String input) {
        Pattern pattern = Pattern.compile(FLOAT_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    @GetMapping("/restaurant")
    public String getLists(Model model, Principal principal,
                           @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                           @RequestParam(name = "page_size", required = false, defaultValue = "200") String page_size,
                           @RequestParam(value = "utilities", required = false) Set<String> utilities
                           )
    {
         if(principal != null) {
             String username = principal.getName();
             User user = this.userService.findByUsername(username);
             if(user != null && !user.getType().equals("ADMIN")) {
                 return "redirect:/";
             }
         } else return "redirect:/";
        List<Restaurant> lists = this.restaurantService.getAllByCondition(Integer.parseInt(page )- 1,
                Integer.parseInt(page_size), "", "",
                "", "", "",  null,
                "", "", "", "");
        Integer total = this.restaurantService.countAllByCondition("", "",
                "", "", "", null, "", "", "", "");
        System.out.println(total);
        model.addAttribute("lists", lists);
        model.addAttribute("total", total);
        model.addAttribute("type", "RESTAURANT");
        model.addAttribute("page", Integer.parseInt(page ));
        model.addAttribute("page_size", Integer.parseInt(page_size));
        model.addAttribute("utilities", utilities);
        return "admin/restaurant/index";
    }

    @GetMapping("/restaurant-partner")
    public String getListRestaurantPartner(Model model, Principal principal,
                                           @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                           @RequestParam(name = "page_size", required = false, defaultValue = "10") String page_size,
                                           @RequestParam(value = "utilities", required = false) Set<String> utilities
    )
    {
         if(principal != null) {
             String username = principal.getName();
             User user = this.userService.findByUsername(username);
             if(user != null && !user.getType().equals("ADMIN")) {
                 return "redirect:/";
             }
         } else return "redirect:/";
        List<Restaurant> lists = this.restaurantService.getAllByCondition(Integer.parseInt(page )- 1,
                Integer.parseInt(page_size), "", "", "", "REGISTER", "",
                null, "", "", "", "");
        Integer total = this.restaurantService.countAllByCondition("", "", "",
                "REGISTER", "", null, "", "", "", "");
        model.addAttribute("lists", lists);
        model.addAttribute("type", "PARTNER");
        model.addAttribute("total", total);
        model.addAttribute("page", Integer.parseInt(page ) - 1);
        model.addAttribute("page_size", Integer.parseInt(page_size));
        model.addAttribute("utilities", utilities);
        return "admin/restaurant/index";
    }

    @GetMapping("/restaurant/create")
    public String create(Model model, Principal principal) {
         if(principal != null) {
             String username = principal.getName();
             User user = this.userService.findByUsername(username);
             if(user != null && !user.getType().equals("ADMIN")) {
                 return "redirect:/";
             }
         } else return "redirect:/";
        Restaurant data = new Restaurant();
        data.setStatus("ACTIVE");
        model.addAttribute("data", data);
        if(data.getHot() == null) {
            data.setHot("DEFAULT");
        }
        if(data.getHot_discount() == null) {
            data.setHot("DEFAULT");
        }
        List<User> users = userService.findUserByType("RESTAURANT");
        model.addAttribute("users", users);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("categoryList", categories);



        model.addAttribute("messageError", "");

        return "admin/restaurant/create";
    }

    @PostMapping("/restaurant/create")
    public String save(@ModelAttribute("data") Restaurant data, Model model,
                       @RequestParam("file") MultipartFile fileAvatar,
                       @RequestParam("categoriesData") List<Long> categoriesData,
                       @RequestParam("fileMultiple") List<MultipartFile> images,
                       @RequestParam(value = "utilities", required = false) Set<String> utilities,
                       Principal principal) {

        String messageError = "";
        try {
            if (principal != null) {
                String username = principal.getName();
                User user = this.userService.findByUsername(username);
                if (user != null && !user.getType().equals("ADMIN")) {
                    return "redirect:/";
                }
            } else {
                return "redirect:/";
            }
            model.addAttribute("categoriesData", categoriesData);

            data.setType("RESTAURANT");
            data.setUtilities(utilities);

            // Replace newlines with <br> in depositRegulation
            if (data.getDepositRegulation() != null) {
            	System.out.println("data---------" +data.getDepositRegulation());
                String formattedDepositRegulation = data.getDepositRegulation().replaceAll("[\\t\\n\\r]+","</br>");
                data.setDepositRegulation(formattedDepositRegulation);
            }

            if (data.getDiscount() != null && data.getDiscount() > 0) {
                data.setHot_discount("HOT");
            } else {
                data.setHot_discount("DEFAULT");
            }

            if (categoriesData.size() > 0) {
                if (!images.isEmpty()) {
                    List<String> imgs = new ArrayList<>();
                    for (MultipartFile item : images) {
                        String itemImg = this.storageService.storeImage(item);
                        if (!itemImg.isEmpty()) {
                            imgs.add(itemImg);
                        }
                    }
                    data.setImages(imgs);
                }
                if (this.restaurantService.create(data, fileAvatar)) {
                    return "redirect:/admin/restaurant";
                }
            } else {
                messageError = "Category is required";
            }

            List<User> users = userService.findUserByType("RESTAURANT");
            model.addAttribute("data", data);
            List<Category> categories = categoryService.getAll();
            model.addAttribute("users", users);
            model.addAttribute("categoryList", categories);

        } catch (Exception e) {
            messageError = "Có lỗi xảy ra: " + e.getMessage();
        }
        model.addAttribute("messageError", messageError);

        return "admin/restaurant/create";
    }


    @GetMapping("/restaurant/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id,
                       Principal principal) {
         if(principal != null) {
             String username = principal.getName();
             User user = this.userService.findByUsername(username);
             if(user != null && !user.getType().equals("ADMIN")) {
                 return "redirect:/";
             }
         } else return "redirect:/";

        System.out.println(1);
        Restaurant data = this.restaurantService.findById(id);
        String startFormat = null;
        if(data.getStartDate() != null) {
            startFormat = data.getStartDate().format(dateFormatter);

        }
        String endFormat = null;
        if(data.getStartDate() != null) {
            endFormat = data.getEndDate().format(dateFormatter);

        }
        if(data.getHot() == null) {
            data.setHot("DEFAULT");
        }
        if(data.getHot_discount() == null) {
            data.setHot("DEFAULT");
        }
        List<Location> locations = this.restaurantService.getAllLocation();
        model.addAttribute("locations", locations);
        model.addAttribute("startDateFormat", startFormat);
        model.addAttribute("endDateFormat", endFormat);
        model.addAttribute("data", data);
        //mới thêm
        Set<String> utilities = data.getUtilities();
        model.addAttribute("utilities", utilities);
        
        List<User> users = userService.findUserByType("RESTAURANT");
        List<Category> categories = categoryService.getAll();
        if(categories.size() > 0 && !data.getCategories().isEmpty()) {
            for (Category item: categories) {
                for (RestaurantCategory itemCheck: data.getCategories()) {
                    if(itemCheck.getCategory() != null && item.getId() == itemCheck.getCategory().getId()){
                        item.setChecked(true);
                    }
                }
            }
        }
        model.addAttribute("users", users);
        model.addAttribute("categoryList", categories);
        model.addAttribute("messageError", "");


        return "admin/restaurant/update";
    }






    @PostMapping("/restaurant/edit")
    public String update(@ModelAttribute("data")Restaurant data, Model model,
                         Principal principal,
                         @RequestParam("file") MultipartFile fileAvatar,
                         @RequestParam("categoriesData") List<Long> categoriesData,
                         @RequestParam("fileMultiple") List<MultipartFile> images,
                         @RequestParam(value = "utilities", required = false) Set<String> utilities,
                         String startDateFormat, String endDateFormat
                         ){

        String messageError = "";
        try {
             if(principal != null) {
             	String username = principal.getName();
             	User user = this.userService.findByUsername(username);
             	if(user != null && !user.getType().equals("ADMIN")) {
             		return "redirect:/";
             	}
             } else {
                 return "redirect:/";
             }

            model.addAttribute("categoriesData", categoriesData);
            Integer id = data.getId();
            Restaurant dataUpdate = this.restaurantService.findById(Long.parseLong(id + ""));
            if(dataUpdate.getLogo() != null) data.setLogo(dataUpdate.getLogo());
            data.setType(dataUpdate.getType());
            if(startDateFormat != null) {
                data.setStartDate(LocalDate.parse(startDateFormat));
            }
            if(endDateFormat != null) {
                data.setEndDate(LocalDate.parse(endDateFormat));
            }
            data.setUtilities(utilities); 

            List<Location> locations = this.restaurantService.getAllLocation();
            model.addAttribute("locations", locations);

            if(data.getDiscount() != null && data.getDiscount() > 0) {
                data.setHot_discount("HOT");
            } else {
                data.setHot_discount("DEFAULT");
            }

            if(categoriesData != null && categoriesData.size() > 0) {
                if(!images.isEmpty()) {
                    List<String> imgs = new ArrayList<>();
                    for (MultipartFile item:images ) {
                        String itemImg = this.storageService.storeImage(item);
                        if(itemImg != null) {
                            imgs.add(itemImg);
                        }
                    }
                    data.setImages(imgs);
                }
                if (this.restaurantService.update(data, fileAvatar)) {
                    try{
                        this.restaurantCategoryRepository.deleteByRestaurantId(id);
                    }catch (Exception ignored) {

                    }
                    for (Long item: categoriesData) {
                        Category c = this.categoryService.findById(item);
                        RestaurantCategory itemData = new RestaurantCategory();
                        itemData.setCategory(c);
                        itemData.setRestaurant(data);
                        this.restaurantCategoryRepository.save(itemData);
                    }
                    return "redirect:/admin/restaurant";
                }
            } else {
                messageError = "Category is required";
            }


            List<User> users = userService.findUserByType("RESTAURANT");
            List<Category> categories = categoryService.getAll();
            model.addAttribute("users", users);
            model.addAttribute("categoryList", categories);

            model.addAttribute("data", data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            messageError = "Có lỗi xảy ra: "+e.getMessage();

        }
        model.addAttribute("messageError", messageError);

        return "admin/restaurant/update";
    }

    @GetMapping("/restaurant/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        this.restaurantService.delete(id);
        return "redirect:/admin/restaurant";
    }

    @GetMapping("/restaurant/approved/{id}")
    public String rejected(Model model, @PathVariable("id") Long id, Principal principal) {
         if(principal != null) {
             String username = principal.getName();
             User user = this.userService.findByUsername(username);
             if(user != null && !user.getType().equals("ADMIN")) {
                 return "redirect:/";
             }
         } else return "redirect:/";
        Restaurant data = this.restaurantService.findById(id);
        if(data!=null) {
            data.setType("RESTAURANT");
            this.restaurantService.updateType(data);
        }

        return "redirect:/admin/restaurant-partner";
    }

    @GetMapping("/restaurant/rejected/{id}")
    public String approved(Model model, @PathVariable("id") Long id, Principal principal) {
         if(principal != null) {
             String username = principal.getName();
             User user = this.userService.findByUsername(username);
             if(user != null && !user.getType().equals("ADMIN")) {
                 return "redirect:/";
             }
         } else return "redirect:/";
        Restaurant data = this.restaurantService.findById(id);
        if(data!=null) {
            data.setStatus("REJECTED");
            this.restaurantService.updateType(data);
        }


        return "redirect:/admin/restaurant-partner";
    }
}
