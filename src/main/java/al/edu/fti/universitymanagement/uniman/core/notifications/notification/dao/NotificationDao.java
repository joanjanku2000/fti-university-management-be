package al.edu.fti.universitymanagement.uniman.core.notifications.notification.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.entity.NotificationEntity;

import java.util.List;

public interface NotificationDao extends BaseDao<NotificationEntity, Long> {
    List<NotificationEntity> findAllByTargetId(Long id);
}
