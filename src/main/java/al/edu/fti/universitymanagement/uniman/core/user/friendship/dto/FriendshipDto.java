package al.edu.fti.universitymanagement.uniman.core.user.friendship.dto;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import lombok.Data;

@Data
public class FriendshipDto extends BaseDto {
    private UserDto sender;
    private UserDto receiver;
    private FriendshipStatus friendshipStatus;
}
