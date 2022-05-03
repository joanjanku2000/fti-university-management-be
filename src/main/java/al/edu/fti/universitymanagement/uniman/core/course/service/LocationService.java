package al.edu.fti.universitymanagement.uniman.core.course.service;

import al.edu.fti.universitymanagement.base.core.converter.BaseConverter;
import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.base.core.service.impl.BaseServiceAbstractImpl;
import al.edu.fti.universitymanagement.base.core.validator.BaseValidator;
import al.edu.fti.universitymanagement.uniman.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.LocationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocationService extends BaseServiceAbstractImpl<LocationEntity, LocationDto> {

    public LocationService(BaseDao<LocationEntity, Long> baseDao, BaseConverter<LocationDto, LocationEntity> baseConverter, BaseValidator<LocationDto, LocationEntity> baseValidator) {
        super(baseDao, baseConverter, baseValidator);
    }
}
