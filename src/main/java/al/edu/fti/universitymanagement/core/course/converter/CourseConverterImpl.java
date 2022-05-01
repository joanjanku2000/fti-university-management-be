package al.edu.fti.universitymanagement.core.course.converter;

import al.edu.fti.universitymanagement.core.base.converter.BaseConverter;
import al.edu.fti.universitymanagement.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.core.course.entity.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseConverterImpl implements BaseConverter<CourseDto,CourseEntity> {

    private final BaseConverter<LocationDto, Location> baseConverter;

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
        courseEntity.setName(base.getName());
        courseEntity.setDescription(base.getDescription());
        courseEntity.setEndTime(base.getEndTime());
        courseEntity.setStartTime(base.getStartTime());
        courseEntity.setLocation(baseConverter.toEntity(base.getLocation()));
        return courseEntity;
    }

    @Override
    public CourseEntity toEntity(CourseDto base, CourseEntity courseEntity) {
        courseEntity.setName(base.getName());
        courseEntity.setEndTime(base.getEndTime());
        courseEntity.setStartTime(base.getStartTime());
        courseEntity.setLocation(baseConverter.toEntity(base.getLocation()));
        return null;
    }


}
