package al.edu.fti.universitymanagement.uniman.api.course;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import al.edu.fti.universitymanagement.uniman.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import com.jpa.filter.dto.filter.FilterWrap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController extends BaseController<CourseDto, CourseEntity> {

    public CourseController(BaseService<CourseDto, CourseEntity> baseService) {
        super(baseService);
    }

    @Override
    public ResponseEntity<List<CourseDto>> filter( @RequestBody FilterWrap filterWrap) {
        return ResponseEntity.ok(baseService.findAll(filterWrap, CourseEntity.class));
    }
}
