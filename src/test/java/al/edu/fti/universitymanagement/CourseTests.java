package al.edu.fti.universitymanagement;

import al.edu.fti.universitymanagement.uniman.core.course.converter.CourseConverterImpl;
import al.edu.fti.universitymanagement.uniman.core.course.dao.CourseDao;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.course.service.CourseService;
import al.edu.fti.universitymanagement.uniman.core.course.validator.CourseValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CourseTests {

    @Mock
    private CourseDao courseDao;

    @Mock
    private CourseValidator courseValidator;

    @Mock
    private CourseConverterImpl courseConverter;

    @InjectMocks
    private CourseService courseService;


    @Test
    public void courseSaveTest(){
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

        Mockito.when(courseConverter.toEntity(courseDto)).thenReturn(courseEntity);
        Mockito.when(courseDao.save(courseEntity)).thenReturn(courseEntity);
        courseDto.setId(1L);
        Mockito.when(courseConverter.toDto(courseEntity)).thenReturn(courseDto);

        CourseDto savedEntity = courseService.save(courseDto);

        assertEquals(savedEntity.getId(),courseEntity.getId());
    }

    @Test
    public void courseUpdateTest(){
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

        Mockito.when(courseConverter.toEntity(courseDto,courseEntity)).thenReturn(courseEntity);
        Mockito.when(courseDao.save(courseEntity)).thenReturn(courseEntity);
        courseEntity.setName("Course Different name ");
        Mockito.when(courseDao.findById(1L)).thenReturn(Optional.of(courseEntity));

        courseDto.setId(1L);
        Mockito.when(courseConverter.toDto(courseEntity)).thenReturn(courseDto);

        CourseDto savedEntity = courseService.update(courseDto);

        assertEquals(savedEntity.getName(),courseDto.getName());
    }

}
