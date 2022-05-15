package al.edu.fti.universitymanagement.uniman.api.like;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.comment.like.dto.LikeDto;
import al.edu.fti.universitymanagement.uniman.core.comment.like.entity.LikeEntity;
import al.edu.fti.universitymanagement.uniman.core.comment.like.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController extends BaseController<LikeDto , LikeEntity> {
    public LikeController(BaseService<LikeDto, LikeEntity> baseService) {
        super(baseService);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<List<LikeDto>> findLikesOfComment(@PathVariable("id") Long commentId){
        return ResponseEntity.ok(((LikeService)baseService).findLikesOfComment(commentId));
    }
}
