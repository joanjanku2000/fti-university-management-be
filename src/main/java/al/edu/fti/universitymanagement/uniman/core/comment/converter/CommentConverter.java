package al.edu.fti.universitymanagement.uniman.core.comment.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.entity.CommentEntity;
import al.edu.fti.universitymanagement.uniman.core.course.converter.CourseConverterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @RequiredArgsConstructor
public class CommentConverter implements BaseConverter<CommentDto, CommentEntity> {

    private final CourseConverterImpl courseConverter;

    @Override
    public CommentDto toDto(CommentEntity baseEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(baseEntity.getCommentString());
        commentDto.setCourseDto(courseConverter.toDto(baseEntity.getCourseEntity()));

        if (commentDto.getReplyingTo() == null){
            if (baseEntity.getParent() != null){
                commentDto.setReplyingTo(toDto(baseEntity.getParent()));
            }
        }

        if (commentDto.getReplies() != null && commentDto.getReplies().size() > 0){
            List<CommentDto> replies = new ArrayList<>();
            baseEntity.getChildren().forEach(child -> replies.add(toDto(child)));
            commentDto.setReplies(replies);
        } else {
            return commentDto;
        }
        return commentDto;
    }

    @Override
    public CommentEntity toEntity(CommentDto baseDto) {
        return null;
    }

    @Override
    public CommentEntity toEntity(CommentDto baseDto, CommentEntity baseEntity) {
        return null;
    }
}
