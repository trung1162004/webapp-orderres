package doan._java_food.controller.fe;

import doan._java_food.models.*;
import doan._java_food.repository.RestaurantCategoryRepository;
import doan._java_food.service.Category.CategoryService;
import doan._java_food.service.Order.OrderService;
import doan._java_food.service.Restaurant.RestaurantService;
import doan._java_food.service.Storage.StorageService;
import doan._java_food.service.User.UserService;
import doan._java_food.service.UserService.UsersServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/")
public class FeUserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UsersServices cooperationService;
    @Autowired
    private UsersServices usersServices;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RestaurantCategoryRepository restaurantCategoryRepository;

    private String defaultIfEmpty(String content) {
        return (content == null || content.trim().isEmpty()) ? "Content is updating!" : content;
    }

    private String formatTextareaContent(String content) {
        if (content != null) {
            return content.replaceAll("[\\t\\n\\r]+", "</br>");
        }
        return null;
    }
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @RequestMapping("/update-info.html")
    public String getProfile(Principal principal, Authentication auth, Model model, HttpServletRequest request)
    {
        model.addAttribute("messageSuccess", "");
        if(principal != null) {
            String usernames = principal.getName();
            User users = this.userService.findByUsername(usernames);
            model.addAttribute("user", users);
            String dobFormat = null;
            if(users != null && users.getDob() != null) {
                dobFormat = users.getDob().format(dateFormatter);
            }
            LocalDate currentDate = LocalDate.now();
            model.addAttribute("maxDate", currentDate);
            Integer totalCooperation = this.cooperationService.countByCondition(users.getId() + "",  "",  "APPROVED");
            model.addAttribute("totalCooperation", totalCooperation);

            model.addAttribute("dobFormat", dobFormat);
            String currentUrl = request.getRequestURI();
            model.addAttribute("currentUrl", currentUrl);
            return "pages/fe/user/profile";
        }

        return "redirect:/login.html";
    }

    @PostMapping("/user/update")
    public String updateProfile(Principal principal, @ModelAttribute("user") User user,
                                @RequestParam("file") MultipartFile fileAvatar,
                                @RequestParam("dobFormat") String dobFormat,
                                Model model
    )
    {
        String username = principal.getName();

        User userLogin = this.userService.findByUsername(username);
        LocalDate currentDate = LocalDate.now();
        model.addAttribute("maxDate", currentDate);
        if(userLogin != null) {
            userLogin.setEmail(user.getEmail());
            userLogin.setName(user.getName());
            userLogin.setAddress(user.getAddress());
            userLogin.setGender(user.getGender());
            userLogin.setPhone(user.getPhone());
            Integer totalCooperation = this.cooperationService.countByCondition(user.getId() + "",  "",  "APPROVED");
            model.addAttribute("totalCooperation", totalCooperation);
            if(dobFormat != null && !dobFormat.isEmpty()) {
                userLogin.setDob(LocalDate.parse(dobFormat));
            }
            this.userService.updateInfo(userLogin, fileAvatar);
            model.addAttribute("messageSuccess", "Update profile successfull");

        }

        return "redirect:/update-info.html";
    }

    @RequestMapping("/update-password.html")
    public String getPassword(Principal principal, Authentication auth, Model model)
    {
        model.addAttribute("messageSuccess", "");
        model.addAttribute("messageError", "");
        model.addAttribute("error", null);
        if(principal != null) {
            String usernames = principal.getName();
            User users = this.userService.findByUsername(usernames);
            model.addAttribute("user", users);
            if(users != null) {
                Integer totalCooperation = this.cooperationService.countByCondition(users.getId() + "",  "",  "APPROVED");
                model.addAttribute("totalCooperation", totalCooperation);
            }

        }else {
            return "redirect:/login.html";
        }

        return "pages/fe/user/change_password";
    }

    @PostMapping("/update-password")
    public String changePassword(Principal principal,
                                 Authentication auth,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Model model)
    {
        String messageError = "";
        String messageSuccess = "";
        try {
            if(principal != null) {
                String usernames = principal.getName();
                User users = this.userService.findByUsername(usernames);

                if(users != null) {
                    if (!passwordEncoder.matches(oldPassword, users.getPassword())) {
                        model.addAttribute("messageSuccess", "");
                        model.addAttribute("messageError", "");
                        model.addAttribute("error", "Old password is incorrect.");
                        return "pages/fe/user/change_password";
                    }
                    users.setPassword(passwordEncoder.encode(newPassword));
                    this.userService.update(users);
                    Integer totalCooperation = this.cooperationService.countByCondition(users.getId() + "",  "",  "APPROVED");
                    model.addAttribute("totalCooperation", totalCooperation);

                    messageSuccess = "Change password successfully";
                    model.addAttribute("messageError", messageError);
                    model.addAttribute("messageSuccess", messageSuccess);


                    return "pages/fe/user/change_password";
                }
            }


        } catch (Exception e) {
            messageError = "Error: " + e.getMessage();
            model.addAttribute("messageError", messageError);

            e.printStackTrace();
        }
        model.addAttribute("messageError", messageError);
        model.addAttribute("messageSuccess", messageSuccess);
        System.out.println(messageError);
        model.addAttribute("messageSuccess", messageSuccess);
        return "pages/fe/user/change_password";
    }

    @RequestMapping("/booking-history.html")
    public String getOrder(Principal principal, Authentication auth, Model model,
                           @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                           @RequestParam(name = "page_size", required = false, defaultValue = "10") String page_size,
                           @RequestParam(name = "status", required = false, defaultValue = "") String status
                           )
    {

        if(principal != null) {
            String usernames = principal.getName();
            User users = this.userService.findByUsername(usernames);
            if(users != null) {
                int numberPage = Integer.valueOf(page);
                if(numberPage >= 1) {
                    numberPage -= 1;
                }
                List<Order> lists = this.orderService.getAllByCondition(numberPage, Integer.valueOf(page_size),
                        status, "", "", "",
                        users.getId() + "");

                Integer total = this.orderService.countAllByCondition(
                        status, "", "", "",
                        users.getId() + "");
                int totalPage = 0;
                if(total != null) {
                    totalPage = (int) Math.ceil((double) total / Integer.parseInt(page_size));;
                }
                model.addAttribute("lists", lists);
                model.addAttribute("user", users);
                model.addAttribute("status", status);
                model.addAttribute("totalPage", totalPage);
                model.addAttribute("total", total);
                model.addAttribute("page", parseInt(page));
                model.addAttribute("page_size", page_size);
                String params = "";
                if(!status.isEmpty()) params += "&status="+status;
                model.addAttribute("url", "/booking-history.html");
                model.addAttribute("params", params);
                Integer totalCooperation = this.cooperationService.countByCondition(users.getId() + "",  "",  "APPROVED");
                model.addAttribute("totalCooperation", totalCooperation);
                return "pages/fe/user/order";
            }
        }
        return "redirect:/login.html";

    }

    @GetMapping("/manage-restaurant.html")
    public String manageRestaurant(Model model,
                           Principal principal, HttpServletRequest request
    )
    {

        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null) {
                Restaurant data = new Restaurant();

                List<Restaurant> lists = this.restaurantService.getAllByCondition(0, 1,
                        "","", "", "", user.getId() + "", null, "", "", "", "");

                if(!lists.isEmpty()) {
                    for (Restaurant item: lists) {
                        BeanUtils.copyProperties(item, data);
                    }
                }

                if(data.getHot() == null) {
                    data.setHot("DEFAULT");
                }
                if(data.getHot_discount() == null) {
                    data.setHot("DEFAULT");
                }

                model.addAttribute("lists", lists);
                model.addAttribute("user", user);
                model.addAttribute("data", data);
                List<Location> locations = this.restaurantService.getAllLocation();
                List<Category> categories = categoryService.getAll();
                if(categories.size() > 0 && data.getCategories() != null) {
                    for (Category item: categories) {
                        for (RestaurantCategory itemCheck: data.getCategories()) {
                            if(itemCheck.getCategory() != null && item.getId() == itemCheck.getCategory().getId()){
                                item.setChecked(true);
                            }
                        }
                    }
                }
                model.addAttribute("categoryList", categories);
                model.addAttribute("user", user);
                model.addAttribute("locations", locations);
                model.addAttribute("messageError", "");
                model.addAttribute("messageSuccess", "");

                Integer totalCooperation = this.cooperationService.countByCondition(user.getId() + "",  "",  "Still valid");
                model.addAttribute("totalCooperation", totalCooperation);


                return "pages/fe/user/manage_restaurant";
            }
        }
        return "redirect:/login.html";

    }
    @PostMapping("/manage-restaurant/edit")
    public String update(@ModelAttribute("data") Restaurant data, Model model,
                         Principal principal,
                         @RequestParam("file") MultipartFile fileAvatar,
                         @RequestParam("categoriesData") List<Long> categoriesData,
                         @RequestParam("fileMultiple") List<MultipartFile> images,
                         @RequestParam(value = "utilities", required = false) Set<String> utilities
                         ) {
    	

        String messageError = "";
        String messageSuccess = "";
        try {
            if (principal != null) {
                String username = principal.getName();
                User user = this.userService.findByUsername(username);
                if (user == null) {
                    return "redirect:/";
                }
                List<Restaurant> lists = this.restaurantService.getAllByCondition(0, 1,
                        "", "", "", "", user.getId() + "", null, "", "", "", "");
                model.addAttribute("categoriesData", categoriesData);

                // Process textarea fields
                data.setDepositRegulation(formatTextareaContent(defaultIfEmpty(data.getDepositRegulation())));
                data.setDiscountRegulation(formatTextareaContent(defaultIfEmpty(data.getDiscountRegulation())));
                data.setPasgoTimeRegulation(formatTextareaContent(defaultIfEmpty(data.getPasgoTimeRegulation())));
                data.setReservationTimeRegulation(formatTextareaContent(defaultIfEmpty(data.getReservationTimeRegulation())));
                data.setHoldTimeRegulation(formatTextareaContent(defaultIfEmpty(data.getHoldTimeRegulation())));
                data.setMinimumGuestsRegulation(formatTextareaContent(defaultIfEmpty(data.getMinimumGuestsRegulation())));
                data.setInvoiceRegulation(formatTextareaContent(defaultIfEmpty(data.getInvoiceRegulation())));
                data.setServiceFeeRegulation(formatTextareaContent(defaultIfEmpty(data.getServiceFeeRegulation())));
                data.setBringInFeeRegulation(formatTextareaContent(defaultIfEmpty(data.getBringInFeeRegulation())));
                data.setFit(formatTextareaContent(defaultIfEmpty(data.getFit())));
                data.setSpecialDish(formatTextareaContent(defaultIfEmpty(data.getSpecialDish())));
                data.setSpace(formatTextareaContent(defaultIfEmpty(data.getSpace())));
                data.setParking(formatTextareaContent(defaultIfEmpty(data.getParking())));
                data.setFeature(formatTextareaContent(defaultIfEmpty(data.getFeature())));

                List<Category> categories = categoryService.getAll();
                if (data.getDiscount() != null && data.getDiscount() > 0) {
                    data.setHot_discount("HOT");
                } else {
                    data.setHot_discount("DEFAULT");
                }
                List<Location> locations = this.restaurantService.getAllLocation();
                model.addAttribute("locations", locations);
                model.addAttribute("categoryList", categories);
                model.addAttribute("user", user);
                model.addAttribute("data", data);

                Integer totalCooperation = this.cooperationService.countByCondition(user.getId() + "", "", "APPROVED");
                model.addAttribute("totalCooperation", totalCooperation);

                Integer id = 0;
                if (!lists.isEmpty()) {
                    for (Restaurant item : lists) {
                        id = item.getId();
                    }
                }
                data.setType("RESTAURANT");
                data.setUser(user);
                if (!images.isEmpty()) {
                    List<String> imgs = new ArrayList<>();
                    for (MultipartFile item : images) {
                        String itemImg = this.storageService.storeImage(item);
                        if (itemImg != null) {
                            imgs.add(itemImg);
                        }
                    }
                    data.setImages(imgs);
                }
                if (categoriesData != null && categoriesData.size() > 0) {
                    if (id == 0) {
                        data.setStatus("PENDING");
                        data.setCreatedAt(new Date());

                        if (this.restaurantService.create(data, fileAvatar)) {
                            List<Restaurant> listData = this.restaurantService.getAllByCondition(0, 1,
                                    "", "", "", "", user.getId() + "", null, "", "", "", "");
                            if (!listData.isEmpty()) {
                                for (Restaurant item : listData) {
                                    id = item.getId();
                                }
                                data.setId(id);
                                this.restaurantCategoryRepository.deleteByRestaurantId(id);
                                for (Long item : categoriesData) {
                                    Category c = this.categoryService.findById(item);
                                    RestaurantCategory itemData = new RestaurantCategory();
                                    itemData.setCategory(c);
                                    itemData.setRestaurant(data);
                                    this.restaurantCategoryRepository.save(itemData);
                                    messageSuccess = "Create restaurant successfully";
                                    model.addAttribute("messageError", messageError);
                                    model.addAttribute("messageSuccess", messageSuccess);
                                }
                            }

                            return "redirect:/manage-restaurant.html";
                        }
                    } else {
                        Restaurant dataUpdate = this.restaurantService.findById(Long.parseLong(id + ""));
                        data.setId(dataUpdate.getId());
                        data.setStatus(dataUpdate.getStatus());
                        data.setCreatedAt(dataUpdate.getCreatedAt());
                        data.setNote(dataUpdate.getNote());
                        if (dataUpdate.getLogo() != null) data.setLogo(dataUpdate.getLogo());
                        if (this.restaurantService.update(data, fileAvatar)) {
                            this.restaurantCategoryRepository.deleteByRestaurantId(id);
                            for (Long item : categoriesData) {
                                Category c = this.categoryService.findById(item);
                                RestaurantCategory itemData = new RestaurantCategory();
                                itemData.setCategory(c);
                                itemData.setRestaurant(data);
                                this.restaurantCategoryRepository.save(itemData);
                            }
                            messageSuccess = "Update restaurant successfully";
                            model.addAttribute("messageError", messageError);
                            model.addAttribute("messageSuccess", messageSuccess);
                            return "redirect:/manage-restaurant.html";
                        }
                    }
                } else {
                    messageError = "Category is required";
                }
            } else {
                return "redirect:/";
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            messageError = "Error: " + e.getMessage();
        }
        model.addAttribute("messageError", messageError);
        model.addAttribute("messageSuccess", messageSuccess);
        model.addAttribute("utilities", utilities);

        return "pages/fe/user/manage_restaurant";
    }

    @RequestMapping("/profile2.html")
    public String getProfile2(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if (user != null) {
            	
            	List<doan._java_food.models.UserService> services = usersServices.findByUserId(user.getId());
                // Thực hiện các xử lý cần thiết và thêm dữ liệu vào model
                model.addAttribute("user", user);
                model.addAttribute("services", services);
            }
        } else {
            return "redirect:/login.html";
        }
        return "pages/fe/user/profile2"; // Đảm bảo rằng file profile2.html tồn tại ở đường dẫn này
    }

}
