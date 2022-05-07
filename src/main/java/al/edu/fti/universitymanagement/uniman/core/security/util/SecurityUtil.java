package al.edu.fti.universitymanagement.uniman.core.security.util;

import al.edu.fti.universitymanagement.uniman.core.security.user.FtiUser;
import al.edu.fti.universitymanagement.uniman.core.security.user.LoginResponse;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

public class SecurityUtil {

    public static UsernamePasswordAuthenticationToken createAuthenticationFromUserDetails(UserDetails userDetails){
        return new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(),
                userDetails.getAuthorities() == null ?
                        Collections.emptyList() : userDetails.getAuthorities()
        );
    }

    public static LoginResponse toLoginResponse(UserDto userDto, String bearer){
        return new LoginResponse(userDto.getEmail(),Collections.singletonList(userDto.getRole().toString()),bearer);
    }
}