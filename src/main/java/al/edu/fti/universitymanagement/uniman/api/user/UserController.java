package al.edu.fti.universitymanagement.uniman.api.user;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import al.edu.fti.universitymanagement.uniman.core.user.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<UserDto , UserEntity> {

    public UserController(BaseService<UserDto, UserEntity> baseService) {
        super(baseService);
    }

    @GetMapping("/{email}/exists")
    public ResponseEntity<Boolean> userExists(@PathVariable("email") String email){
        return ResponseEntity.ok(((UserService)baseService).userExists(email));
    }
}
