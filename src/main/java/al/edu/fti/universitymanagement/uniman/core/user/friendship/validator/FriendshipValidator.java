package al.edu.fti.universitymanagement.uniman.core.user.friendship.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotAllowedException;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dao.FriendshipDao;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static al.edu.fti.universitymanagement.base.core.enums.Operation.*;
import static al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages.GENERIC_NOT_ALLOWED;

@RequiredArgsConstructor
@Component
public class FriendshipValidator implements BaseValidator<FriendshipDto, FriendshipEntity> {

    private final UserDao userDao;
    private final FriendshipDao friendshipDao;
    /**
     * <h2>Important security feature/h2>
     * This method validates if logged user is actually trying to send the friendship request
     * and more importantly if the receiver of the friendship requests is actually the one
     * accepting or declining it
     * @param dto FriendshipDto
     * @param operation Operation Type
     * @throws NotAllowedException
     */
    @Override
    public void validate(FriendshipDto dto, Operation operation) {
        UserEntity loggedUser = userDao.getById(SecurityUtil.getLoggedUser().getUserDto().getId());

        if (operation == CREATE) {
            if (dto.getSender()!= null && dto.getSender().getId().equals(loggedUser.getId()) == Boolean.FALSE) {
                throw new NotAllowedException(GENERIC_NOT_ALLOWED);
            }
        }

        if (operation == UPDATE){
            FriendshipEntity friendshipEntity = friendshipDao.getById(dto.getId());
            if (friendshipEntity.getReceiver().getId().equals(loggedUser.getId()) == Boolean.FALSE) {
                throw new NotAllowedException(GENERIC_NOT_ALLOWED);
            }
        }

    }

    /**
     * <h2>Important security feature</h2>
     * This method validates if the logged user trying to perform the delete action
     * on a friendship is one of the participants in this relationship
     * @param entity entity to be deleted
     * @param operation Operation Type
     * @throws NotAllowedException
     */
    @Override
    public void validate(FriendshipEntity entity, Operation operation) {
        UserEntity loggedUser = userDao.getById(SecurityUtil.getLoggedUser().getUserDto().getId());

        if (operation == DELETE) {
            if (entity.getReceiver().getId().equals(loggedUser.getId()) == Boolean.FALSE
                        || entity.getSender().getId().equals(loggedUser.getId()) == Boolean.FALSE) {
                throw new NotAllowedException(GENERIC_NOT_ALLOWED);
            }
        }

    }
}
