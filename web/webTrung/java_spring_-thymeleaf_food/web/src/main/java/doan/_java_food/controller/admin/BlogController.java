package doan._java_food.controller.admin;

import doan._java_food.models.Blog;
import doan._java_food.models.User;
import doan._java_food.service.Blog.BlogService;
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
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/blog")
    public String getLists(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        List<Blog> lists = this.blogService.getAll();
        model.addAttribute("lists", lists);
        return "admin/blog/index";
    }

    @GetMapping("/blog/create")
    public String create(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        Blog data = new Blog();
        data.setStatus("ACTIVE");

        model.addAttribute("data", data);

        return "admin/blog/create";
    }

    @PostMapping("/blog/create")
    public String save(@ModelAttribute("data")Blog dataRequest, @RequestParam("file") MultipartFile fileAvatar, Principal principal)
    {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";

        if (this.blogService.create(dataRequest, fileAvatar)) {
            return "redirect:/admin/blog";
        }
        return "admin/blog/create";
    }

    @GetMapping("/blog/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id, Principal principal) {
        Blog data = this.blogService.findById(id);
//        String avatar = data.avatar ? data.avatar : "'default.jpg";
        model.addAttribute("data", data);
        return "admin/blog/update";
    }

    @PostMapping("/blog/edit")
    public String update(@ModelAttribute("data")Blog data, @RequestParam("file") MultipartFile fileAvatar){
        Integer id = data.getId();
        Blog dataUpdate = this.blogService.findById(Long.parseLong(id + ""));
        if(dataUpdate.getAvatar() != null) data.setAvatar(dataUpdate.getAvatar());
        if (this.blogService.update(data, fileAvatar)) {
            return "redirect:/admin/blog";
        }
        return "redirect:/admin/blog/create";
    }

    @GetMapping("/blog/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        this.blogService.delete(id);
        return "redirect:/admin/blog";
    }
}
