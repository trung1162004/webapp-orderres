package doan._java_food.controller.admin;

import doan._java_food.models.User;
import doan._java_food.service.EmailService;
import doan._java_food.service.User.UserService;
import doan._java_food.service.UserService.UsersServices;
import io.jsonwebtoken.io.IOException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


@Controller
@RequestMapping("/admin")
public class UserCooperationController {
    @Autowired
    private UsersServices service;

    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/terms-and-conditions")
    public String termsAndConditions() {
        return "pages/fe/user/terms-and-conditions";
    }
    
    @GetMapping("/cooperation")
    public String getLists(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        List<doan._java_food.models.UserService> lists = this.service.getAll();
        model.addAttribute("lists", lists);
        return "admin/cooperation/index";
    }

    @GetMapping("/cooperation/create")
    public String create(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }

        List<User> users = userService.findUserByType("RESTAURANT");
        model.addAttribute("users", users);
        doan._java_food.models.UserService data = new doan._java_food.models.UserService();
        model.addAttribute("data", data);
        model.addAttribute("messageError", "");
        data.setStatus("ACTIVE");

        return "admin/cooperation/create";
    }

    @PostMapping("/cooperation/create")
    public String save(@ModelAttribute("data") doan._java_food.models.UserService dataRequest,
                       Principal principal, Model model) {
        String message = "";
        try {
            if (principal != null) {
                String username = principal.getName();
                User user = this.userService.findByUsername(username);

                // Kiểm tra xem người dùng có phải là ADMIN không
                if (user != null && !user.getType().equals("ADMIN")) {
                    return "redirect:/";
                }

                // Kiểm tra xem người dùng đã có gói dịch vụ hoạt động chưa
                List<doan._java_food.models.UserService> activeServices = this.service.findActiveServiceByUser(user.getId());

                boolean canCreateNewPackage = true;

                for (doan._java_food.models.UserService activeService : activeServices) {
                    // Kiểm tra nếu gói dịch vụ hiện tại đang hoạt động hoặc sắp hết hạn trong 10 ngày
                    if ("Still valid".equals(activeService.getStatus()) ||
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
                    message = "Bạn đã có một gói dịch vụ hoạt động hoặc gói dịch vụ hiện tại không hết hạn trong vòng 10 ngày.";
                    model.addAttribute("messageError", message);
                    return "admin/cooperation/create";
                }

                // Xử lý giá của gói dịch vụ dựa trên loại dịch vụ
                switch (dataRequest.getService_type()) {
                    case 1:
                        dataRequest.setPrice(500.0f);
                        break;
                    case 2:
                        dataRequest.setPrice(1000.0f);
                        break;
                    case 3:
                        dataRequest.setPrice(1200.0f);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid service type");
                }

                // Lưu gói dịch vụ mới
                if (this.service.create(dataRequest)) {
                    return "redirect:/admin/cooperation";
                }
            } else {
                return "redirect:/";
            }
        } catch (Exception e) {
            message = "Create failed: " + e.getMessage();
        }
        
        model.addAttribute("messageError", message);
        return "admin/cooperation/create";
    }



    @GetMapping("/cooperation/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        doan._java_food.models.UserService data = this.service.findById(id);
        model.addAttribute("data", data);
        List<User> users = userService.findUserByType("RESTAURANT");
        model.addAttribute("users", users);
        model.addAttribute("messageError", "");

        return "admin/cooperation/update";
    }

    @PostMapping("/cooperation/edit")
    public String update(@ModelAttribute("data") doan._java_food.models.UserService data, Model model) {
        Long id = data.getId();
        doan._java_food.models.UserService dataUpdate = this.service.findById(Long.parseLong(id + ""));
        String message = "";
        List<User> users = userService.findUserByType("RESTAURANT");
        model.addAttribute("users", users);
        try {
            // Cập nhật các thuộc tính nếu có giá trị mới
            if (data.getName() != null) {
                dataUpdate.setName(data.getName());
            }
            if (data.getEmail() != null) {
                dataUpdate.setEmail(data.getEmail());
            }
            if (data.getDescription() != null) {
                dataUpdate.setDescription(data.getDescription());
            }
            if (data.getPhone() != null) {
                dataUpdate.setPhone(data.getPhone());
            }
            if (data.getService_type() != null) {
                dataUpdate.setService_type(data.getService_type());
            }

            // Xác định thời gian hiện tại
            LocalDateTime now = LocalDateTime.now();

            // Cập nhật giá và ngày hết hạn dựa trên loại dịch vụ
            if (data.getService_type() != null) {
                if (data.getService_type() == 1) {
                    dataUpdate.setPrice(Float.valueOf(500));
                    if (dataUpdate.getStatus().equals("Still valid") && data.getStatus().equals("PENDING")) {
                        dataUpdate.setDeadline(now.plusMonths(3)); // Cập nhật deadline
                    }
                } else if (data.getService_type() == 2) {
                    dataUpdate.setPrice(Float.valueOf(1000));
                    if (dataUpdate.getStatus().equals("Still valid") && data.getStatus().equals("PENDING")) {
                        dataUpdate.setDeadline(now.plusMonths(8)); // Cập nhật deadline
                    }
                } else if (data.getService_type() == 3) {
                    dataUpdate.setPrice(Float.valueOf(1200));
                    if (dataUpdate.getStatus().equals("Still valid") && data.getStatus().equals("PENDING")) {
                        dataUpdate.setDeadline(now.plusMonths(12)); // Cập nhật deadline
                    }
                }
            }

            // Cập nhật trạng thái nếu có giá trị mới
            if (data.getStatus() != null) {
                dataUpdate.setStatus(data.getStatus());
            }

            // Lưu cập nhật
            if (this.service.update(dataUpdate)) {
                return "redirect:/admin/cooperation";
            }
        } catch (Exception e) {
            message = "Update failed: " + e.getMessage();
        }
        model.addAttribute("data", dataUpdate);
        model.addAttribute("messageError", message);
        return "admin/cooperation/update";
    }
    
    
    @GetMapping("/cooperation/export-pdf")
    public ResponseEntity<Resource> exportPdf() throws DocumentException, IOException {
        // Tạo tài liệu PDF
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.add(new Paragraph("List of Cooperations"));

        // Lấy danh sách hợp tác từ cơ sở dữ liệu
        List<doan._java_food.models.UserService> cooperations = service.getNearlyExpiredAndExpiredServices();

        // Thêm nội dung vào PDF
        for (doan._java_food.models.UserService cooperation : cooperations) {
            String details = "ID: " + cooperation.getId() + 
                             ", Name: " + cooperation.getName() + 
                             ", Email: " + cooperation.getEmail() +
                             ", Phone: " + cooperation.getPhone() +
                             ", Status: " + cooperation.getStatus() +
                             ", Price: " + cooperation.getPrice() +
                             ", Service Type: " + cooperation.getService_type() +
                             ", Description: " + cooperation.getDescription() +
                             ", Created At: " + cooperation.getCreatedAt() +
                             ", Updated At: " + cooperation.getUpdatedAt() +
                             ", Deadline: " + cooperation.getDeadline();
            document.add(new Paragraph(details));
        }

        document.close();

        // Tạo ByteArrayResource từ ByteArrayOutputStream
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        // Thiết lập tiêu đề HTTP cho file đính kèm
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cooperations.pdf");

        // Trả về ResponseEntity với nội dung PDF
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    
	    
 // Hiển thị danh sách các gói sắp hết hạn và nút gửi email
    @GetMapping("/cooperation/filter-expiring")
    public String filterExpiringSoon(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        if (principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if (user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenDaysLater = now.plusDays(10);

        List<doan._java_food.models.UserService> allServices = this.service.getAll();
        List<doan._java_food.models.UserService> expiringSoon = allServices.stream()
                .filter(service -> {
                    LocalDateTime deadline = service.getDeadline();
                    return deadline != null && 
                           (deadline.isAfter(now) || deadline.isEqual(now)) && 
                           deadline.isBefore(tenDaysLater);
                })
                .collect(Collectors.toList());

        model.addAttribute("lists", expiringSoon);
        
        return "admin/cooperation/filter-expiring";
    }

 // Xử lý gửi email thông báo
    @PostMapping("/cooperation/send-email")
    public String sendEmailNotification(RedirectAttributes redirectAttributes) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tenDaysLater = now.plusDays(10);

        List<doan._java_food.models.UserService> allServices = this.service.getAll();
        List<doan._java_food.models.UserService> expiringSoon = allServices.stream()
                .filter(service -> {
                    LocalDateTime deadline = service.getDeadline();
                    return deadline != null && 
                           (deadline.isAfter(now) || deadline.isEqual(now)) && 
                           deadline.isBefore(tenDaysLater);
                })
                .collect(Collectors.toList());

        boolean emailSent = false;
        for (doan._java_food.models.UserService service : expiringSoon) {
            User serviceUser = service.getUser();
            if (serviceUser != null) {
                String recipientEmail = serviceUser.getEmail();
                String subject = "Thông Báo Hết Hạn Dịch Vụ";
                String text = "Kính gửi " + serviceUser.getName() + ",\n\n"
                        + "Dịch vụ của bạn sẽ hết hạn vào ngày "
                        + service.getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ".\n"
                        + "Vui lòng gia hạn dịch vụ để tránh gián đoạn.\n\n"
                        + "Cảm ơn bạn đã chú ý!";
                emailService.sendSimpleEmail(recipientEmail, subject, text);

                // Cập nhật trạng thái emailSent và lưu thay đổi vào cơ sở dữ liệu
                service.setEmailSent(true);
                this.service.update(service); // Giả sử phương thức update đã được định nghĩa trong lớp dịch vụ

                emailSent = true;
            }
        }

        if (emailSent) {
            redirectAttributes.addFlashAttribute("message", "Email gửi thành công.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Không có email nào được gửi.");
        }

        return "/admin/cooperation/success2";
    }



    @GetMapping("/cooperation/extension/{id}")
    public String extension(Model model, @PathVariable("id") Long id, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if(user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        doan._java_food.models.UserService data = this.service.findById(id);
        model.addAttribute("data", data);
        List<User> users = userService.findUserByType("RESTAURANT");
        model.addAttribute("users", users);
        model.addAttribute("messageError", "");

        return "admin/cooperation/extension";
    }

    @PostMapping("/cooperation/extension")
    public String extensionData(Model model,
                                Principal principal,
                                @ModelAttribute("data") doan._java_food.models.UserService data) {
        String message = "";
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

            // Tính toán giá dựa trên loại dịch vụ
            if (data.getService_type() == 1) {
                data.setPrice(Float.valueOf(500));
            } else if (data.getService_type() == 2) {
                data.setPrice(Float.valueOf(1000));
            } else if (data.getService_type() == 3) {
                data.setPrice(Float.valueOf(1200));
            }

            // Lưu gói dịch vụ mới
            if (this.service.create(data)) {
                return "redirect:/admin/cooperation";
            }
        } catch (Exception e) {
            message = "Update failed: " + e.getMessage();
        }
        model.addAttribute("data", data);
        List<User> users = userService.findUserByType("RESTAURANT");
        model.addAttribute("users", users);
        model.addAttribute("messageError", message);

        return "admin/cooperation/extension";
    }

    @GetMapping("/cooperation/detail/{id}")
    public String getCooperationDetail(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = this.userService.findByUsername(username);
            if (user != null && !user.getType().equals("ADMIN")) {
                return "redirect:/";
            }
        } else return "redirect:/";
        
        doan._java_food.models.UserService cooperation = this.service.findById(id);
        if (cooperation != null) {
            model.addAttribute("cooperation", cooperation);
            return "admin/cooperation/detail"; // Tệp JSP hoặc Thymeleaf để hiển thị chi tiết
        } else {
            return "redirect:/admin/cooperation"; // Quay về trang danh sách nếu không tìm thấy
        }
    }
    
    


    @GetMapping("/cooperation/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        this.service.delete(id);
        return "redirect:/admin/cooperation";
    }
}