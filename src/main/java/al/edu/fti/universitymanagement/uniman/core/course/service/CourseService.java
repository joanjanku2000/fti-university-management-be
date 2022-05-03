package al.edu.fti.universitymanagement.uniman.core.course.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourseService extends BaseServiceAbstractImpl<CourseEntity,CourseDto> {

    public CourseService(BaseDao<CourseEntity, Long> baseDao, BaseConverter<CourseDto, CourseEntity> baseConverter, BaseValidator<CourseDto, CourseEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }
}
