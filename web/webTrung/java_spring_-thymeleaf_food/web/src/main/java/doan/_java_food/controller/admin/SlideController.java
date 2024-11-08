package doan._java_food.controller.admin;

import doan._java_food.models.Slides;
import doan._java_food.models.User;
import doan._java_food.service.Slide.SlideService;
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
public class SlideController {
    @Autowired
    private SlideService slideService;

    @Autowired
    private UserService userService;

    @GetMapping("/slide")
    public String getLists(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        List<Slides> lists = this.slideService.getAll();
        model.addAttribute("lists", lists);
        return "admin/slide/index";
    }

    @GetMapping("/slide/create")
    public String create(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        Slides data = new Slides();
        data.setStatus("ACTIVE");
        model.addAttribute("data", data);


        return "admin/slide/create";
    }

    @PostMapping("/slide/create")
    public String save(@ModelAttribute("data")Slides dataRequest, @RequestParam("file") MultipartFile fileAvatar, Principal principal)
    {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";

        if (this.slideService.create(dataRequest, fileAvatar)) {
            return "redirect:/admin/slide";
        }
        return "admin/slide/create";
    }

    @GetMapping("/slide/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        Slides data = this.slideService.findById(id);
        model.addAttribute("data", data);

        return "admin/slide/update";
    }

    @PostMapping("/slide/edit")
    public String update(@ModelAttribute("data")Slides data, @RequestParam("file") MultipartFile fileAvatar){
        Integer id = data.getId();
        Slides dataUpdate = this.slideService.findById(Long.parseLong(id + ""));
        if(dataUpdate.getImage() != null) data.setImage(dataUpdate.getImage());
        if (this.slideService.update(data, fileAvatar)) {
            return "redirect:/admin/slide";
        }
        return "redirect:/admin/slide/create";
    }

    @GetMapping("/slide/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        this.slideService.delete(id);
        return "redirect:/admin/slide";
    }
}
