package al.edu.fti.universitymanagement;

import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotFoundException;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.core.userCourse.converter.UserCourseConverter;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dao.UserCourseDao;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import al.edu.fti.universitymanagement.uniman.core.userCourse.service.UserCourseService;
import al.edu.fti.universitymanagement.uniman.core.userCourse.validator.UserCourseValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CourseUserTests {

    @Mock
    private UserCourseDao userCourseDao;

    @Mock
    private UserCourseValidator courseValidator;

    @Mock
    private UserCourseConverter courseConverter;

    @InjectMocks
    private UserCourseService courseService;


    @Test
    public void courseSaveTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        UserDto userDto = new UserDto();
        userDto.setId(1L);

        CourseDto courseDto = new CourseDto();
        courseDto.setName("Course test");
        courseDto.setStartTime(LocalDateTime.now());
        courseDto.setEndTime(LocalDateTime.now().plusHours(2));
        courseDto.setDescription("Description");

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(1L);
        courseEntity.setName("Course test");
        courseEntity.setStartTime(LocalDateTime.now());
        courseEntity.setEndTime(LocalDateTime.now().plusHours(2));
        courseEntity.setDescription("Description");

        UserCourseEntity userCourseEntity = new UserCourseEntity();
        userCourseEntity.setCourseEntity(courseEntity);
        userCourseEntity.setUserEntity(userEntity);
        userCourseEntity.setJoinedOn(LocalDateTime.now());

        UserCourseDto userCourseDto = new UserCourseDto();
        userCourseDto.setCourseDto(courseDto);
        userCourseDto.setUserDto(userDto);

        Mockito.when(courseConverter.toEntity(userCourseDto)).thenReturn(userCourseEntity);
        Mockito.when(userCourseDao.save(userCourseEntity)).thenReturn(userCourseEntity);
        courseDto.setId(1L);
        Mockito.when(courseConverter.toDto(userCourseEntity)).thenReturn(userCourseDto);

        UserCourseDto savedEntity = courseService.save(userCourseDto);

        assertEquals(savedEntity.getId(), userCourseEntity.getId());
    }

    @Test
    public void courseLeaveSucceedsTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        UserDto userDto = new UserDto();
        userDto.setId(1L);

        CourseDto courseDto = new CourseDto();
        courseDto.setName("Course test");
        courseDto.setStartTime(LocalDateTime.now());
        courseDto.setEndTime(LocalDateTime.now().plusHours(2));
        courseDto.setDescription("Description");

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(1L);
        courseEntity.setName("Course test");
        courseEntity.setStartTime(LocalDateTime.now());
        courseEntity.setEndTime(LocalDateTime.now().plusHours(2));
        courseEntity.setDescription("Description");

        UserCourseEntity userCourseEntity = new UserCourseEntity();
        userCourseEntity.setId(1L);
        userCourseEntity.setCourseEntity(courseEntity);
        userCourseEntity.setUserEntity(userEntity);
        userCourseEntity.setJoinedOn(LocalDateTime.now());

        UserCourseDto userCourseDto = new UserCourseDto();
        userCourseDto.setCourseDto(courseDto);
        userCourseDto.setUserDto(userDto);

        Mockito.when(userCourseDao.findById(1L)).thenReturn(Optional.of(userCourseEntity));

        courseService.delete(1L);

        Mockito.verify(userCourseDao,Mockito.times(1)).delete(any(UserCourseEntity.class));

    }

    @Test
    public void courseLeaveFailsTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        UserDto userDto = new UserDto();
        userDto.setId(1L);

        CourseDto courseDto = new CourseDto();
        courseDto.setName("Course test");
        courseDto.setStartTime(LocalDateTime.now());
        courseDto.setEndTime(LocalDateTime.now().plusHours(2));
        courseDto.setDescription("Description");

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(1L);
        courseEntity.setName("Course test");
        courseEntity.setStartTime(LocalDateTime.now());
        courseEntity.setEndTime(LocalDateTime.now().plusHours(2));
        courseEntity.setDescription("Description");

        UserCourseEntity userCourseEntity = new UserCourseEntity();
        userCourseEntity.setId(1L);
        userCourseEntity.setCourseEntity(courseEntity);
        userCourseEntity.setUserEntity(userEntity);
        userCourseEntity.setJoinedOn(LocalDateTime.now());

        UserCourseDto userCourseDto = new UserCourseDto();
        userCourseDto.setCourseDto(courseDto);
        userCourseDto.setUserDto(userDto);

        assertThrows(NotFoundException.class, () -> courseService.delete(2L));

    }
}
