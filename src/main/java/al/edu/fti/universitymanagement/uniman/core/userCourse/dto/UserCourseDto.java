package al.edu.fti.universitymanagement.uniman.core.userCourse.dto;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCourseDto extends BaseDto {

    @JsonIgnore
    private UserDto userDto;

    private CourseDto courseDto;
    private LocalDateTime joinedOn;
}
