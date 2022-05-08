package al.edu.fti.universitymanagement.uniman.core.userCourse.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dao.UserCourseDao;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCourseService extends BaseServiceAbstractImpl<UserCourseEntity, UserCourseDto> {
    public UserCourseService(BaseDao<UserCourseEntity, Long> baseDao, BaseConverter<UserCourseDto, UserCourseEntity> baseConverter, BaseValidator<UserCourseDto, UserCourseEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }

    @Override
    public Page<UserCourseDto> findAll(RequestDto requestDto) {
        log.info("Finding all courses of logged user");
        Long userId = SecurityUtil.getLoggedUser().getUserDto().getId();
        return ((UserCourseDao) baseDao)
                .findAllByUserEntityId(userId, PageRequest.of(requestDto.getPageNumber(), requestDto.getPageSize()))
                .map(baseConverter::toDto);

    }
}
