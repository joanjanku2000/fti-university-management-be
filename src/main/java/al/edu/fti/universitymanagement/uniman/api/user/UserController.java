package al.edu.fti.universitymanagement.uniman.api.user;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<UserDto , UserEntity> {

    public UserController(BaseService<UserDto, UserEntity> baseService) {
        super(baseService);
    }
}
