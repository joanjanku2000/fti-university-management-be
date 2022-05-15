package al.edu.fti.universitymanagement.uniman.core.user.friendship.entity;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity @Table(name = "friendship")
@Where(clause = "active = true")
@SQLDelete(sql = "update friendship set active=false where id = ?")
public class FriendshipEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sender_id",referencedColumnName = "id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id",referencedColumnName = "id")
    private UserEntity receiver;

    @Enumerated(EnumType.ORDINAL)
    private FriendshipStatus status;
}
