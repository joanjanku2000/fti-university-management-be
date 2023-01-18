package al.edu.fti.universitymanagement.uniman.api.friendship;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.dto.RequestDto;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.service.FriendshipService;
import com.jpa.filter.dto.FilterWrap;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friendship")
public class FriendshipController extends BaseController<FriendshipDto, FriendshipEntity> {

    public FriendshipController(BaseService<FriendshipDto, FriendshipEntity> baseService) {
        super(baseService);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<FriendshipDto>> findFriendsOfUser(@PathVariable("id") Long id
            , @RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(((FriendshipService) baseService).friendshipOfUser(id, requestDto));
    }

    @GetMapping("/user/{id}/requests")
    public ResponseEntity<List<FriendshipDto>> findFriendRequestsOfUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(((FriendshipService) baseService).friendshipRequestsOfUser(id));
    }


    @Override
    public ResponseEntity<List<FriendshipDto>> filter(@RequestBody FilterWrap filterWrap) {
        return ResponseEntity.ok(baseService.findAll(filterWrap, FriendshipEntity.class));
    }
}
