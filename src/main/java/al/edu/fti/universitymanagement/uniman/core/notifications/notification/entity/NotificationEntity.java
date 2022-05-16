package al.edu.fti.universitymanagement.uniman.core.notifications.notification.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationType;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity @Table(name = "notification_user")
@Where(clause = "active=true")
@SQLDelete(sql = "update notification_user set active=false where id = ?")
public class NotificationEntity extends BaseEntity {

    private String message;

    @Enumerated(EnumType.ORDINAL)
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity target;
}
