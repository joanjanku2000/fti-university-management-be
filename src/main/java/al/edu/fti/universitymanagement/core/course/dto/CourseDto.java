package al.edu.fti.universitymanagement.core.course.dto;

import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import al.edu.fti.universitymanagement.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.core.util.CustomZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Data
public class CourseDto extends BaseDto {

    @NotEmpty
    @NotBlank(message = "Name should not be blank")
    private String name;

    @NotEmpty
    @NotBlank(message = "Description should not be blank")
    private String description;

    @JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @NotEmpty
    @NotBlank(message = "Start time should not be blank")
    private LocalDateTime startTime;

    @JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotEmpty
    @NotBlank(message = "End time should not be blank")
    private LocalDateTime endTime;

    @NotEmpty
    @NotBlank(message = "Location should not be blank")
    private LocationDto location;

    @Override
    public CourseEntity toEntity() {
        CourseEntity course = new CourseEntity(name, description, startTime, endTime, location.toEntity());
        course.setActive(true);
        return course;
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

    public CourseDto(Long id, String name, String description,
                     LocalDateTime startTime, LocalDateTime endTime) {
        super(id);
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean equals(Object o){
        if (o instanceof CourseDto){
            return ((CourseDto) o).getLocation().equals(location) && ((CourseDto) o).getDescription().equals(description) && ((CourseDto) o).endTime.equals(endTime) && ((CourseDto) o).startTime.equals(startTime);
        }
        return false;
    }

    public CourseDto toDto(CourseEntity entity){
        return new CourseDto(entity.getId(),entity.getName(),entity.getDescription(),entity.getStartTime(),entity.getEndTime());
    }

}
