package al.edu.fti.universitymanagement.uniman.core.comment.comment.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.BadRequestException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dao.CommentDao;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.enums.CommentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static al.edu.fti.universitymanagement.base.core.enums.Operation.CREATE;
import static al.edu.fti.universitymanagement.base.core.enums.Operation.UPDATE;
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.ANNOUNCEMENTS_ARE_ONLY_ADDED_BY_ADMINS;
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.GENERIC_NOT_ALLOWED;
import static al.edu.fti.universitymanagement.uniman.core.comment.comment.enums.CommentType.ANNOUNCEMENT;
import static al.edu.fti.universitymanagement.uniman.core.comment.comment.enums.CommentType.TIMELINE;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentValidator implements BaseValidator<CommentDto, CommentEntity> {

    private final CommentDao commentDao;

    /**
     * This method validates the input comment
     * The given entity must follow the rules:
     *  Comment in the timeline must not have a Course Entity FK and status must be TIMELINE
     *  Comment in the Course must have a CourseEntity Not Null and status must be COURSE
     *  Comment in the Course of type ANNOUNCEMENT must only be done by administrator and cannot be replied to
     * @param dto CommentDto
     * @param operation Operation
     */
    @Override
    public void validate(CommentDto dto, Operation operation) {
        if (operation == CREATE) {
            log.info("Validating comment of type create");
            if (dto.getCommentType() == ANNOUNCEMENT) {
                if (dto.getReplyingTo() != null){
                    throw new BadRequestException(ANNOUNCEMENTS_ARE_ONLY_ADDED_BY_ADMINS);
                }
            }
            if (dto.getCommentType().equals(TIMELINE) ){
                if (dto.getReplyingTo() != null) {
                    CommentEntity replyingTo = commentDao.getById(dto.getReplyingTo().getId());
                    if (replyingTo.getCourseEntity() != null)
                        throw new BadRequestException(GENERIC_NOT_ALLOWED);
                }

            }
        }
    }

    @Override
    public void validate(CommentEntity entity, Operation operation) {

    }
}
