package al.edu.fti.universitymanagement.core.base.converter.impl;

import al.edu.fti.universitymanagement.core.base.converter.BaseConverter;
import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import org.springframework.stereotype.Component;


@Component(value = "base")
public class BaseConverterImpl implements BaseConverter<BaseDto, BaseEntity> {
    @Override
    public BaseDto toDto(BaseEntity baseEntity) {
        return new BaseDto();
    }

    @Override
    public BaseEntity toEntity(BaseDto baseDto) {
        return null;
    }

    @Override
    public BaseEntity toEntity(BaseDto baseDto, BaseEntity baseEntity) {
        return null;
    }
}
