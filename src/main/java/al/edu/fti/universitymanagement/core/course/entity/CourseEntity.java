package al.edu.fti.universitymanagement.core.course.entity;

import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
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
public class CourseEntity extends BaseEntity {
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location location;

    public CourseEntity(){
        super.setActive(true);
    }
}
