package al.edu.fti.universitymanagement.uniman.core.course.converter;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.uniman.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.LocationEntity;
import org.springframework.stereotype.Service;

@Service
public class LocationConverterImpl implements BaseConverter<LocationDto, LocationEntity> {

    @Override
    public LocationDto toDto(LocationEntity baseEntity) {
        return new LocationDto(baseEntity.getId(),baseEntity.getName());
    }

    @Override
    public LocationEntity toEntity(LocationDto baseDto) {
        return new LocationEntity(baseDto.getName());
    }

    @Override
    public LocationEntity toEntity(LocationDto baseDto, LocationEntity baseEntity) {
        baseEntity.setName(baseDto.getName());
        return baseEntity;
    }

}
