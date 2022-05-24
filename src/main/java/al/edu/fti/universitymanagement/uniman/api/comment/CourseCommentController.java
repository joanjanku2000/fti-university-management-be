package al.edu.fti.universitymanagement.uniman.api.comment;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.dto.CommentDto;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment/course")
public class CourseCommentController extends BaseController<CommentDto, CommentEntity> {

    public CourseCommentController(BaseService<CommentDto, CommentEntity> baseService) {
        super(baseService);
    }

    @GetMapping("/timeline/{userId}")
    public ResponseEntity<List<CommentDto>> getUpdatesOfUser(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(((CommentService)baseService).getUserUpdates(userId));
    }
}
