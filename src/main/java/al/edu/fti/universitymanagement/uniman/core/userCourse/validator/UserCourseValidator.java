package al.edu.fti.universitymanagement.uniman.core.userCourse.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.BadRequestException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotAllowedException;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dao.UserCourseDao;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static al.edu.fti.universitymanagement.base.core.enums.Operation.CREATE;
import static al.edu.fti.universitymanagement.base.core.enums.Operation.DELETE;
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.NOT_ALLOWED;
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.STUDENT_IS_ALREADY_PART_OF_THIS_COURSE;
import static java.lang.Boolean.FALSE;

@RequiredArgsConstructor
@Component
public class UserCourseValidator implements BaseValidator<UserCourseDto, UserCourseEntity> {

    private final UserCourseDao userCourseDao;

    @Override
    public void validate(UserCourseDto dto, Operation operation) {
        Long userId = dto.getUserDto().getId();
        Long courseId = dto.getCourseDto().getId();

        if (operation == CREATE) {
            if (userCourseDao.findAllByUserEntityIdAndCourseEntityId(userId,courseId).isEmpty() == FALSE) {
                throw new BadRequestException(STUDENT_IS_ALREADY_PART_OF_THIS_COURSE);
            }
        }

    }

    @Override
    public void validate(UserCourseEntity entity, Operation operation) {
        if (operation == DELETE){
            if (entity.getUserEntity().getId() != SecurityUtil.getLoggedUser().getUserDto().getId()) {
                throw new NotAllowedException(NOT_ALLOWED);
            }
        }
    }
}
