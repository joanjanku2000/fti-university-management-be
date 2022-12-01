package al.edu.fti.universitymanagement.uniman.core.course.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor
@Entity
@Table(name = "location")
@Where(clause = "active=true")
@SQLDelete(sql = "update location c set c.active = false where c.id = ?")
public class LocationEntity extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "location" )
    private List<CourseEntity> courseEntity;

    public LocationEntity(String name) {
        this.name = name;
    }
}
