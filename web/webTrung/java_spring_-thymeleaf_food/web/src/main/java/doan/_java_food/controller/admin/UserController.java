package doan._java_food.controller.admin;

import doan._java_food.models.User;
import doan._java_food.service.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getLists(@RequestParam(value = "type", required = false) String type, 
                           @AuthenticationPrincipal User user, 
                           Model model, 
                           Principal principal) {

        // Kiểm tra quyền truy cập của người dùng
        if (principal != null) {
            String username = principal.getName();
            User userData = this.userService.findByUsername(username);
            if (userData != null && !userData.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }

        // Lấy danh sách người dùng dựa trên loại (nếu có)
        List<User> users;
        if (type != null && !type.isEmpty()) {
            users = userService.findUserByType(type);
        } else {
            users = userService.getAll();
        }

        // Thêm dữ liệu vào model
        model.addAttribute("lists", users);
        model.addAttribute("selectedType", type); // Đưa tham số type vào model

        return "admin/user/index";
    }

    @GetMapping("/create")
    public String create(Model model, Principal principal) {
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
            User data = new User();
            model.addAttribute("user", data);

        } catch (Exception e) {
            messageError = "Có lỗi xảy ra";
        }
        model.addAttribute("messageError", messageError);
        return "admin/user/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("user") User data, Model model, Principal principal) {
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
            if (this.userService.createData(data)) {
                return "redirect:/admin/user";
            }
            model.addAttribute("user", data);

        } catch (Exception e) {
            messageError = "Có lỗi xảy ra: " + e.getMessage();
        }
        model.addAttribute("messageError", messageError);
        return "admin/user/create";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id, Principal principal) {
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
            User job = userService.findById(id);
            if (job != null) {
                model.addAttribute("user", job);
            } else {
                throw new RuntimeException("Không tìm thấy Tài khoản");
            }
        } catch (Exception e) {
            messageError = "Có lỗi xảy ra: " + e.getMessage();
        }
        model.addAttribute("messageError", messageError);
        return "admin/user/update";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User job, Model model) {
        String messageError = "";
        try {
            if (this.userService.update(job)) {
                return "redirect:/admin/user";
            }

            model.addAttribute("user", job);

        } catch (Exception e) {
            messageError = "Có lỗi xảy ra: " + e.getMessage();
        }
        model.addAttribute("messageError", messageError);
        return "admin/user/update";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        try {
            // Xử lý xóa người dùng
            this.userService.delete(id);
        } catch (Exception e) {
            System.out.println("error delete--------> " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/user";
    }
}
