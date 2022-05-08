package al.edu.fti.universitymanagement.uniman.api.userCourse;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course-user")
public class UserCourseController extends BaseController<UserCourseDto, UserCourseEntity> {
    public UserCourseController(BaseService<UserCourseDto, UserCourseEntity> baseService) {
        super(baseService);
    }
}
