package taskEasy.demo;

import jakarta.servlet.annotation.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
public class TaskEasyApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskEasyApplication.class, args);
	}
}
