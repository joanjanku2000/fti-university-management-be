package al.edu.fti.universitymanagement.uniman.core.userCourse.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "course_student")
@Where(clause = "active = true")
@SQLDelete(sql = "update course_student c set c.active = false where c.id = ?")
public class UserCourseEntity extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "id")
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
