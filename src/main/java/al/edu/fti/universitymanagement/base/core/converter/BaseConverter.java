package al.edu.fti.universitymanagement.base.core.converter;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;


public interface BaseConverter<T extends BaseDto , S> {
     T toDto(S baseEntity);

     S toEntity(T baseDto);

     S toEntity(T baseDto, S baseEntity);

}
