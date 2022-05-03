package al.edu.fti.universitymanagement.base.core.validator;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.base.core.enums.Operation;

public interface BaseValidator<T extends BaseDto, S extends BaseEntity> {
    void validate(T dto, Operation operation);
    void validate(S entity, Operation operation);
}
