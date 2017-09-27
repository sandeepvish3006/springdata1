package springdata1.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springdata1.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	//search method findByEmail in User.java
	Iterable<User> findByEmail(String email);
	
	Iterable<User> findByNameAndEmail(@Param("name") String name, @Param("email") String email);
	// search by name
	@Query("select u from User u where u.name = ?")
	User findByName(String name);
	
	//search by email or name 
	//return the list of user
	@Query("select u from User u where u.name = ? or u.email=?")
	Iterable<User> findByNameOrEmail(@Param("name") String name, @Param("email") String email);
	
	// update on the basis of email
	@Modifying
	@Transactional
	@Query("update User u set u.name = ? where u.email = ?")
	int updateByEmail(@Param("name") String name, @Param("email") String email);
	
	// delete by email
	@Modifying
	@Transactional
	@Query("delete from User where email=?")
	void deleteByEmail(String email);
	
	 @Query("select u from User u where u.name like %:s%")
	 List<User> findByAndSort(String name, Sort sort);

}
