package al.edu.fti.universitymanagement.uniman.api.friendship;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.dto.FriendshipDto;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/friendship")
public class FriendshipController extends BaseController<FriendshipDto, FriendshipEntity> {
    public FriendshipController(BaseService<FriendshipDto, FriendshipEntity> baseService) {
        super(baseService);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<FriendshipDto> findFriendsOfUser(Long id){
        // TODO IMPLEMENTATION
        return ResponseEntity.ok(new FriendshipDto());
    }



}
