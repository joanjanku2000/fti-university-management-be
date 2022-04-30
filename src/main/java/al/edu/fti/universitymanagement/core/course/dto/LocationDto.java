package al.edu.fti.universitymanagement.core.course.dto;

import al.edu.fti.universitymanagement.core.base.dto.BaseDto;
import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import al.edu.fti.universitymanagement.core.course.entity.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationDto extends BaseDto {

    @NotEmpty
    @NotBlank(message = "Name should not be blank")
    private String name;

    @Override
    public Location toEntity(){
        return new Location(name);
    }

    @Override
    public Location toEntity(BaseEntity location){
       ((Location) location).setName(name);
        return (Location) location;
    }
}
