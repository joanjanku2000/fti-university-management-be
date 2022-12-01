package al.edu.fti.universitymanagement.uniman.core.course.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.course.dao.LocationDao;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.course.entity.LocationEntity;
import al.edu.fti.universitymanagement.uniman.core.userCourse.converter.UserCourseConverter;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Converter Component Class to make the neccessary conversions
 * between Entity And Dto representation of it
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CourseConverterImpl implements BaseConverter<CourseDto,CourseEntity> {

    private final BaseConverter<LocationDto, LocationEntity> baseConverter;
    private final LocationDao locationDao;

    @Override
    public CourseDto toDto(CourseEntity baseEntity) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(baseEntity.getId());
        courseDto.setName((baseEntity).getName());
        courseDto.setDescription((baseEntity).getDescription());
        courseDto.setStartTime(baseEntity.getStartTime());
        courseDto.setEndTime(baseEntity.getEndTime());
        courseDto.setLocation(baseConverter.toDto(baseEntity.getLocation()));
        return courseDto;
    }

    @Override
    public CourseEntity toEntity(CourseDto base) {
        CourseEntity courseEntity = new CourseEntity();
        return convertEntity(base, courseEntity);
    }

    @Override
    public CourseEntity toEntity(CourseDto base, CourseEntity courseEntity) {
        return convertEntity(base, courseEntity);
    }

    private CourseEntity convertEntity(CourseDto base, CourseEntity courseEntity) {
        courseEntity.setName(base.getName());
        courseEntity.setDescription(base.getDescription());
        courseEntity.setEndTime(base.getEndTime());
        courseEntity.setStartTime(base.getStartTime());
        LocationEntity location = locationDao.findById(base.getId()).orElseThrow(() -> new RuntimeException("Location Not Found"));
        log.info("Saving course location {}",location);
        courseEntity.setLocation(location);
        return courseEntity;
    }


}
