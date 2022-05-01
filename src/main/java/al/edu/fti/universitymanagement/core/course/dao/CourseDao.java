package al.edu.fti.universitymanagement.core.course.dao;

import al.edu.fti.universitymanagement.core.course.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<CourseEntity,Long> {
}
