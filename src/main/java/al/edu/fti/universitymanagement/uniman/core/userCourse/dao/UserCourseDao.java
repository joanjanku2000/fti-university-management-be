package al.edu.fti.universitymanagement.uniman.core.userCourse.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;

import java.util.List;


public interface UserCourseDao extends BaseDao<UserCourseEntity,Long> {

    List<UserCourseEntity> findAllByUserEntityIdAndCourseEntityId(Long userEntityId , Long courseEntityId);
    List<UserCourseEntity> findAllByUserEntityId(Long userEntityId);
    List<UserCourseEntity> findAllByCourseEntityId(Long courseId);
}
