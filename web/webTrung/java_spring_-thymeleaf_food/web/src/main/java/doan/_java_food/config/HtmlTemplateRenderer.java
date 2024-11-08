package doan._java_food.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.ui.Model;

@Service
public class HtmlTemplateRenderer {

    @Autowired
    private TemplateEngine templateEngine;

    public String renderTemplate(String templateName, Model model) {
        Context context = new Context();
        model.asMap().forEach(context::setVariable);
        return templateEngine.process(templateName, context);
    }
}
