package net.bigpoint.gasstation.dataaccessobject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import net.bigpoint.gasstation.domainobject.UserDO;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface UserRepository extends CrudRepository<UserDO, Long> {

	@Query("select user from UserDO user where user.username = :username and user.password = :password and user.expired = 'false' ")
	UserDO findUser(@Param("username") String username, @Param("password") String password);

	@Query("select user from UserDO user where user.accessToken = :accessToken and user.expired = 'false' ")
	UserDO findUserAccessToken(@Param("accessToken") String accessToken);

}
