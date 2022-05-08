package al.edu.fti.universitymanagement.uniman.api.userCourse;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.dto.UserCourseDto;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import al.edu.fti.universitymanagement.uniman.core.userCourse.service.UserCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/course-user")
public class UserCourseController extends BaseController<UserCourseDto, UserCourseEntity> {

    public UserCourseController(BaseService<UserCourseDto, UserCourseEntity> baseService) {
        super(baseService);
        log.info("Creating Course api controller");
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<UserCourseDto>> findAll(RequestDto requestDto) {
        log.info("Controller: Finding all users courses");
        return ResponseEntity.ok(baseService.findAll(requestDto));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<List<UserDto>> findStudentsOfCourse(@PathVariable Long id){
        return ResponseEntity.ok(((UserCourseService) baseService).findStudentsOfCourse(id));
    }
}
