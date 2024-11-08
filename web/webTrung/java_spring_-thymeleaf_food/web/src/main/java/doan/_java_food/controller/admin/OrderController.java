package doan._java_food.controller.admin;

import doan._java_food.models.Category;
import doan._java_food.models.Order;
import doan._java_food.models.User;
import doan._java_food.service.Category.CategoryService;
import doan._java_food.service.Order.OrderService;
import doan._java_food.service.User.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private UserService userService;

    @GetMapping("/order")
    public String getLists(Model model, Principal principal) {
//        if(principal != null) {
//            String username = principal.getName();
//            User user = this.userService.findByUsername(username);
//            if(user != null && !user.getType().equals("ADMIN")) {
//                return "redirect:/";
//            }
//        } else return "redirect:/";
        List<Order> lists = this.service.getAllByCondition(0, 100, "", "", "", "", "");
        model.addAttribute("lists", lists);
        return "admin/order/index";
    }



    @GetMapping("/order/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        Order data = this.service.findById(id);
        model.addAttribute("data", data);
        model.addAttribute("messageError", "");

        return "admin/order/update";
    }

    @PostMapping("/order/edit")
    public String update(@ModelAttribute("data")Order data){
        Integer id = data.getId();
        Order dataUpdate = this.service.findById(Long.parseLong(id + ""));
        if(data.getReceiverName() != null) {
            dataUpdate.setReceiverName(data.getReceiverName());
        }
        if(data.getReceiverEmail() != null) {
            dataUpdate.setReceiverEmail(data.getReceiverEmail());
        }
        if(data.getReceiverPhone() != null) {
            dataUpdate.setReceiverPhone(data.getReceiverPhone());
        }
        if(data.getReceiverAddress() != null) {
            dataUpdate.setReceiverAddress(data.getReceiverAddress());
        }
        if(data.getRestaurantName() != null) {
            dataUpdate.setRestaurantName(data.getRestaurantName());
        }
        if(data.getTotalPrice() != null) {
            dataUpdate.setTotalPrice(data.getTotalPrice());
        }
        if(data.getStatus() != null) {
            dataUpdate.setStatus(data.getStatus());
        }
        if(data.getNote() != null) {
            dataUpdate.setNote(data.getNote());
        }

        if (this.service.update(dataUpdate)) {
            return "redirect:/admin/order";
        }
        return "redirect:/admin/order/edit/"+data.getId();
    }

    @GetMapping("/order/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        this.service.delete(id);
        return "redirect:/admin/order";
    }
}
