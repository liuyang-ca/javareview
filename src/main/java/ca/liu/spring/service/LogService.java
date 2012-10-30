package ca.liu.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.liu.dao.LogDAO;
import ca.liu.domain.Log;
import ca.liu.ws.soap.LogServiceEndPoint;

@Service("logService")
@Transactional
public class LogService implements LogServiceEndPoint {
	@Autowired private LogDAO logDAO;
	
	public List<Log> list() {
		return logDAO.list();
	}

	public Log get(int id) {
		return logDAO.findById(id);
	}

	public void save(Log log) {
		logDAO.save(log);
	}

	public void delete(Log log) {
		logDAO.delete(log);
	}

	public void update(Log log) {
		logDAO.update(log);
	}
}
