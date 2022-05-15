package al.edu.fti.universitymanagement.uniman.core.comment.comment.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.comment.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentDao extends BaseDao<CommentEntity, Long> {

    @Query("select c from CommentEntity c where c.parent.id = :id")
    List<CommentEntity> findAllByParentId(Long id);
}
