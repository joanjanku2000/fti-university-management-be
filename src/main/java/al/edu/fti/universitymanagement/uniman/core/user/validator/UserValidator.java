package al.edu.fti.universitymanagement.uniman.core.user.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotAllowedException;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import al.edu.fti.universitymanagement.uniman.core.user.UserService;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static al.edu.fti.universitymanagement.base.core.enums.Operation.DELETE;
import static al.edu.fti.universitymanagement.base.core.enums.Operation.UPDATE;
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.CANNOT_CHANGE_EMAIL;
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.CANNOT_UPDATE_USER;
import static java.lang.Boolean.FALSE;

@Component
public class UserValidator implements BaseValidator<UserDto, UserEntity> {

    @Lazy
    @Autowired
    private UserService userService;

    @Override
    public void validate(UserDto dto, Operation operation) {
        if (operation == UPDATE) {
            if (SecurityUtil.getLoggedUser().getUserDto().getId().equals(dto.getId()) == FALSE){
                throw new NotAllowedException(CANNOT_UPDATE_USER);
            }
            UserDto oldEntity = userService.read(dto.getId());

            if (oldEntity.getEmail().equals(dto.getEmail()) == FALSE){
                throw new NotAllowedException(CANNOT_CHANGE_EMAIL);
            }
        }
    }

    @Override
    public void validate(UserEntity entity, Operation operation) {
        if (operation == DELETE) {
            if (SecurityUtil.getLoggedUser().getUserDto().getId().equals(entity.getId()) == FALSE){
                throw new NotAllowedException(CANNOT_UPDATE_USER);
            }
        }
    }
}
