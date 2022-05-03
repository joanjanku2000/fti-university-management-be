package al.edu.fti.universitymanagement.uniman.core.course.dto;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.util.CustomZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Data @NoArgsConstructor
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
