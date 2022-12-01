package al.edu.fti.universitymanagement.uniman.core.userCourse.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.userCourse.entity.UserCourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserCourseDao extends BaseDao<UserCourseEntity,Long> {

    /**
     * Finds a course by UserId and CourseId
     * @param userEntityId Long
     * @param courseEntityId Long
     * @return a List of UserCourseEntity
     */
    List<UserCourseEntity> findAllByUserEntityIdAndCourseEntityId(Long userEntityId , Long courseEntityId);

    /**
     * Finds all courses of a User
     * @param userEntityId Long
     * @param pageable Pageable for pagination
     * @return a Page of UserCourseEntity
     */
    Page<UserCourseEntity> findAllByUserEntityId(Long userEntityId, Pageable pageable);

    /**
     * Finds all courses by CourseId in order to get all participants of a course
     * @param courseId Long
     * @return a List of UserCourseEntity
     */
    List<UserCourseEntity> findAllByCourseEntityId(Long courseId);
}
