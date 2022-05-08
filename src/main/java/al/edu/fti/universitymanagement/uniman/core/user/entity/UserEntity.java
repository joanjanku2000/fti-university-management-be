package al.edu.fti.universitymanagement.uniman.core.user.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.uniman.core.user.enums.Gender;
import al.edu.fti.universitymanagement.uniman.core.user.enums.Role;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "university_user" )
@Where(clause = "active=true")
@SQLDelete(sql = "update university_user u set u.active = false where id = ?1")
public class UserEntity extends BaseEntity {
    private String name;
    private String email; // not updatable
    private String lastName;
    private LocalDate birthday;
    private String idNumber;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    private String picture;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    private LocalDateTime registrationDate;
    private String password;

    public UserEntity(){
        super.setActive(true);
    }

}
