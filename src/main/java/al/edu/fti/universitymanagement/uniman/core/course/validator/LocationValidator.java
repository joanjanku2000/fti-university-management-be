package al.edu.fti.universitymanagement.uniman.core.course.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.LocationEntity;
import org.springframework.stereotype.Component;

@Component
public class LocationValidator implements BaseValidator<LocationDto, LocationEntity> {
    @Override
    public void validate(LocationDto dto, Operation operation) {

    }

    @Override
    public void validate(LocationEntity entity, Operation operation) {

    }
}
