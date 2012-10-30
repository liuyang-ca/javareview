package ca.liu.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.liu.dao.LogDAO;
import ca.liu.dao.UserDAO;
import ca.liu.domain.Log;
import ca.liu.domain.User;
import ca.liu.util.StringUtil;

@Service("userService")
public class UserService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LogDAO logDAO;
	private Properties props;
	private List<?> list;
	private Set<?> set;
	private Map<?, ?> map;
	private String name;
	
	@Transactional
	public List<User> listUsers() {
		return userDAO.list();
	}
	
	@Transactional
	public User findById(int id) {
		return userDAO.findById(id);
	}
	
	@Transactional
	public void update(User user) {
		userDAO.update(user);
	}
	
	@Transactional
	public void delete(int id) {
		userDAO.delete(userDAO.findById(id));
	}
	
	@Transactional//(noRollbackFor = {HibernateException.class})
	public void resetUsersPassword(String ids, String password) {		
		for(int id : StringUtil.instance.parseIds(ids)) {
			resetUserPassord(id, password);
		}
	}
	
	@Transactional//(propagation = Propagation.REQUIRES_NEW)
	public void resetUserPassord(int userId, String password) {
		User user = userDAO.findById(userId);
		if(user != null) {
			user.setPassword(password);
			userDAO.update(user);
		}
	}

	@Transactional
	public void printUsers() {
		List<User> users = userDAO.list();
		for(User u : users) {
			System.out.println(u);
		}
	}
	
	@Transactional
	public void saveUser(User user) {
		userDAO.save(user);
		String logMsg = user.toString() + " is saved at " + new Date();
		logDAO.save(new Log(user, logMsg));
		logger.debug(logMsg);
	}
	
	@Transactional
	public int deleteLogs(int userId) {
		return logDAO.deleteByUserId(userDAO.findById(userId));
	}
	
	@Transactional
	public void addLog(int userId, String log) {
		logDAO.save(new Log(userDAO.findById(userId), log));
	}
	
	@PreDestroy
	public void destory() {
		logger.info("UserService Destoried!");
	}

	public List<?> getList() {
		return list;
	}

	public LogDAO getLogDAO() {
		return logDAO;
	}
	
	public Map<?, ?> getMap() {
		return map;
	}
	
	public String getName() {
		return name;
	}

	public Properties getProps() {
		return props;
	}

	public Set<?> getSet() {
		return set;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	@PostConstruct
	public void init() {
		logger.info("UserService Started!");
	}

	public void printUser(User user) {
		userDAO.print(user);
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	public void setMap(Map<?, ?> map) {
		this.map = map;
	}

	@Value("Injected value via annotation")
	public void setName(String name) {
		this.name = name;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public void setSet(Set<?> set) {
		this.set = set;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
