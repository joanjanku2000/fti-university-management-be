package al.edu.fti.universitymanagement.uniman.core.comment.comment.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dao.CommentDao;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static al.edu.fti.universitymanagement.uniman.core.comment.comment.enums.CommentType.TIMELINE;

@Service
public class CommentService extends BaseServiceAbstractImpl<CommentEntity, CommentDto> {

    public CommentService(BaseDao<CommentEntity, Long> baseDao, BaseConverter<CommentDto, CommentEntity> baseConverter, BaseValidator<CommentDto, CommentEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }

    public List<CommentDto> getUserUpdates(Long userId){
        return  ((CommentDao) baseDao).findAllByTypeAndUserEntityId(TIMELINE,userId)
                .stream()
                .map(baseConverter::toDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getCommentsOfCourse(Long courseId){
        return ((CommentDao) baseDao).findCommentsOfCourse(courseId)
                .stream()
                .map(baseConverter::toDto)
                .collect(Collectors.toList());
    }

}
