package al.edu.fti.universitymanagement.uniman.security.user;

import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@ToString
public class FtiUser implements UserDetails {
    private UserDto userDto;

    public FtiUser(UserDto userDto) {this.userDto = userDto;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority
                (userDto.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    /**
     * Username is email in this system
     * @return email
     */
    @Override
    public String getUsername() {
        return userDto.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
