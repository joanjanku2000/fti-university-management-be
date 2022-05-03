package al.edu.fti.universitymanagement.base.core.dto;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BaseDto {
    private Long id;

    public BaseEntity toEntity(){
        return new BaseEntity(id,true);
    }

    public BaseEntity toEntity(BaseEntity base){
        return base;
    }

    public BaseDto toDto(BaseEntity baseEntity){
        return new BaseDto(baseEntity.getId());
    }
}
