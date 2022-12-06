package al.edu.fti.universitymanagement;


import com.jpa.filter.dao.FilterRepo;
import com.jpa.filter.dao.FilterRepoJpaImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
public class UniversityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityManagementApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public FilterRepo filterRepo() {
        return new FilterRepoJpaImpl();
    }

}
