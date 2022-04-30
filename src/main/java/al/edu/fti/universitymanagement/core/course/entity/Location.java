package al.edu.fti.universitymanagement.core.course.entity;

import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor
@Entity @AllArgsConstructor
@Where(clause = "active=true")
public class Location extends BaseEntity {
    private String name;
}
