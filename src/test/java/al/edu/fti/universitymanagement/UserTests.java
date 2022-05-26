package al.edu.fti.universitymanagement;

import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.security.user.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@SpringBootTest
public class UserTests {

    private final String BASE_URL = "http://localhost:8080/api/v1";

    private Long existingUserId = 1L;
    private String jwtToken = null;

    @Test
    public void performLogin(){

            String loginUrl = "http://localhost:8080/login";
            UserDto loginDTO = new UserDto();
            loginDTO.setEmail("jo@fti.edu.al");
            loginDTO.setMicrosoftAccessToken("eyJ0eXAiOiJKV1QiLCJub25jZSI6InR2Q1BMYkw3OVRkd1o3djVMSlpDUG5hM0JvVnpJZUlLVlFzd1dqMTNfQmMiLCJhbGciOiJSUzI1NiIsIng1dCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyIsImtpZCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyJ9.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTAwMDAtYzAwMC0wMDAwMDAwMDAwMDAiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC83Mzc0NGM4Mi04MDJmLTRmMTQtYjNiZS1hOTdkZjA3ODdkMTcvIiwiaWF0IjoxNjUxODIzOTkxLCJuYmYiOjE2NTE4MjM5OTEsImV4cCI6MTY1MTgyOTM2OCwiYWNjdCI6MCwiYWNyIjoiMSIsImFpbyI6IkUyWmdZRERnVVk3Z09PZ3NLUDN2cklYQmxhNkp6WEVOVDdoMGpzUmZNcGdUeE1QandBUUEiLCJhbXIiOlsicHdkIl0sImFwcF9kaXNwbGF5bmFtZSI6IlVuaXZlcnNpdHkgbWFuYWdtZW50IGFwcCIsImFwcGlkIjoiODBlODIxNWMtZDgxZi00OGJlLWE1ZmQtYmY3MDJkN2ZmYWZmIiwiYXBwaWRhY3IiOiIwIiwiaWR0eXAiOiJ1c2VyIiwiaXBhZGRyIjoiOTUuMTA3LjE3Mi4xNDAiLCJuYW1lIjoiU3RldmkgWml1Iiwib2lkIjoiYjViYmU5Y2YtMTAxOC00YTYwLThkMDItOWVkMmE1ODA4ZDZiIiwicGxhdGYiOiIzIiwicHVpZCI6IjEwMDMyMDAxQTRCQzAxNDkiLCJyaCI6IjAuQVFzQWdreDBjeS1BRkUtenZxbDk4SGg5RndNQUFBQUFBQUFBd0FBQUFBQUFBQUFMQUtnLiIsInNjcCI6Im9wZW5pZCBwcm9maWxlIFVzZXIuUmVhZCBlbWFpbCIsInN1YiI6InkxTWg4S3VVR3FicVBVVnFrTDcyYVN1MG1idFNaSnF6eVp4NVNBNkRkamMiLCJ0ZW5hbnRfcmVnaW9uX3Njb3BlIjoiRVUiLCJ0aWQiOiI3Mzc0NGM4Mi04MDJmLTRmMTQtYjNiZS1hOTdkZjA3ODdkMTciLCJ1bmlxdWVfbmFtZSI6InN0ZXZpLnppdUBmdGkuZWR1LmFsIiwidXBuIjoiU3RldmkuWml1QGZ0aS5lZHUuYWwiLCJ1dGkiOiJsbTBQVFRVSzZFbU1zYkZubmo5cEFBIiwidmVyIjoiMS4wIiwid2lkcyI6WyJiNzlmYmY0ZC0zZWY5LTQ2ODktODE0My03NmIxOTRlODU1MDkiXSwieG1zX3N0Ijp7InN1YiI6InlvSEdBRU9jUmNISWxZMDk4bzJOZGR6bDFadzZYdE15QkFPeVNJdWVqUjAifSwieG1zX3RjZHQiOjEzNzEwMTE0NDN9.LXETxDaeQYXfP5_RxlIdcHXk4qbSl77aTPMLyfGGpu0cvUmUac4DKxQKiqJJxc0ZJPhNSqeifhqk7BgXKVjNsHh2qYYe3hYANtNtsOUv83sqNZEnZpi3wJoXW7rnOnxWMHJOO_f59PCcf2M6w2PkLM3hUr4RDNw7DjfWdhZy8dumxZoO7L0IPfPU6_dw6tuDkXSSIWd7va9R5FHWUk_ZT0DQKBApwBOmUUTzk9fOOMuUWv-iMjCjCWqY9MbnTnp0FoXl5ZMfZKIFB88__-FOXnHrSEUx61fhS_Hz6GvYOD4J2t59snF5rMQoibKgr_GhsKTCV2N1kQI67rD0XARbAQ");

            TestRestTemplate testRestTemplate = new TestRestTemplate();
            ResponseEntity<LoginResponse> response =
                    testRestTemplate.postForEntity(loginUrl,loginDTO, LoginResponse.class);
            this.jwtToken = response.getBody().getBearer();
            log.info("Token {}",jwtToken);
            assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void updateUserHimself_status200(){

    }

    @Test
    public void updateUserNotHimself_status403(){

    }
}
