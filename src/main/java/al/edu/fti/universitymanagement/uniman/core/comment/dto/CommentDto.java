package al.edu.fti.universitymanagement.uniman.core.comment.dto;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.uniman.core.comment.enums.CommentType;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDto extends BaseDto {

    private String comment;
    private LocalDateTime createdAt;
    private UserDto userDto;
    private CourseDto courseDto;
    private CommentDto replyingTo;
    private List<CommentDto> replies;
    private CommentType commentType;
}
