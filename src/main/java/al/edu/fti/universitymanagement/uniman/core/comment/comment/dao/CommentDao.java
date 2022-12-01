package al.edu.fti.universitymanagement.uniman.core.comment.comment.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.enums.CommentType;
import al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums.NotificationType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentDao extends BaseDao<CommentEntity, Long> {

    @Query("select c from CommentEntity c where c.parent.id = :id")
    List<CommentEntity> findAllByParentId(Long id);

    /**
     * Used for getting timeline updates
     * @param notificationType CommentType
     * @param userId Long
     * @return List of comment entities
     */
    List<CommentEntity> findAllByTypeAndUserEntityId(CommentType notificationType, Long userId);

    @Query("select comment from CommentEntity comment where comment.courseEntity is not null " +
            "and comment.courseEntity.id = :courseID and comment.parent is  null")
    List<CommentEntity> findCommentsOfCourse(@Param("courseID") Long courseID);
}
