package al.edu.fti.universitymanagement.uniman.security.user;

import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;


@Data
@AllArgsConstructor
public class LoginResponse {
    private String email;
    private List<String> authorities;
    private String bearer;
    private UserDto userDto;
}
