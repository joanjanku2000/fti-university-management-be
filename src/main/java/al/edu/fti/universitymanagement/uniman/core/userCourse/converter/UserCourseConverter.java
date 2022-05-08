package al.edu.fti.universitymanagement.uniman.core.userCourse.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.api.course.CourseController;
import al.edu.fti.universitymanagement.uniman.core.course.converter.CourseConverterImpl;
import al.edu.fti.universitymanagement.uniman.core.course.dao.CourseDao;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserCourseConverter implements BaseConverter< UserCourseDto, UserCourseEntity> {
    private final UserDao userDao;
    private final CourseDao courseDao;
    private final UserConverter userConverter;
    private final CourseConverterImpl courseConverter;

    @Override
    public UserCourseDto toDto(UserCourseEntity baseEntity) {
        UserCourseDto userCourseDto = new UserCourseDto();
        userCourseDto.setUserDto(userConverter.toDto(baseEntity.getUserEntity()));
        userCourseDto.setCourseDto(courseConverter.toDto(baseEntity.getCourseEntity()));
        userCourseDto.setId(baseEntity.getId());
        userCourseDto.setJoinedOn(baseEntity.getJoinedOn());
        return userCourseDto;
    }

    @Override
    public UserCourseEntity toEntity(UserCourseDto baseDto) {
        UserEntity userEntity = userDao.getById(baseDto.getUserDto().getId());
        CourseEntity courseEntity = courseDao.getById(baseDto.getCourseDto().getId());

        UserCourseEntity userCourseEntity = new UserCourseEntity();
        userCourseEntity.setUserEntity(userEntity);
        userCourseEntity.setCourseEntity(courseEntity);

        return userCourseEntity;
    }

    @Override
    public UserCourseEntity toEntity(UserCourseDto baseDto, UserCourseEntity baseEntity) {
        // NOT NEEDED
        return null;
    }
}
