package al.edu.fti.universitymanagement.uniman.core.notifications.notification.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.dto.NotificationDto;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter implements BaseConverter<NotificationDto, NotificationEntity> {

    @Override
    public NotificationDto toDto(NotificationEntity baseEntity) {
        return null;
    }

    @Override
    public NotificationEntity toEntity(NotificationDto baseDto) {
        return null;
    }

    @Override
    public NotificationEntity toEntity(NotificationDto baseDto, NotificationEntity baseEntity) {
        return null;
    }
}
