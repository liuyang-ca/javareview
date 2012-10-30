package ca.liu.spring.dao.springdataImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.liu.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username = ?")
	public User findByUsername(String username);
	
	@Query("select u from User u where u.username = :value or u.password= :value")
	public User findByUsernameOrPassword(@Param("value")String value);
}
