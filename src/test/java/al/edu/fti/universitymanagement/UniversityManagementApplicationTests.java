package al.edu.fti.universitymanagement;

import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@SpringBootTest
class UniversityManagementApplicationTests {
	@Autowired
	private UserDao userDao;

	@Test
	void contextLoads() {
		assertEquals(userDao.getById(13L).getId(),13L);
	}

}
