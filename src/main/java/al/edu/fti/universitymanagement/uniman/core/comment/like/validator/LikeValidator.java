package al.edu.fti.universitymanagement.uniman.core.comment.like.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.BadRequestException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotAllowedException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dao.LikeDao;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dto.LikeDto;
import al.edu.fti.universitymanagement.uniman.core.comment.like.entity.LikeEntity;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static al.edu.fti.universitymanagement.base.core.enums.Operation.CREATE;
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.LIKE_EXISTS;

@RequiredArgsConstructor
@Component
public class LikeValidator implements BaseValidator<LikeDto, LikeEntity> {
    private final LikeDao likeDao;
    @Override
    public void validate(LikeDto dto, Operation operation) {
       if (operation == CREATE) {
            if (likeDao.likeExists(SecurityUtil.getLoggedUser().getUserDto().getId(),dto.getCommentDto().getId())){
                throw new BadRequestException(LIKE_EXISTS);
            }
       }
    }

    @Override
    public void validate(LikeEntity entity, Operation operation) {
        // Empty body
    }
}
