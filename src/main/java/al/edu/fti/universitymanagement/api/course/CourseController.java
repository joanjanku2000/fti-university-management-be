package al.edu.fti.universitymanagement.api.course;

import al.edu.fti.universitymanagement.core.base.service.BaseService;
import al.edu.fti.universitymanagement.core.course.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final BaseService<CourseDto> courseService;

    @PostMapping
    public ResponseEntity<CourseDto> save(@RequestBody CourseDto courseDto){
        log.info("Got {} from request",courseDto);
        return ResponseEntity.ok(courseService.save(courseDto));
    }
}
