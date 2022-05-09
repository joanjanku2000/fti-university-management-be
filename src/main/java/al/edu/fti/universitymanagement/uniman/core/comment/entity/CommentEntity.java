package al.edu.fti.universitymanagement.uniman.core.comment.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.uniman.core.comment.enums.CommentType;
import al.edu.fti.universitymanagement.uniman.core.course.entity.CourseEntity;
import al.edu.fti.universitymanagement.uniman.core.user.entity.UserEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity @Table(name = "university_comment")
@SQLDelete(sql = "update university_comment c set c.active = false where c.id = ? ")
public class CommentEntity extends BaseEntity {

    private String commentString;
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity userEntity;

    @Enumerated(EnumType.ORDINAL)
    private CommentType type;

    @OneToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private CommentEntity parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private CourseEntity courseEntity;

    @OrderBy("createdAt")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id",referencedColumnName = "parent_id")
    private List<CommentEntity> children;

    @PrePersist
    private void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

}
