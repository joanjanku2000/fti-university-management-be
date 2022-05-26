package al.edu.fti.universitymanagement;

import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.uniman.core.user.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.core.user.user.enums.Gender;
import al.edu.fti.universitymanagement.uniman.core.user.user.enums.Role;
import al.edu.fti.universitymanagement.uniman.core.user.user.service.UserService;
import al.edu.fti.universitymanagement.uniman.core.user.user.validator.UserValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserTests {

    @Mock
    private UserDao userDao;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("Update User Properties")
    public void updateUserHimself_status200() {
        UserDto userDto = new UserDto();
        userDto.setId(2L);
        userDto.setName("Bob");
        userDto.setLastName("Ndertuesi");
        userDto.setEmail("bobndertuese@gmail.com");
        userDto.setBirthday(LocalDate.of(2000,04,02));
        userDto.setGender(Gender.MALE.ordinal());
        userDto.setRole(Role.STUDENT);
        userDto.setIdNumber("K00204012N");
        userDto.setRegistrationDate(LocalDateTime.now());

        // Updated Entity
        UserEntity userEntity = new UserEntity();
        userEntity.setId(2L);
        userEntity.setName("Bob");
        userEntity.setLastName("Ndertuesi");
        userEntity.setEmail("bobndertuesi@gmail.com");
        userEntity.setBirthday(LocalDate.of(2000,04,02));
        userEntity.setGender(Gender.MALE);
        userEntity.setRole(Role.STUDENT);
        userEntity.setIdNumber("K00204012N");


        Mockito.when(userConverter.toEntity(any(UserDto.class),any(UserEntity.class))).thenReturn(userEntity);
        Mockito.when(userDao.save(any(UserEntity.class))).thenReturn(userEntity);

        //Original entity
        userEntity.setName("Something other than Bob");
        Mockito.when(userDao.findById(2L)).thenReturn(Optional.of(userEntity));


        UserDto updatedEntity = userService.update(userDto);
        log.info("Updated entity",updatedEntity.getFullName());

        verify(userDao,times(1))
                .save(any(UserEntity.class));

        assertEquals(updatedEntity.getName(), userDto.getName());

    }

    @Test
    public void updateUserNotHimself_status403() {

    }
}
