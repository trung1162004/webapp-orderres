package doan._java_food.controller.auth;

import doan._java_food.models.User;
import doan._java_food.service.EmailService;
import doan._java_food.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/register.html")
    public String register(Model model)
    {
        model.addAttribute("messageError", null);
        model.addAttribute("messageSuccess", null);
        model.addAttribute("user", new User());
        return "pages/fe/register";
    }

    @RequestMapping("/active/{email}")
    public String activeAccount(Model model, @PathVariable("email") String email)
    {
        System.out.println("========= EMAIL ACTIVE: " + email);
        String messageError = "";
        String messageSuccess = "";

        User userActive = this.userService.findByUserEmail(email);
        if (userActive == null) {
            messageError = "Tài khoản không tồn tại";
        }else{
            userActive.setStatus("ACTIVE");
            this.userService.update(userActive);
            messageSuccess = "Active tài khoản thành công, xin vui lòng đăng nhập";
        }

        model.addAttribute("messageError", messageError);
        model.addAttribute("messageSuccess", messageSuccess);
        return "pages/fe/login";
//        return "redirect:/login.html";
    }


    @PostMapping("/register.html")
    public String save(@ModelAttribute("user") User user, Model model)
    {
        user.setStatus("INACTIVE");
        String messageError = null;
        String messageSuccess = null;
        try {
            String email = user.getEmail();
            User checkUser = this.userService.findUserByEmail(email);
            System.out.println("========== USER BY EMAIL: " + checkUser);
            if (checkUser != null) {
                messageError = "Email existed";
                System.out.println("============= ALERT =================" + messageError);
                System.out.println("============= ALERT messageSuccess=================" + messageSuccess);
                model.addAttribute("messageError", messageError);

                return "pages/fe/register";
//                return "redirect:/register.html";
            }
            if(user.getUsername() != null) {
                User checkUserUsername = this.userService.findByUsername(user.getUsername());
                if (checkUserUsername != null) {
                    messageError = "Username existed";
                    System.out.println("============= ALERT =================" + messageError);
                    model.addAttribute("messageError", messageError);

                    return "pages/fe/register";
//                return "redirect:/register.html";
                }
            }
            if(user.getType() == null) {
                user.setType("USER");
            }
            if (this.userService.createData(user)) {
                Map<String, Object> model2 = new HashMap<>();
                model2.put("name", user.getName());
                model2.put("link", "http://localhost:3013/active/" + user.getEmail());
                this.emailService.sendEmail(user.getEmail(),"Xác nhận đăng ký tài khoản",model2,"pages/mail/active_account");
                model.addAttribute("messageSuccess", "Register successfully");
                return "pages/fe/register";
            }
            messageError = "Error: Register failed";
        }catch (Exception e) {
            messageError = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        model.addAttribute("messageError", messageError);
        System.out.println(messageError);
        model.addAttribute("user", user);

        return "pages/fe/register";
    }

    @RequestMapping("/forgot-password")
    public String forgotPassword() {
        return "pages/fe/forgot-password";
    }


    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        User user = userService.findByResetToken(token);
        if (user == null) {
            model.addAttribute("error", "Invalid token.");
            return "pages/fe/forgot-password";
        }
        model.addAttribute("token", token);
        return "pages/fe/reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "pages/fe/reset-password";
        }

        User user = userService.findByResetToken(token);
        if (user == null) {
            model.addAttribute("error", "Invalid token.");
            return "pages/fe/reset-password";
        }

        userService.updatePassword(user, newPassword);
        model.addAttribute("success", "Password updated successfully.");
        return "redirect:/login.html?success=Password updated successfully.";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam("email") String email, Model model) {
        boolean result = this.userService.sendResetPasswordEmail(email);
        if (result) {
            model.addAttribute("success", "A password reset link has been sent to your email.");
        } else {
            model.addAttribute("error", "Email address not found.");
        }
        return "pages/fe/forgot-password";
    }
}
