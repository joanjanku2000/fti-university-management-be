package al.edu.fti.universitymanagement.uniman.core.user.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements BaseValidator<UserDto, UserEntity> {
    @Override
    public void validate(UserDto dto, Operation operation) {

    }

    @Override
    public void validate(UserEntity entity, Operation operation) {

    }
}
