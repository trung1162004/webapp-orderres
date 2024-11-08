package doan._java_food.controller.fe;

import doan._java_food.models.Blog;
import doan._java_food.models.Category;
import doan._java_food.models.User;
import doan._java_food.service.Blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/")
public class FeBlogController {

    @Autowired
    private BlogService blogService;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @RequestMapping("blog")
    public String homeIndex(Model model,
                            @RequestParam(value = "error", required = false) String error) {

        String from_date = LocalDate.now().minusWeeks(1).atStartOfDay().format(dateFormatter);
        String to_date = LocalDate.now().format(dateFormatter);
        System.out.println("from_date-------> " + from_date);
        System.out.println("to_date-------> " + to_date);
        List<Blog> list = this.blogService.findAndCount(0, 20,
                "ACTIVE","","","" );
        List<Blog> listHot = this.blogService.findAndCount(0, 5, "ACTIVE","",
                "","HOT"  );

        model.addAttribute("list", list);
        model.addAttribute("type", "BLOG");
        model.addAttribute("listHot", listHot);
        return "pages/fe/blog/index";
    }

    @GetMapping("/blog-{id}.html")
    public String getListCategory(Model model, @PathVariable("id") Long id)
    {
        Blog data = this.blogService.findById(id);
        List<Blog> listHot = this.blogService.findAndCount(0, 5, "ACTIVE","",
                "","HOT"  );
        model.addAttribute("data", data);
        model.addAttribute("type", "BLOG");
        model.addAttribute("listHot", listHot);
        return "pages/fe/blog/detail";
    }
}
