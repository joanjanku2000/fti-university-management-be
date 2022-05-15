package al.edu.fti.universitymanagement.uniman.core.comment.comment.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends BaseServiceAbstractImpl<CommentEntity, CommentDto> {

    public CommentService(BaseDao<CommentEntity, Long> baseDao, BaseConverter<CommentDto, CommentEntity> baseConverter, BaseValidator<CommentDto, CommentEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }
}
