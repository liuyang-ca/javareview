package ca.liu.spring.dao.springdataImpl;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ca.liu.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("springdata_jpa.xml")
@Transactional
public class UserRepositoryTest {

	@Inject
	private UserRepository userRepository;
	private User user = new User("DAO Test", "12345");
	
	@Test
	public void listTest() {
		List<User> list = userRepository.findAll();
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void countTest() {
		System.out.println(userRepository.count());
	}
	
	@Test
	public void findByUsernameOrIdTest() {
		System.out.println(userRepository.findByUsernameOrPassword("Jack Smith"));
	}
	
	@Test
	public void listWithPageableTest() {
		Page<User> list = userRepository.findAll(new PageRequest(0, 4, new Sort(Sort.Direction.ASC, "id")));
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void findByIdTest() {
		User user = this.userRepository.findOne(1);
		System.out.println(user);
	}
	
	@Test
	public void findByUsernameTest() {
		User user = this.userRepository.findByUsername("Jack Smith");
		System.out.println(user);
	}
	
	/**
	 * Note: this save == save or merge
	 */
	@Test
	@Rollback(false)
	public void saveTest() {
		user.setId(16);
		user.setUsername("Changed DAO Test");
		userRepository.save(user);
		listTest();
	}
	
	@Test
	@Rollback(false)
	public void deleteTest() {
		user.setId(17);
		userRepository.delete(user);
	}
}
