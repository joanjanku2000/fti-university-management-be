package al.edu.fti.universitymanagement.uniman.core.comment.comment.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dao.CommentDao;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.enums.CommentType;
import al.edu.fti.universitymanagement.uniman.core.course.converter.CourseConverterImpl;
import al.edu.fti.universitymanagement.uniman.core.course.dao.CourseDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter Component Class to make the neccessary conversions
 * between Entity And Dto representation of it
 */
@Component @RequiredArgsConstructor
public class CommentConverter implements BaseConverter<CommentDto, CommentEntity> {

    private final CourseConverterImpl courseConverter;
    private final CourseDao courseDao;
    private final CommentDao commentDao;
    private final UserDao userDao;
    private final UserConverter userConverter;

    @Override
    public CommentDto toDto(CommentEntity baseEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(baseEntity.getId());
        commentDto.setComment(baseEntity.getCommentString());
        commentDto.setCommentType(baseEntity.getType());
        commentDto.setUserDto(userConverter.toDto(baseEntity.getUserEntity()));
        if (baseEntity.getCourseEntity()!=null && baseEntity.getType() != CommentType.TIMELINE)
            commentDto.setCourseDto(courseConverter.toDto(baseEntity.getCourseEntity()));

        List<CommentEntity> repliesList = commentDao.findAllByParentId(baseEntity.getId());

        if (repliesList.size() > 0){
            List<CommentDto> replies = new ArrayList<>();
            repliesList.forEach(child -> replies.add(toDto(child)));
            commentDto.setReplies(replies);
        } else {
            return commentDto;
        }
        commentDto.setCreatedAt(baseEntity.getCreatedAt());
        return commentDto;
    }

    /**
     * This method is used to convert the DTO to entity
     * when a comment is pushed into the system.
     * If replyingTo is provided it means the course entity can be inferred
     * else the course entity should be provided. This presumes the
     * comment isn't a reply but a conversation starter
     *
     * @param baseDto CommentDto
     * @return CommentEntity
     */
    @Override
    public CommentEntity toEntity(CommentDto baseDto) {
        CommentEntity replyingTo = null;

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserEntity(userDao.getById(baseDto.getUserDto().getId()));

        if (baseDto.getReplyingTo() == null) {
            if (baseDto.getCommentType() != CommentType.TIMELINE)
                commentEntity.setCourseEntity(courseDao.getById(baseDto.getCourseDto().getId()));
        } else{
            replyingTo = commentDao.getById(baseDto.getReplyingTo().getId());
            if (baseDto.getCommentType() != CommentType.TIMELINE)
                commentEntity.setCourseEntity(replyingTo.getCourseEntity());
        }

        commentEntity.setCommentString(baseDto.getComment());
        commentEntity.setType(baseDto.getCommentType());

        commentEntity.setParent(baseDto.getReplyingTo() != null ? replyingTo : null);
        commentEntity.setType(baseDto.getCommentType());
        return commentEntity;
    }

    @Override
    public CommentEntity toEntity(CommentDto baseDto, CommentEntity baseEntity) {
        return null;
    }
}
