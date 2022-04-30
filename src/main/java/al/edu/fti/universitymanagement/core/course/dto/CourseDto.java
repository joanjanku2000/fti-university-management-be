package al.edu.fti.universitymanagement.core.course.dto;

import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import al.edu.fti.universitymanagement.core.course.entity.CourseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseDto extends BaseDto {

    @NotEmpty
    @NotBlank(message = "Name should not be blank")
    private String name;

    @NotEmpty
    @NotBlank(message = "Description should not be blank")
    private String description;

    @NotEmpty
    @NotBlank(message = "Start time should not be blank")
    private LocalDateTime startTime;

    @NotEmpty
    @NotBlank(message = "End time should not be blank")
    private LocalDateTime endTime;

    @NotEmpty
    @NotBlank(message = "Location should not be blank")
    private LocationDto location;

    @Override
    public CourseEntity toEntity() {
        return new CourseEntity(name, description, startTime, endTime, location.toEntity());
    }

    @Override
    public CourseEntity toEntity(BaseEntity base) {
        ((CourseEntity) base).setName(name);
        ((CourseEntity) base).setDescription(description);
        ((CourseEntity) base).setStartTime(startTime);
        ((CourseEntity) base).setEndTime(endTime);
        ((CourseEntity) base).setLocation(location.toEntity(((CourseEntity) base).getLocation()));
        return (CourseEntity) base;
    }
}
