package ca.liu.dao;

import java.util.List;

public interface EntityDAO<T> {
	public void save(T transientInstance);
	public void delete(T transientInstance);
	public void update(T transientInstance);
	public T findById(int id);
	public List<T> list();
}
