package al.edu.fti.universitymanagement.uniman.core.security.provider;

import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotFoundException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages;
import al.edu.fti.universitymanagement.uniman.core.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class FtiAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDao userDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Using my custom auth provider");
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        UserEntity foundEntity = userDao.findByEmail(username)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND));
        if (new BCryptPasswordEncoder().matches(pwd,foundEntity.getPassword())) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,pwd, Collections.emptyList());

            return new UsernamePasswordAuthenticationToken(username,pwd, Collections.emptyList()); // for the moment
        } else {
            throw new BadCredentialsException("Passwords don't match");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
         return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
