package al.edu.fti.universitymanagement.uniman.core.userCourse.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserCourseService extends BaseServiceAbstractImpl<UserCourseEntity , UserCourseDto> {
    public UserCourseService(BaseDao<UserCourseEntity, Long> baseDao, BaseConverter<UserCourseDto, UserCourseEntity> baseConverter, BaseValidator<UserCourseDto, UserCourseEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }
}
