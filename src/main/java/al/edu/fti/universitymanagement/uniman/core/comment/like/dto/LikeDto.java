package al.edu.fti.universitymanagement.uniman.core.comment.like.dto;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import lombok.Data;

@Data
public class LikeDto extends BaseDto {
    private CommentDto commentDto;
    private UserDto likedBy;
}
