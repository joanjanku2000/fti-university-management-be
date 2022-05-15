package al.edu.fti.universitymanagement.uniman.core.comment.like.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dao.LikeDao;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dto.LikeDto;
import al.edu.fti.universitymanagement.uniman.core.comment.like.entity.LikeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService extends BaseServiceAbstractImpl<LikeEntity, LikeDto> {
    public LikeService(BaseDao<LikeEntity, Long> baseDao, BaseConverter<LikeDto, LikeEntity> baseConverter, BaseValidator<LikeDto, LikeEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }

    public List<LikeDto> findLikesOfComment(Long commentId) {
        return ((LikeDao) baseDao)
                    .findAllByCommentEntityId(commentId)
                        .stream()
                            .map(baseConverter::toDto)
                                .collect(Collectors.toList());
    }
}
