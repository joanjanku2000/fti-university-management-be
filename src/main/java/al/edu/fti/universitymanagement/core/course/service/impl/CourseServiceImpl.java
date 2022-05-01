package al.edu.fti.universitymanagement.core.course.service.impl;

import al.edu.fti.universitymanagement.core.base.converter.BaseConverter;
import al.edu.fti.universitymanagement.core.base.dao.BaseDao;
import al.edu.fti.universitymanagement.core.course.dao.CourseDao;
import al.edu.fti.universitymanagement.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.core.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDao baseDao;

    @Qualifier("courseConverter")
    private final BaseConverter<CourseDto, CourseEntity> baseConverter;

    public CourseServiceImpl(CourseDao baseDao, BaseConverter<CourseDto, CourseEntity> baseConverter) {
        this.baseDao = baseDao;
        this.baseConverter = baseConverter;
    }

    @Override
    public CourseDto save(CourseDto baseDto) {
        log.info("Course:: Saving baseDto {} ", baseDto);
        return baseConverter.toDto(baseDao.save(baseConverter.toEntity(baseDto)));
    }

    @Override
    public CourseDto update(CourseDto baseDto) {
        return null;
    }

    @Override
    public CourseDto read(Long id) {
        CourseEntity course = baseDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return baseConverter.toDto(course);
    }

    @Override
    public CourseDto delete(Long id) {
        return null;
    }
}
