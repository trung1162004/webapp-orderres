package doan._java_food.controller.fe;

import doan._java_food.models.Category;
import doan._java_food.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categoriesGlobal")
    public List<Category> getCategories() {
        return this.categoryService.getAll();
    }
}
