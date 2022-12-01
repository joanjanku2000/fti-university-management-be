package al.edu.fti.universitymanagement.uniman.core.comment.like.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.comment.like.entity.LikeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LikeDao extends BaseDao<LikeEntity, Long> {

    List<LikeEntity> findAllByCommentEntityId(Long id);

    @Query("select case WHEN count(l) > 0 THEN true ELSE false END " +
            "from LikeEntity l where l.userEntity.id = :userId and l.commentEntity.id = :commentId and l.active = true")
    Boolean likeExists(@Param("userId") Long userId, @Param("commentId") Long commentId);
}
