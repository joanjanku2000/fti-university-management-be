package al.edu.fti.universitymanagement.core.base.entity;

import al.edu.fti.universitymanagement.core.base.converter.BaseConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;

    public BaseEntity(){
        active = true;
    }
}
