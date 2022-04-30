package al.edu.fti.universitymanagement.core.base.dao;

import al.edu.fti.universitymanagement.core.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, Long> {
}
