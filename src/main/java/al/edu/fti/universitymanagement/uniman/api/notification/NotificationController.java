package al.edu.fti.universitymanagement.uniman.api.notification;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.dto.NotificationDto;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.entity.NotificationEntity;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController extends BaseController<NotificationDto, NotificationEntity> {

    public NotificationController(BaseService<NotificationDto, NotificationEntity> baseService) {
        super(baseService);
    }


    @GetMapping("/all")
    public ResponseEntity<List<NotificationDto>> getNotificationsOfUser(){
        return ResponseEntity.ok(((NotificationService)baseService).findNotificationsOfLoggedUser());
    }


    /**
     * For security purposes this method is overriden to null
     * @param courseDto CourseDto
     * @return null
     */
    @Override
    public ResponseEntity<NotificationDto> save(NotificationDto courseDto) {
        return ResponseEntity.ok(null);
    }

    /**
     * For security purposes this method is overriden to null
     * @param courseDto CourseDto
     * @return null
     */
    @Override
    public ResponseEntity<NotificationDto> update(NotificationDto courseDto) {
        return ResponseEntity.ok(null);
    }

    /**
     * For security purposes this method is overriden to null
     * @param id Long
     * @return null
     */
    @Override
    public ResponseEntity<NotificationDto> delete(Long id) {
        return ResponseEntity.ok(null);
    }

    /**
     * For security purposes this method is overriden to null
     * @param id Long
     * @return null
     */
    @Override
    public ResponseEntity<NotificationDto> getById(Long id) {
        return ResponseEntity.ok(null);
    }


}
