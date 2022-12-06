package al.edu.fti.universitymanagement.uniman.core.notifications.notification.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.dao.NotificationDao;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.dto.NotificationDto;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.entity.NotificationEntity;
import al.edu.fti.universitymanagement.uniman.security.util.SecurityUtil;
import com.jpa.filter.dao.FilterRepo;
import com.jpa.filter.dao.FilterRepoJpaImpl;
import com.jpa.filter.dto.filter.FilterWrap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService extends BaseServiceAbstractImpl<NotificationEntity, NotificationDto> {


    public NotificationService(BaseDao<NotificationEntity, Long> baseDao, BaseConverter<NotificationDto, NotificationEntity> baseConverter, BaseValidator<NotificationDto, NotificationEntity> baseValidator, FilterRepo filterRepo) {
        super(baseDao, baseConverter, baseValidator, filterRepo);
    }

    public List<NotificationDto> findNotificationsOfLoggedUser(){
        Long loggedUserId = SecurityUtil.getLoggedUser().getUserDto().getId();
        return ((NotificationDao) baseDao)
                .findAllByTargetId(loggedUserId).stream()
                .map(baseConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> findAll(FilterWrap filterWrap, Class<NotificationEntity> clazz) {
        return filterRepo.filter(filterWrap,clazz).stream().map(baseConverter::toDto).collect(Collectors.toList());
    }
}
