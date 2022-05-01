package al.edu.fti.universitymanagement.api.location;

import al.edu.fti.universitymanagement.api.base.BaseController;
import al.edu.fti.universitymanagement.core.base.service.BaseService;
import al.edu.fti.universitymanagement.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.core.course.entity.Location;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController extends BaseController<LocationDto, Location> {
    public LocationController(BaseService<LocationDto, Location> baseService) {
        super(baseService);
    }
}
