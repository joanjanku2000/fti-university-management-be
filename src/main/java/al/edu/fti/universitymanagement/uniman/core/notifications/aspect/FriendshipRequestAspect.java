package al.edu.fti.universitymanagement.uniman.core.notifications.aspect;

import al.edu.fti.universitymanagement.uniman.core.notifications.notification.dto.NotificationDto;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationMessage;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.service.NotificationService;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dao.FriendshipDao;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.service.FriendshipService;
import al.edu.fti.universitymanagement.uniman.core.user.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationType.FRIEND_REQUEST;

@Slf4j
@Aspect
@Configuration
public class FriendshipRequestAspect {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private FriendshipDao friendshipDao;

    @AfterReturning("execution(* al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl.*(..)) " +
            "|| execution(* al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl.*(..))")
    public void after(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Object[] arguments = joinPoint.getArgs();

        if (target instanceof FriendshipService) {
            if (((MethodSignature) joinPoint.getSignature()).getMethod().getName().contains("save")) {
                log.info(
                        "Friendship request, notifying user"
                );
                if (arguments.length == 1) {
                    FriendshipDto friendshipDto = (FriendshipDto) arguments[0];
                    FriendshipEntity friendshipEntity = friendshipDao.getById(friendshipDto.getId());
                    UserEntity toBeNotifiedAkaTarget = userDao.getById(friendshipEntity.getReceiver().getId());
                    log.debug("To be notified {}",toBeNotifiedAkaTarget.getFullName());
                    UserEntity loggedUserAkaNotifiedBy = userDao.getById(SecurityUtil.getLoggedUser().getUserDto().getId());

                    NotificationDto notificationDto = new NotificationDto();
                    notificationDto.setNotificationType(FRIEND_REQUEST);
                    notificationDto.setUser(userConverter.toDto(toBeNotifiedAkaTarget));
                    notificationDto.setMessage(NotificationMessage
                            .getUserSentYouAFriendRequest(loggedUserAkaNotifiedBy.getFullName()));

                    notificationService.save(notificationDto);
                    log.info("End of aspect execution,notification of type friendship request saved to db");
                }
            }
            if (((MethodSignature) joinPoint.getSignature()).getMethod().getName().contains("update")) {
                log.info(
                        "Friendship request response, notifying user"
                );
                if (arguments.length == 1) {
                    FriendshipDto friendshipDto = (FriendshipDto) arguments[0];
                    FriendshipEntity friendshipEntity = friendshipDao.getById(friendshipDto.getId());
                    UserEntity toBeNotifiedAkaTarget = userDao.getById(friendshipEntity.getSender().getId());
                    log.debug("To be notified {}",toBeNotifiedAkaTarget.getFullName());
                    UserEntity loggedUserAkaNotifiedBy = userDao.getById(SecurityUtil.getLoggedUser().getUserDto().getId());

                    NotificationDto notificationDto = new NotificationDto();
                    notificationDto.setNotificationType(FRIEND_REQUEST);
                    notificationDto.setUser(userConverter.toDto(toBeNotifiedAkaTarget));

                    notificationDto.setMessage(NotificationMessage
                            .getUserRespondedToYourFriendRequest(loggedUserAkaNotifiedBy.getFullName(),friendshipDto.getFriendshipStatus().name()));

                    notificationService.save(notificationDto);
                    log.info("End of aspect execution,notification of type friendship request saved to db");
                }
            }
        }


    }
}
