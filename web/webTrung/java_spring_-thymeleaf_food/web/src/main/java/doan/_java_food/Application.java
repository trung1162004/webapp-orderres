package doan._java_food;

import doan._java_food.service.Storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@ComponentScan("doan._java_food")
public class Application {

	public static void main(String[] args) {
		Locale vi = new Locale("vi", "VN");
		NumberFormat formatCurrency = NumberFormat.getCurrencyInstance(vi);
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	CommandLineRunner init(StorageService storageService) {
//		return (args) -> {
//			storageService.deleteAll();
//			storageService.init();
//		};
//	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("lvtotnghiep123@gmail.com");
		mailSender.setPassword("rhgtundlonwrkhpa");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		

		return mailSender;
	}
}
