package springdata1.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springdata1.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	Iterable<User> findByEmail(String email);
	
	@Query("select u from User u where u.name = ?")
	User findByName(String name);
	
	@Query("select u from User u where u.name = ? or u.email=?")
	Iterable<User> findByNameOrEmail(@Param("name") String name, @Param("email") String email);
	
	@Modifying
	@Query("update User u set u.name = ? where u.email = ?")
	int updateByEmail(@Param("name") String name, @Param("email") String email);
	
	@Modifying
	@Transactional
	@Query("delete from User where email=?")
	void deleteByEmail(String email);
	

}
