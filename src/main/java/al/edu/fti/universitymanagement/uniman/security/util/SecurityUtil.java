package al.edu.fti.universitymanagement.uniman.security.util;

import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotAllowedException;
import al.edu.fti.universitymanagement.uniman.security.user.FtiUser;
import al.edu.fti.universitymanagement.uniman.security.user.LoginResponse;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public static FtiUser getLoggedUser(){
      try {
          return (FtiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      } catch (Exception e){
          throw new NotAllowedException("Not allowed");
      }
    }
}
