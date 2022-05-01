package al.edu.fti.universitymanagement.core.course.converter.impl;

import al.edu.fti.universitymanagement.core.course.converter.LocationConverter;
import al.edu.fti.universitymanagement.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.core.course.entity.Location;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LocationConverterImpl implements LocationConverter {

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
        return null;
    }

}
