package al.edu.fti.universitymanagement.uniman.core.user.user.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.user.user.enums.Gender;
import al.edu.fti.universitymanagement.uniman.core.user.user.enums.Role;
import al.edu.fti.universitymanagement.uniman.core.user.user.dto.UserDto;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements BaseConverter<UserDto, UserEntity> {

    @Override
    public UserDto toDto(UserEntity baseEntity) {
        UserDto userDto = new UserDto();

        userDto.setId(baseEntity.getId());

        userDto.setName(baseEntity.getName());
        userDto.setBirthday(baseEntity.getBirthday());
        userDto.setGender(baseEntity.getGender() == Gender.MALE ? 0 : 1);
        userDto.setEmail(baseEntity.getEmail());
        userDto.setIdNumber(baseEntity.getIdNumber());
        userDto.setPicture(baseEntity.getPicture());
        userDto.setLastName(baseEntity.getLastName());
        userDto.setRole(baseEntity.getRole());
        userDto.setPassword(baseEntity.getPassword());
        return userDto;
    }

    @Override
    public UserEntity toEntity(UserDto baseDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(new BCryptPasswordEncoder().encode(baseDto.getMicrosoftAccessToken()));
        System.gc(); // to maybe delete BCrypt instance
        return convertBaseProperties(baseDto, userEntity);
    }

    @Override
    public UserEntity toEntity(UserDto baseDto, UserEntity userEntity) {
        return convertBaseProperties(baseDto, userEntity);
    }

    private UserEntity convertBaseProperties(UserDto baseDto, UserEntity userEntity) {
        userEntity.setName(baseDto.getName());
        userEntity.setBirthday(baseDto.getBirthday());
        if (baseDto.getGender() != null)
        userEntity.setGender(baseDto.getGender() == 0 ? Gender.MALE : Gender.FEMALE);
        userEntity.setEmail(baseDto.getEmail());
        if (baseDto.getIdNumber() != null)
        userEntity.setIdNumber(baseDto.getIdNumber());
        if (baseDto.getPicture() != null)
        userEntity.setPicture(baseDto.getPicture());
        if (baseDto.getLastName() != null)
        userEntity.setLastName(baseDto.getLastName());
        userEntity.setRole(Role.STUDENT);
        return userEntity;
    }
}
