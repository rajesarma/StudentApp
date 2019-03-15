package in.education.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication {	//extends SpringBootServletInitializer

	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StudentApplication.class);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
