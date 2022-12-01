package al.edu.fti.universitymanagement.uniman.core.comment.like.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dao.LikeDao;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dto.LikeDto;
import al.edu.fti.universitymanagement.uniman.core.comment.like.entity.LikeEntity;
import com.mongo.filter.dao.FilterRepoJpaImpl;
import com.mongo.filter.dto.filter.FilterWrap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService extends BaseServiceAbstractImpl<LikeEntity, LikeDto> {


    public LikeService(BaseDao<LikeEntity, Long> baseDao, BaseConverter<LikeDto, LikeEntity> baseConverter, BaseValidator<LikeDto, LikeEntity> baseValidator, FilterRepoJpaImpl filterRepo) {
        super(baseDao, baseConverter, baseValidator, filterRepo);
    }

    public List<LikeDto> findLikesOfComment(Long commentId) {
        return ((LikeDao) baseDao)
                    .findAllByCommentEntityId(commentId)
                        .stream()
                            .map(baseConverter::toDto)
                                .collect(Collectors.toList());
    }

    @Override
    public List<LikeDto> findAll(FilterWrap filterWrap, Class<LikeEntity> clazz) {
        return filterRepo.filter(filterWrap,clazz).stream().map(baseConverter::toDto).collect(Collectors.toList());
    }
}
