package al.edu.fti.universitymanagement;

import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
//@SpringBootTest
public class UniversityManagementApplicationTests {
	@Autowired
	private UserDao userDao;

	@Test
	void contextLoads() {
		UserEntity u = userDao.getById(13L);
		log.info("Us {}",u.getFullName());
	}

}
