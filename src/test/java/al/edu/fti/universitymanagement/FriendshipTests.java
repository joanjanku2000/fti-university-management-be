package al.edu.fti.universitymanagement;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dao.FriendshipDao;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.validator.FriendshipValidator;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.core.user.user.enums.Role;
import al.edu.fti.universitymanagement.uniman.security.user.FtiUser;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class FriendshipTests {
    @Mock
    private FriendshipDao friendshipDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private FriendshipValidator friendshipValidator;

    @BeforeAll
    public static void initializeSecurityContext() {
        UserDto sender = new UserDto();
        sender.setId(1L);
        sender.setRole(Role.STUDENT);
        sender.setEmail("jo@fti.edu.al");
        sender.setName("Joan");
        sender.setName("Janku");
        sender.setPassword("1234Test");

        UserDetails userDetails = new FtiUser(sender);

        UsernamePasswordAuthenticationToken authentication =
                SecurityUtil.createAuthenticationFromUserDetails(userDetails);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        SecurityContextHolder.setContext(securityContext);

    }

    @Test
    public void sendFriendshipRequestSuccessTest() {
        UserEntity sender = new UserEntity();
        sender.setId(1L);

        UserDto senderDto = new UserDto();
        senderDto.setId(1L);

        UserDto receiver = new UserDto();
        receiver.setId(2L);

        FriendshipDto friendshipDto = new FriendshipDto();
        friendshipDto.setSender(senderDto);
        friendshipDto.setReceiver(receiver);

        Mockito.when(userDao.getById(any())).thenReturn(sender);

        assertDoesNotThrow(() -> friendshipValidator.validate(friendshipDto, Operation.CREATE));

    }

    @Test
    @DisplayName("Fail: Cannot send request to yourself")
    public void sendFriendshipRequestFail1Test() {
        UserEntity sender = new UserEntity();
        sender.setId(1L);

        UserDto senderDto = new UserDto();
        senderDto.setId(1L);

        UserDto receiver = new UserDto();
        receiver.setId(1L);

        FriendshipDto friendshipDto = new FriendshipDto();
        friendshipDto.setSender(senderDto);
        friendshipDto.setReceiver(receiver);

        Mockito.when(userDao.getById(any())).thenReturn(sender);

        assertDoesNotThrow(() -> friendshipValidator.validate(friendshipDto, Operation.CREATE));
    }

    @Test
    public void sendFriendshipRequestSuccessTest2() {
        UserEntity sender = new UserEntity();
        sender.setId(1L);
        UserEntity receiverEntity = new UserEntity();
        receiverEntity.setId(2L);

        UserDto senderDto = new UserDto();
        senderDto.setId(1L);

        UserDto receiver = new UserDto();
        receiver.setId(1L);

        FriendshipDto friendshipDto = new FriendshipDto();
        friendshipDto.setSender(senderDto);
        friendshipDto.setReceiver(receiver);

        FriendshipEntity friendshipEntity = new FriendshipEntity();
        friendshipEntity.setStatus(FriendshipStatus.FRIENDS);
        friendshipEntity.setSender(sender);
        friendshipEntity.setReceiver(receiverEntity);

        Mockito.when(friendshipDao.getById(any())).thenReturn(friendshipEntity);
        Mockito.when(userDao.getById(any())).thenReturn(sender);

        assertThrows(Exception.class, () -> friendshipValidator.validate(friendshipDto, Operation.UPDATE));
    }
}
