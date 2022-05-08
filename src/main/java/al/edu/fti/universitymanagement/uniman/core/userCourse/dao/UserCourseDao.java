package al.edu.fti.universitymanagement.uniman.core.userCourse.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserCourseDao extends BaseDao<UserCourseEntity,Long> {

    List<UserCourseEntity> findAllByUserEntityIdAndCourseEntityId(Long userEntityId , Long courseEntityId);
    Page<UserCourseEntity> findAllByUserEntityId(Long userEntityId, Pageable pageable);
    List<UserCourseEntity> findAllByCourseEntityId(Long courseId);
}
