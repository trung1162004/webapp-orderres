	package doan._java_food.controller.auth;
	
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	import org.springframework.security.core.Authentication;
	import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	
	@Controller
	@RequestMapping("/")
	public class LoginController {
	
	    @RequestMapping("/login.html")
	    public String login( Model model, @RequestParam(value = "error", required = false) String error) {
	        String message = null;
	        model.addAttribute("errorMsg", message);
	
	        return "pages/fe/login";
	    }
	
	    @GetMapping("/logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	        new SecurityContextLogoutHandler().logout(request, response, authentication);
	
	        return "redirect:/";
	    }
	}
