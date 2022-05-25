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
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.*;
import static java.lang.Boolean.FALSE;

@RequiredArgsConstructor
@Component
public class UserCourseValidator implements BaseValidator<UserCourseDto, UserCourseEntity> {

    private final UserCourseDao userCourseDao;

    /**
     * Validates operation if
     * 1) Logged user corresponds with the one being passed in the argument (security reasons)
     * 2) Student is already part of the course
     * @param dto UserCourseDto
     * @param operation Operation
     * @throws BadRequestException if user in the dto is not the logged user or if Student already has joined the course
     */
    @Override
    public void validate(UserCourseDto dto, Operation operation) {
        Long loggedUserId = SecurityUtil.getLoggedUser().getUserDto().getId();
        Long courseId = dto.getCourseDto().getId();
        Long dtoUserId = dto.getUserDto().getId();
        if (!dtoUserId.equals(loggedUserId)){
            throw new BadRequestException(GENERIC_NOT_ALLOWED);
        }
        if (operation == CREATE) {
            if (userCourseDao.findAllByUserEntityIdAndCourseEntityId(loggedUserId,courseId).isEmpty() == FALSE) {
                throw new BadRequestException(STUDENT_IS_ALREADY_PART_OF_THIS_COURSE);
            }
        }

    }

    /**
     * Validates operation if
     * 1) Logged user corresponds with the one being passed in the argument (security reasons)
     * 2) Student is already part of the course
     * @param entity UserCourseEntity
     * @param operation Operation
     * @throws NotAllowedException if logged user is not doing the action for himself
     */
    @Override
    public void validate(UserCourseEntity entity, Operation operation) {
        if (operation == DELETE){
            if (entity.getUserEntity().getId() != SecurityUtil.getLoggedUser().getUserDto().getId()) {
                throw new NotAllowedException(NOT_ALLOWED);
            }
        }
    }
}
