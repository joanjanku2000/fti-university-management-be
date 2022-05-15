package al.edu.fti.universitymanagement.uniman.core.comment.comment.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentValidator implements BaseValidator<CommentDto, CommentEntity> {
    @Override
    public void validate(CommentDto dto, Operation operation) {

    }

    @Override
    public void validate(CommentEntity entity, Operation operation) {

    }
}
