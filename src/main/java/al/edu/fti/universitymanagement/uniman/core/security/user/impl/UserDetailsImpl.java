package al.edu.fti.universitymanagement.uniman.core.security.user.impl;

import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotFoundException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages;
import al.edu.fti.universitymanagement.uniman.core.security.user.FtiUser;
import al.edu.fti.universitymanagement.uniman.core.user.converter.UserConverter;
import al.edu.fti.universitymanagement.uniman.core.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {
    private final UserDao userRepository;
    private final UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String email) {
        log.info("Loading user by useername");
        UserDto userDto =
                userConverter.toDto(
                        userRepository.findByEmail(email)
                                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND + email))
                );

        return new FtiUser(userDto);
    }

}
