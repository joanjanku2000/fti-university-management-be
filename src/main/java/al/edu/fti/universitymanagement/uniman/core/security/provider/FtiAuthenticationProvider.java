package al.edu.fti.universitymanagement.uniman.core.security.provider;

import al.edu.fti.universitymanagement.uniman.core.security.user.FtiUser;
import al.edu.fti.universitymanagement.uniman.core.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FtiAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Using my custom auth provider");
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        try {
            FtiUser foundEntity = (FtiUser) userDetailsService.loadUserByUsername(username);
            if (new BCryptPasswordEncoder().matches(pwd, foundEntity.getPassword())) {
                return SecurityUtil.createAuthenticationFromUserDetails(foundEntity);
            } else {
                throw new BadCredentialsException("Passwords don't match");
            }
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Email not found");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
