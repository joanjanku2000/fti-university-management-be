package al.edu.fti.universitymanagement.uniman.core.userCourse.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.user.entity.UserEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "course_student")
@Where(clause = "active = true")
@SQLDelete(sql = "update uc from course_student set active=false where id = ?1")
public class UserCourseEntity extends BaseEntity {

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private CourseEntity courseEntity;

    private LocalDateTime joinedOn;

    private LocalDateTime leftOn;

    @PrePersist
    private void defineJoinedOn() {
        this.joinedOn = LocalDateTime.now();
        // TODO NOTIFICATIONS
    }

    @PreRemove
    private void defineLeftOn() {
        this.leftOn = LocalDateTime.now();
    }

    public UserCourseEntity(){
        super.setActive(true);
    }
}
