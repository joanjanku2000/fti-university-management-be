package al.edu.fti.universitymanagement.uniman.api.location;

import al.edu.fti.universitymanagement.base.api.BaseController;
import al.edu.fti.universitymanagement.base.core.service.BaseService;
import al.edu.fti.universitymanagement.uniman.core.course.dto.LocationDto;
import al.edu.fti.universitymanagement.uniman.core.course.entity.LocationEntity;
import com.mongo.filter.dto.filter.FilterWrap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController extends BaseController<LocationDto, LocationEntity> {
    public LocationController(BaseService<LocationDto, LocationEntity> baseService) {
        super(baseService);
    }

    @Override
    public ResponseEntity<List<LocationDto>> filter(@RequestBody FilterWrap filterWrap) {
        return ResponseEntity.ok(baseService.findAll(filterWrap, LocationEntity.class));

    }
}
