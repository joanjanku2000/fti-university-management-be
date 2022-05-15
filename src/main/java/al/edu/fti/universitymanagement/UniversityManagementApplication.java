package al.edu.fti.universitymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class UniversityManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityManagementApplication.class, args);
	}

}
