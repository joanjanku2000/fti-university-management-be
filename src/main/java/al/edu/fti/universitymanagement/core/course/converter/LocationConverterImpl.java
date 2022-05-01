package al.edu.fti.universitymanagement.core.course.converter;

import al.edu.fti.universitymanagement.core.base.converter.BaseConverter;
import al.edu.fti.universitymanagement.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.core.course.entity.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationConverterImpl implements BaseConverter<LocationDto,Location> {

    @Override
    public LocationDto toDto(Location baseEntity) {
        return new LocationDto(baseEntity.getId(),baseEntity.getName());
    }

    @Override
    public Location toEntity(LocationDto baseDto) {
        return new Location(baseDto.getName());
    }

    @Override
    public Location toEntity(LocationDto baseDto, Location baseEntity) {
        baseEntity.setName(baseDto.getName());
        return baseEntity;
    }

}
