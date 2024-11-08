package doan._java_food.controller.admin;

import doan._java_food.models.Category;
import doan._java_food.models.User;
import doan._java_food.service.Category.CategoryService;
import doan._java_food.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @Autowired
    private UserService userService;

    @GetMapping("/category")
    public String getLists(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        List<Category> lists = this.service.getAll();
        model.addAttribute("lists", lists);
        return "admin/category/index";
    }

    @GetMapping("/category/create")
    public String create(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        Category data = new Category();
        model.addAttribute("data", data);
//        if(data.getStatus().isEmpty()) {
            data.setStatus("ACTIVE");
//        }

        return "admin/category/create";
    }

    @PostMapping("/category/create")
    public String save(@ModelAttribute("data")Category dataRequest, @RequestParam("file") MultipartFile fileAvatar, Principal principal)
    {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";

        if (this.service.create(dataRequest, fileAvatar)) {
            return "redirect:/admin/category";
        }
        return "admin/category/create";
    }

    @GetMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        Category data = this.service.findById(id);
        model.addAttribute("data", data);

        return "admin/category/update";
    }

    @PostMapping("/category/edit")
    public String update(@ModelAttribute("data")Category data, @RequestParam("file") MultipartFile fileAvatar){
        Integer id = data.getId();
        Category dataUpdate = this.service.findById(Long.parseLong(id + ""));
        if(dataUpdate.getImage() != null) data.setImage(dataUpdate.getImage());
        if (this.service.update(data, fileAvatar)) {
            return "redirect:/admin/category";
        }
        return "redirect:/admin/category/edit/"+data.getId();
    }

    @GetMapping("/category/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        this.service.delete(id);
        return "redirect:/admin/category";
    }
}
