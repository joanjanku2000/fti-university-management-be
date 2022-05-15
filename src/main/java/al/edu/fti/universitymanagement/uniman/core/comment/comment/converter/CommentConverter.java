package al.edu.fti.universitymanagement.uniman.core.comment.comment.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dao.CommentDao;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import al.edu.fti.universitymanagement.uniman.core.course.converter.CourseConverterImpl;
import al.edu.fti.universitymanagement.uniman.core.course.dao.CourseDao;
import al.edu.fti.universitymanagement.uniman.core.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @RequiredArgsConstructor
public class CommentConverter implements BaseConverter<CommentDto, CommentEntity> {

    private final CourseConverterImpl courseConverter;
    private final CourseDao courseDao;
    private final CommentDao commentDao;
    private final UserDao userDao;

    @Override
    public CommentDto toDto(CommentEntity baseEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(baseEntity.getId());
        commentDto.setComment(baseEntity.getCommentString());
        commentDto.setCourseDto(courseConverter.toDto(baseEntity.getCourseEntity()));
        List<CommentEntity> repliesList = commentDao.findAllByParentId(baseEntity.getId());

        if (repliesList.size() > 0){
            List<CommentDto> replies = new ArrayList<>();
            repliesList.forEach(child -> replies.add(toDto(child)));
            commentDto.setReplies(replies);
        } else {
            return commentDto;
        }
        return commentDto;
    }

    @Override
    public CommentEntity toEntity(CommentDto baseDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserEntity(userDao.getById(SecurityUtil.getLoggedUser().getUserDto().getId()));
        commentEntity.setCourseEntity(courseDao.getById(baseDto.getCourseDto().getId()));
        commentEntity.setCommentString(baseDto.getComment());
        commentEntity.setParent(baseDto.getReplyingTo() != null ? commentDao.getById(baseDto.getReplyingTo().getId())
                : null);
        commentEntity.setType(baseDto.getCommentType());
        return commentEntity;
    }

    @Override
    public CommentEntity toEntity(CommentDto baseDto, CommentEntity baseEntity) {
        return null;
    }
}
