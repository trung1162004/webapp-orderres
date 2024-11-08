package doan._java_food.controller.admin;

import doan._java_food.models.User;
import doan._java_food.repository.OrderRepository;
import doan._java_food.service.User.UserService;
import doan._java_food.service.UserService.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private UserService userService;


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UsersServices usersServices;

    @GetMapping()
    public String index(Principal principal) {
        return "redirect:/admin/";
    }
    @GetMapping()
    @RequestMapping("/")
    public String getHomeAdm(Model model, Principal principal) {
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

            Integer totalUserCompany = this.userService.countTotalUser("RESTAURANT");
            Integer totalUser = this.userService.countTotalUser("USER");
            Float totalOrderApprovedBooking = this.orderRepository.sumOrderByTypeAndStatus("", "BOOKING");
            Float totalOrderApprovedRegister = this.usersServices.sumTotalPrice("Still valid", "");
            Float totalSalesThisMonth = this.usersServices.getSalesThisMonth();
            Integer totalPackagesThisMonth = this.usersServices.getPackagesThisMonth();
            
            

            model.addAttribute("totalUserCompany", totalUserCompany);
            model.addAttribute("totalUser", totalUser);
            model.addAttribute("totalOrderApprovedBooking", totalOrderApprovedBooking);
            model.addAttribute("totalOrderApprovedRegister", totalOrderApprovedRegister);
            model.addAttribute("totalSalesThisMonth", totalSalesThisMonth);
            model.addAttribute("totalPackagesThisMonth", totalPackagesThisMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/home/index";
    }
}