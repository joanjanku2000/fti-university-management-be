package al.edu.fti.universitymanagement.uniman.core.course.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.BadRequestException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages;
import al.edu.fti.universitymanagement.uniman.core.course.dao.LocationDao;
import al.edu.fti.universitymanagement.uniman.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.LocationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LocationValidator implements BaseValidator<LocationDto, LocationEntity> {
    private final LocationDao locationDao;

    @Override
    public void validate(LocationDto dto, Operation operation) {

    }

    @Override
    public void validate(LocationEntity entity, Operation operation) {
        if (operation.equals(Operation.DELETE)) {
            LocationEntity loadedEntity = locationDao.findLocationWithLoadedCourses(entity.getId());

            if (loadedEntity != null && loadedEntity.getCourseEntity().size() > 0){
                throw new BadRequestException(ErrorMessages.NOT_ALLOWED + this.getClass().getSimpleName()
                        .substring(0,this.getClass().getSimpleName().indexOf("Validator")));
            }
        }

    }
}
