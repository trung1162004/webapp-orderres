package doan._java_food.controller.fe;

import doan._java_food.models.User;
import doan._java_food.service.UserService.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import doan._java_food.service.EmailService;
import doan._java_food.service.User.PayPalService;
import doan._java_food.service.User.UserService;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@CrossOrigin(origins = "http://localhost:3013") // Replace with your frontend URL
@Controller
@RequestMapping("/")
public class FeCooperateController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersServices usersServices;
    
    @Autowired
    private EmailService emailService;


    @RequestMapping("cooperate")
    public String homeIndex(Model model, @RequestParam(value = "error", required = false) String error, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);

            doan._java_food.models.UserService data = new doan._java_food.models.UserService();
            if(user != null) {
                data.setName(user.getName());
                data.setPhone(user.getPhone());
                data.setEmail(user.getEmail());
            }
            model.addAttribute("data", data);
            model.addAttribute("user", user);
            return "pages/fe/user/cooperate";
        }
        return "redirect:/";

    }

    @Autowired
    private PayPalService payPalService; // Ensure PayPalService is correctly defined and autowired


    @Value("${paypal.success.url}")
    private String successUrl;

    @Value("${paypal.cancel.url}")
    private String cancelUrl;
    @PostMapping("/cooperate")
    @CrossOrigin(origins = "http://localhost:3013")
    public RedirectView save(@ModelAttribute("data") doan._java_food.models.UserService dataRequest, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if (user == null) {
                return new RedirectView("/login.html");
            }

            dataRequest.setUser(user);

            // Kiểm tra xem người dùng đã có gói dịch vụ hoạt động chưa
            List<doan._java_food.models.UserService> activeServices = usersServices.findActiveServiceByUser(user.getId());

            boolean canCreateNewPackage = true;
            for (doan._java_food.models.UserService activeService : activeServices) {
                // Kiểm tra nếu gói dịch vụ hiện tại đang hoạt động hoặc sắp hết hạn trong 10 ngày
                if ("Still valid".equals(activeService.getStatus()) &&
                    (activeService.getDeadline() != null && 
                     activeService.getDeadline().isBefore(LocalDateTime.now().plus(10, ChronoUnit.DAYS)) &&
                     activeService.getDeadline().isAfter(LocalDateTime.now()))) {
                    // Nếu gói dịch vụ đang hoạt động hoặc sắp hết hạn trong vòng 10 ngày, cho phép mua gói mới
                    canCreateNewPackage = true;
                    break;
                }
                // Nếu gói dịch vụ đã hết hạn, cho phép mua gói mới
                if (activeService.getDeadline() != null && activeService.getDeadline().isBefore(LocalDateTime.now())) {
                    canCreateNewPackage = true;
                    break;
                }
                // Nếu gói dịch vụ còn hiệu lực và không sắp hết hạn trong vòng 10 ngày
                canCreateNewPackage = false;
            }

            if (!canCreateNewPackage) {
                // Nếu không cho phép tạo gói dịch vụ mới
                return new RedirectView("/notification");
            }

            dataRequest.setStatus("Still valid");

            // Set the price, end date, and expired date based on the selected service type
            switch (dataRequest.getService_type()) {
            case 1:
                dataRequest.setPrice(500.0f);
                dataRequest.setDeadline(LocalDateTime.now().plus(3, ChronoUnit.MONTHS));
                break;
            case 2:
                dataRequest.setPrice(1000.0f);
                dataRequest.setDeadline(LocalDateTime.now().plus(8, ChronoUnit.MONTHS));
                break;
            case 3:
                dataRequest.setPrice(1200.0f);
                dataRequest.setDeadline(LocalDateTime.now().plus(12, ChronoUnit.MONTHS));
                break;
            default:
                throw new IllegalArgumentException("Invalid service type");
            }

            // Create PayPal payment
            try {
                Payment payment = payPalService.createPayment(
                        dataRequest.getPrice(),
                        "USD",
                        "paypal",
                        "sale",
                        "Service Payment",
                        cancelUrl,
                        successUrl
                );

                String approvalUrl = payment.getLinks().stream()
                    .filter(link -> "approval_url".equals(link.getRel()))
                    .map(Links::getHref)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Approval URL not found"));

                this.usersServices.create(dataRequest);

                return new RedirectView(approvalUrl);
            } catch (PayPalRESTException e) {
                e.printStackTrace();
                // Log error and redirect to an error page or home page
                return new RedirectView("/error");
            } catch (RuntimeException e) {
                e.printStackTrace();
                // Handle unexpected errors
                return new RedirectView("/error");
            }
        } else {
            return new RedirectView("/login.html");
        }
    }


    
    @GetMapping("/paypal/success")
    public String paypalSuccess(Principal principal, doan._java_food.models.UserService dataRequest,
                                @RequestParam("paymentId") String paymentId,
                                @RequestParam("PayerID") String payerId,
                                Model model) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);

            if ("approved".equals(payment.getState())) {
                String username = principal.getName();
                User user = this.userService.findByUsername(username);
                dataRequest.setUser(user);
                dataRequest.setStatus("APPROVED");

                // Send email notification
                String recipientEmail = user.getEmail();
                String subject = "Payment Successful";
                String text = "Dear " + user.getName() + ",\n\nYour payment was successful.\n\nThank you for your purchase!";
                emailService.sendSimpleEmail(recipientEmail, subject, text);

                model.addAttribute("message", "Payment successful and email sent.");
                return "success"; // Ensure 'success.html' exists in the templates directory
            } else {
                model.addAttribute("error", "Payment was not approved.");
                return "error"; // Ensure 'error.html' exists in the templates directory
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            model.addAttribute("error", "Payment execution failed.");
            return "error"; // Ensure 'error.html' exists in the templates directory
        }
    }

    
    @GetMapping("/cooperate/profile")
    public String getUserProfile(Model model,Principal principal) {
    	 String username = principal.getName();
         User user =  this.userService.findByUsername(username);
         // Kiểm tra nếu user là null
         if (user == null) {
             throw new RuntimeException("User not found");
         }
        List<doan._java_food.models.UserService> services = usersServices.findByUserId(user.getId()); // Lấy danh sách dịch vụ của người dùng

        model.addAttribute("user", user);
        model.addAttribute("services", services);

        return "pages/fe/user/profile2";
    }
    
    
    @GetMapping("/notification")
    public String notificationPage() {
        // Trả về view 'notification.html'
        return "notification";
    }



    @GetMapping("/paypal/cancel")
    public String paypalCancel() {
        // Handle cancellation
        return "redirect:/"; // Redirect to home or cancel page
    }


    
}