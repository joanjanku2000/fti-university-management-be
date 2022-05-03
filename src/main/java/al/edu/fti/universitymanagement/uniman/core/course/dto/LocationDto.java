package al.edu.fti.universitymanagement.uniman.core.course.dto;

import al.edu.fti.universitymanagement.base.core.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class LocationDto extends BaseDto {

    @NotEmpty
    @NotBlank(message = "Name should not be blank")
    private String name;

    public LocationDto(Long id, String name) {
        super(id);
        this.name = name;
    }

}
