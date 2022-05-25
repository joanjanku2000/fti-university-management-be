package al.edu.fti.universitymanagement.uniman.core.notifications.aspect;

import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.service.CommentService;
import al.edu.fti.universitymanagement.uniman.core.course.dao.CourseDao;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.dto.NotificationDto;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationMessage;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationType;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.service.NotificationService;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dao.FriendshipDao;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.service.FriendshipService;
import al.edu.fti.universitymanagement.uniman.core.user.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.service.UserCourseService;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

import static al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationMessage.getTypeMessageExcludingFriendshipMap;
import static al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationType.FRIEND_REQUEST;
import static al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus.FRIENDS;

@Slf4j
@Aspect
@Configuration
@RequiredArgsConstructor
public class FtiAspect {

    private final NotificationService notificationService;
    private final UserDao userDao;
    private final UserConverter userConverter;
    private final FriendshipDao friendshipDao;
    private final CourseDao courseDao;

    /**
     * This AOP method notifies the participating entities in a friendship request
     * that the request has been approved/declined or that a friend request has been sent
     * @param joinPoint
     */
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
                    log.debug("To be notified {}", toBeNotifiedAkaTarget.getFullName());
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
                    log.debug("To be notified {}", toBeNotifiedAkaTarget.getFullName());
                    UserEntity loggedUserAkaNotifiedBy = userDao.getById(SecurityUtil.getLoggedUser().getUserDto().getId());

                    NotificationDto notificationDto = new NotificationDto();
                    notificationDto.setNotificationType(FRIEND_REQUEST);
                    notificationDto.setUser(userConverter.toDto(toBeNotifiedAkaTarget));

                    notificationDto.setMessage(NotificationMessage
                            .getUserRespondedToYourFriendRequest(loggedUserAkaNotifiedBy.getFullName(), friendshipDto.getFriendshipStatus().name()));

                    notificationService.save(notificationDto);
                    log.info("End of aspect execution,notification of type friendship request saved to db");
                }
            }
        }
    }

    /**
     * This AOP method notifies all friends of the user that the user
     * is updating the timeline
     *
     * @param joinPoint
     * @throws Throwable
     */

    @AfterReturning("execution(* al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl.*(..)) " +
            "|| execution(* al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl.*(..))")
    public void timelineUpdateNotification(JoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        Object[] arguments = joinPoint.getArgs();
        if (target instanceof CommentService) {

            if (((MethodSignature) joinPoint.getSignature()).getMethod().getName().contains("save")) {
                log.info(
                        "Timeline update, notifying friends of user"
                );

                if (arguments.length == 1) {
                    CommentDto commentDto = (CommentDto) arguments[0];
                    if (commentDto.getUserDto() != null) {
                        UserEntity userEntity = userDao.getById(commentDto.getUserDto().getId());
                        List<UserEntity> friendsOfUser = getFriendsOfUser(userEntity);

                        notifyFriends(userEntity, friendsOfUser, NotificationType.POST_UPDATE, null);
                    }
                }

            }
        }

    }




    @AfterReturning("execution(* al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl.*(..)) " +
            "|| execution(* al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl.*(..))")
    public void timelineUpdateWhenUserJoinsCourses(JoinPoint joinPoint){
        Object target = joinPoint.getTarget();
        Object[] arguments = joinPoint.getArgs();
        if (target instanceof UserCourseService) {
            if (((MethodSignature) joinPoint.getSignature()).getMethod().getName().contains("save")) {
                log.info(
                        "User joining course, notifying friends of user"
                );
                if (arguments.length == 1) {
                    UserCourseDto userCourseDto = (UserCourseDto) arguments[0];
                    UserEntity userEntity = userDao.getById(userCourseDto.getUserDto().getId());
                    CourseEntity courseEntity = courseDao.getById(userCourseDto.getCourseDto().getId());
                    List<UserEntity> friendsOfUser = getFriendsOfUser(userEntity);
                    notifyFriends(userEntity, friendsOfUser, NotificationType.USER_JOINED_COURSE,courseEntity.getName());
                }
            }
        }
    }

    /**
     * Utility method to notify friends of user aiming to avoid code duplication
     * @param userEntity UserEntity
     * @param friendsOfUser List
     */
    private void notifyFriends(UserEntity userEntity, List<UserEntity> friendsOfUser, NotificationType notificationType, String coursename) {
        List<NotificationDto> notificationDtos =
                friendsOfUser.stream().map(friend ->
                        createNotification(userEntity, friend, notificationType,coursename))
                        .collect(Collectors.toList());
        notificationDtos.forEach(notificationService::save);
    }

    /**
     * This method creates a notification DTO and persists it to database
     * by taking into consideration the sender,receiver and notification type
     * and accordingly calculating the necessary payload except FriendshipRequest
     * (they are handed differently)
     *
     * @param notifiedBy       UserEntity
     * @param target           UserEntity
     * @param notificationType NotificationType
     * @return NotificationDto
     */
    private NotificationDto createNotification(UserEntity notifiedBy, UserEntity target, NotificationType notificationType, String courseName) {
        log.info("Creating notification {} {} {}", notifiedBy.getFullName(), target.getFullName(), notificationType);
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setNotificationType(notificationType);
        notificationDto.setMessage(courseName == null ? getTypeMessageExcludingFriendshipMap()
                .get(notificationType).replace(":user", notifiedBy.getFullName())
                :
                        getTypeMessageExcludingFriendshipMap()
                                .get(notificationType).replace(":user", notifiedBy.getFullName())
                .replace(":course",courseName)
                );
        notificationDto.setUser(userConverter.toDto(target));
        return notificationDto;

    }

    /**
     * Utility method used to extract the friends of user given as a parameter
     * @param userEntity UserEntity
     * @return List<\<UserEntity\>>
     */
    private List<UserEntity> getFriendsOfUser(UserEntity userEntity) {
        return friendshipDao
                .findAllBySenderIdOrReceiverIdAndStatus(userEntity.getId()
                        , userEntity.getId(), FRIENDS)
                .stream().map(f -> !f.getSender().getId()
                .equals(userEntity.getId()) ? f.getSender()
                : f.getReceiver())
                .distinct()
                .filter(u -> !u.equals(userEntity))
                .collect(Collectors.toList());
    }
}

