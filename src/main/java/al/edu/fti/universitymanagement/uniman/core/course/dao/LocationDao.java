package al.edu.fti.universitymanagement.uniman.core.course.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.course.entity.LocationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationDao extends BaseDao<LocationEntity,Long> {
    @Query("select l from LocationEntity l join fetch l.courseEntity where l.id = :id")
    LocationEntity findLocationWithLoadedCourses(@Param("id") Long id);
}
