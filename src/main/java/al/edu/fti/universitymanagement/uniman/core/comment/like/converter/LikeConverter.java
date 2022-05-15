package al.edu.fti.universitymanagement.uniman.core.comment.like.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dao.CommentDao;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dao.LikeDao;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dto.LikeDto;
import al.edu.fti.universitymanagement.uniman.core.comment.like.entity.LikeEntity;
import al.edu.fti.universitymanagement.uniman.core.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeConverter implements BaseConverter<LikeDto, LikeEntity> {

    private final UserDao userDao;
    private final CommentDao commentDao;

    @Override
    public LikeDto toDto(LikeEntity baseEntity) {
        LikeDto likeDto = new LikeDto();
        likeDto.setId(baseEntity.getId());

        CommentDto commentDto = new CommentDto();
        commentDto.setId(baseEntity.getCommentEntity().getId());
        commentDto.setComment(baseEntity.getCommentEntity().getCommentString());
        commentDto.setCommentType(baseEntity.getCommentEntity().getType());

        likeDto.setCommentDto(commentDto);

        UserDto userDto = new UserDto();
        userDto.setName(baseEntity.getUserEntity().getName());
        userDto.setLastName(baseEntity.getUserEntity().getLastName());

        likeDto.setLikedBy(userDto);
        return likeDto;
    }

    @Override
    public LikeEntity toEntity(LikeDto baseDto) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setUserEntity(userDao.getById(SecurityUtil.getLoggedUser()
                .getUserDto().getId()));
        likeEntity.setCommentEntity(commentDao.getById(baseDto.getCommentDto().getId()));
        return likeEntity;
    }

    @Override
    public LikeEntity toEntity(LikeDto baseDto, LikeEntity baseEntity) {
        return null;
    }
}
