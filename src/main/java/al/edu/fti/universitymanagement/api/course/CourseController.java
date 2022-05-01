package al.edu.fti.universitymanagement.api.course;

import al.edu.fti.universitymanagement.core.base.service.BaseService;
import al.edu.fti.universitymanagement.core.course.dto.CourseDto;
import al.edu.fti.universitymanagement.core.course.entity.CourseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final BaseService<CourseDto, CourseEntity> courseService;

    @PostMapping
    public ResponseEntity<CourseDto> save(@RequestBody CourseDto courseDto){
        log.info("Got {} from request",courseDto);
        return ResponseEntity.ok(courseService.save(courseDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.read(id));
    }
}
