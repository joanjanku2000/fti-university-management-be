package al.edu.fti.universitymanagement.uniman.core.comment.like.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import al.edu.fti.universitymanagement.uniman.core.user.entity.UserEntity;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity @Table(name = "likes")
@Where(clause = "active=true")
public class LikeEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "comment_id",referencedColumnName = "id")
    private CommentEntity commentEntity;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity userEntity;
}
