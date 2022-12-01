package al.edu.fti.universitymanagement.base.core.dao;

import al.edu.fti.universitymanagement.base.core.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;

/**
 * Base DAO Interface which is to be extended by all
 * Repositories of the application. It is a generic implementation
 * and most of the CRUDs need not be overriden in its implementations
 * In order for generics to work and bean injection to work
 * it should be marked as NoRepositoryBean, otherwise app will fail to start
 * since BaseEntity is not an actual Entity. The repos implementing
 * this interface are the ones which will be injected where needed in runtime
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
    /**
     * This method will be used to achieve soft deletion
     * without having to repeat code
     * Must be marked with @Modifying otherwise
     * Not supported for DML operations Exception will emerge
     * @param id
     */
    @Modifying
    @Query("update #{#entityName} e set e.active=false where e.id=?1")
    void softDelete(ID id);


}
