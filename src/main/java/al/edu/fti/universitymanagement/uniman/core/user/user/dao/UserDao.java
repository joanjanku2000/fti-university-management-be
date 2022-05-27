package al.edu.fti.universitymanagement.uniman.core.user.user.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.user.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends BaseDao<UserEntity,Long> {

    @Query("select u from UserEntity u where u.email = :email")
    Optional<UserEntity> findByEmail(String email);

    @Query("select case when count(u) > 0 then true else false end from UserEntity u where u.email = :email")
    Boolean userExists(@Param("email") String email);
}
