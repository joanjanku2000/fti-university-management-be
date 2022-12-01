package al.edu.fti.universitymanagement.uniman.core.user.friendship.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import al.edu.fti.universitymanagement.uniman.core.user.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus.PENDING;

/**
 * Converter Component Class to make the neccessary conversions
 * between Entity And Dto representation of it
 */
@RequiredArgsConstructor
@Component
public class FriendshipConverter implements BaseConverter<FriendshipDto, FriendshipEntity> {

    private final UserDao userDao;
    private final UserConverter userConverter;

    @Override
    public FriendshipDto toDto(FriendshipEntity baseEntity) {
        FriendshipDto friendshipDto = new FriendshipDto();
        friendshipDto.setId(baseEntity.getId());
        friendshipDto.setFriendshipStatus(baseEntity.getStatus());
        friendshipDto.setReceiver(userConverter.toDto(baseEntity.getReceiver()));
        friendshipDto.setSender(userConverter.toDto(baseEntity.getSender()));
        return friendshipDto;
    }

    @Override
    public FriendshipEntity toEntity(FriendshipDto baseDto) {
        FriendshipEntity friendshipEntity = new FriendshipEntity();
        friendshipEntity.setSender(userDao.getById(SecurityUtil.getLoggedUser().getUserDto().getId()));
        friendshipEntity.setReceiver(userDao.getById(baseDto.getReceiver().getId()));
        friendshipEntity.setStatus(PENDING);
        return friendshipEntity;
    }

    /**
     * This method in the context of Friendship Functionality
     * is primarily used by the receiver to either accept or decline
     * the Friendship request
     * @param baseDto updated Dto
     * @param baseEntity needed as entity reference (to avoid hitting the database twice)
     * @return updated entity
     */
    @Override
    public FriendshipEntity toEntity(FriendshipDto baseDto, FriendshipEntity baseEntity) {
        baseEntity.setStatus(baseDto.getFriendshipStatus());
        return baseEntity;
    }
}
