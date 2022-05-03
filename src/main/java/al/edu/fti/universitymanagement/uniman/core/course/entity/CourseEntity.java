package al.edu.fti.universitymanagement.uniman.core.course.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data @AllArgsConstructor
@Entity @Table(name = "course")
@Where(clause = "active=true")
@SQLDelete(sql = "update course c set c.active = false where c.id = ?")
public class CourseEntity extends BaseEntity {
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    private LocationEntity location;

    public CourseEntity(){
        super.setActive(true);
    }
}
