package al.edu.fti.universitymanagement.uniman.core.notifications.notification.validator;

import al.edu.fti.universitymanagement.base.core.enums.Operation;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.dto.NotificationDto;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.entity.NotificationEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationValidator implements BaseValidator<NotificationDto, NotificationEntity> {
    @Override
    public void validate(NotificationDto dto, Operation operation) {
        // Empty body
    }

    @Override
    public void validate(NotificationEntity entity, Operation operation) {
        // Empty body
    }
}
