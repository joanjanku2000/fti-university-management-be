package al.edu.fti.universitymanagement.core.course.dto;

import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import al.edu.fti.universitymanagement.core.course.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data  @NoArgsConstructor
public class LocationDto extends BaseDto {

    @NotEmpty
    @NotBlank(message = "Name should not be blank")
    private String name;

    public LocationDto(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public Location toEntity() {
        Location location = new Location(name);
        location.setActive(true)
        ;
        return location;
    }

    @Override
    public Location toEntity(BaseEntity location) {
        ((Location) location).setName(name);
        return (Location) location;
    }
}
