package al.edu.fti.universitymanagement.uniman.api.course;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController extends BaseController<CourseDto, CourseEntity> {

    public CourseController(BaseService<CourseDto, CourseEntity> baseService) {
        super(baseService);
    }
}
