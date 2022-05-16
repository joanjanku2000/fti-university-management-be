package al.edu.fti.universitymanagement.uniman.core.notifications.notification.dto;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationType;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class NotificationDto extends BaseDto {
    private String message;
    private UserDto user;
    private NotificationType notificationType;
}
