package al.edu.fti.universitymanagement.uniman.core.course.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CourseValidator implements BaseValidator<CourseDto, CourseEntity> {

    @Override
    public void validate(CourseDto dto, Operation operation) {
        log.info("Validating passed course dto ");
    }

    @Override
    public void validate(CourseEntity entity, Operation operation) {
        log.info("Validating passed course entity ");
    }
}
