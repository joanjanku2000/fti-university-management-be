package al.edu.fti.universitymanagement.core.base.converter;

import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;


public interface BaseConverter<T extends BaseDto, S extends BaseEntity> {
     T toDto(S baseEntity);

     S toEntity(T baseDto);

     S toEntity(T baseDto, S baseEntity);
}
