package al.edu.fti.universitymanagement.uniman.core.user.user.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.NotFoundException;
import al.edu.fti.universitymanagement.base.core.validator.exceptions.messages.ErrorMessages;
import al.edu.fti.universitymanagement.uniman.core.user.user.dao.UserDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import com.mongo.filter.dao.FilterRepo;
import com.mongo.filter.dao.FilterRepoJpaImpl;
import com.mongo.filter.dto.filter.FilterWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService extends BaseServiceAbstractImpl<UserEntity, UserDto> {


    public UserService(BaseDao<UserEntity, Long> baseDao, BaseConverter<UserDto, UserEntity> baseConverter,
                       BaseValidator<UserDto, UserEntity> baseValidator, FilterRepo filterRepo) {
        super(baseDao, baseConverter, baseValidator, filterRepo);
    }

    public UserDto findByEmail(String email) {
        return baseConverter.toDto(((UserDao) baseDao).findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND)));
    }

    public void updateLoginTokenPassword(UserDto userDto) {
        UserEntity userEntity = baseDao.getById(userDto.getId());
        userEntity.setPassword(new BCryptPasswordEncoder().encode("password"));
        baseDao.save(userEntity);
        log.info("Successfully added acces token as psw");
    }

    public Boolean existsByEmail(String email){
        UserEntity userEntity = ((UserDao) baseDao).findByEmail(email)
                .orElse(null);
        log.info("User exists {}",userEntity != null);
        return userEntity != null;
    }

    public void createUserAtFirstLogin(UserDto userDto){
        userDto.setPassword("password");
        super.save(userDto);
    }

    public Boolean userExists(String email){
        return ((UserDao)baseDao).userExists(email);
    }

    @Override
    public List<UserDto> findAll(FilterWrap filterWrap, Class<UserEntity> clazz) {
        return filterRepo.filter(filterWrap,clazz).stream().map(baseConverter::toDto).collect(Collectors.toList());
    }
}
