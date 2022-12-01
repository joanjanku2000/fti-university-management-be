package al.edu.fti.universitymanagement.uniman.core.userCourse.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.user.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dao.UserCourseDao;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import com.mongo.filter.dao.FilterRepo;
import com.mongo.filter.dao.FilterRepoJpaImpl;
import com.mongo.filter.dto.filter.FilterWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserCourseService extends BaseServiceAbstractImpl<UserCourseEntity, UserCourseDto> {
    @Lazy
    @Autowired
    private UserConverter userConverter;

    public UserCourseService(BaseDao<UserCourseEntity, Long> baseDao, BaseConverter<UserCourseDto, UserCourseEntity> baseConverter, BaseValidator<UserCourseDto, UserCourseEntity> baseValidator, FilterRepo filterRepo) {
        super(baseDao, baseConverter, baseValidator, filterRepo);
    }


    /**
     * This method uses Pagination to display all courses where logged user has joined
     * @param requestDto RequestDto
     * @return Page element containt all UserCourseDtos
     */
    @Override
    public Page<UserCourseDto> findAll(RequestDto requestDto) {
        log.info("Finding all courses of logged user");
        Long userId = SecurityUtil.getLoggedUser().getUserDto().getId();
        return ((UserCourseDao) baseDao)
                .findAllByUserEntityId(userId, PageRequest.of(requestDto.getPageNumber(), requestDto.getPageSize()))
                .map(baseConverter::toDto);

    }

    @Override
    public List<UserCourseDto> findAll(FilterWrap filterWrap, Class<UserCourseEntity> clazz) {
        return filterRepo.filter(filterWrap,clazz).stream().map(baseConverter::toDto).collect(Collectors.toList());
    }


    public List<UserDto> findStudentsOfCourse(Long courseId){
        log.info("Finding students of course {}",courseId);
        return ((UserCourseDao) baseDao)
                .findAllByCourseEntityId(courseId)
                .stream().map(UserCourseEntity::getUserEntity)
                .map(userConverter::toDto)
                .collect(Collectors.toList());

    }

    public Page<UserCourseDto> findCoursesOfUsers(Long userId, RequestDto requestDto){
        log.info("Finding all courses of  user");
        return ((UserCourseDao) baseDao)
                .findAllByUserEntityId(userId, PageRequest.of(requestDto.getPageNumber(), requestDto.getPageSize()))
                .map(baseConverter::toDto);
    }

}
