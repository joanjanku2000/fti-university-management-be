package al.edu.fti.universitymanagement.uniman.core.notifications.notification.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.dto.NotificationDto;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.entity.NotificationEntity;
import al.edu.fti.universitymanagement.uniman.core.user.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.modeler.NotificationInfo;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationConverter implements BaseConverter<NotificationDto, NotificationEntity> {
    private final UserConverter userConverter;
    private final UserDao userDao;
    @Override
    public NotificationDto toDto(NotificationEntity baseEntity) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(baseEntity.getId());
        notificationDto.setMessage(baseEntity.getMessage());
        notificationDto.setNotificationType(baseEntity.getType());
        notificationDto.setUser(userConverter.toDto(baseEntity.getTarget()));
        return notificationDto;
    }

    @Override
    public NotificationEntity toEntity(NotificationDto baseDto) {
        NotificationEntity notificationEntity = new NotificationEntity();
        UserEntity loggedUserAkaNotifiedBy = userDao.getById(SecurityUtil.getLoggedUser().getUserDto().getId());
        UserEntity target = userDao.getById(baseDto.getUser().getId());
        notificationEntity.setMessage(baseDto.getMessage());
        notificationEntity.setTarget(target);
        notificationEntity.setNotifiedBy(loggedUserAkaNotifiedBy);
        notificationEntity.setType(baseDto.getNotificationType());
        log.info("Notification converting done {}",notificationEntity);
        return notificationEntity;
    }

    @Override
    public NotificationEntity toEntity(NotificationDto baseDto, NotificationEntity baseEntity) {
        return null;
    }
}
