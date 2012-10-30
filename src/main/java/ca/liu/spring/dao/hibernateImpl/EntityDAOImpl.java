package ca.liu.spring.dao.hibernateImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ca.liu.dao.EntityDAO;
import ca.liu.util.StringUtil;

/**
 * =============================================
 *	Copyright (c) 2011 Canadian Broadcasting Corporation.
 *	All rights reserved.
 *
 *	ca.cbc.giscommon.db.dao.EntityHome.java
 *	=============================================
 *
 *	PURPOSE:	This class contains common methods used by all DAO class using Genetics. 
 *
 * =============================================
 * MODIFICATION	LOG	DATE		REASON / Change Request #
 * Liu Yang	Mar 25, 2008		Initial file creation
 *
 * =============================================
*/

public abstract class EntityDAOImpl<T> implements EntityDAO<T> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	/**
	 * Delete Instance
	 * 
	 * @param instance
	 */	
	public void delete(T persistentInstance)  {
		getCurrentSession().delete(persistentInstance);
	}

	/**
	 * Delete Instance by Id
	 * 
	 * @param id
	 */	
	public void deleteById(int id)  {
		T obj = findById(id);
		this.delete(obj);
    }

	public void evict(T object) {
        getCurrentSession().evict(object);
    }
	
	/**
	 * Find T By Field
	 * 
	 * @param field field to search based on
	 * @param value
	 * @return 
	 */	    
    @SuppressWarnings("unchecked")
	public T findByField(String field, String value)  {
    	Criteria c = getCurrentSession().createCriteria(getFullClassName()).add(Restrictions.eq(field,value));
    	return (T)c.uniqueResult();
    }
    
	/**
	 * Find T By Id
	 * @return merged instance
	 */	
	@SuppressWarnings("unchecked")
	public T findById(int id) {
		return (T)getCurrentSession().get(getFullClassName(), id);
	}
	
	protected String getAscOrDesc(boolean isAscending) {
    	if(isAscending) {
    		return "ASC";
    	}
    	
    	return "DESC ";
    }
	
	/**
	 * Return the Current Session
	 * 
	 * @return Current Session
	 */
    protected abstract Session getCurrentSession();
	
	/**
	 * This method returns the full class name of this class
	 * @return
	 */
    protected abstract String getFullClassName();
	
	/**
	 * This method returns all record.
	 * 
	 * @return A list of entities.
	 */
	@SuppressWarnings("unchecked")
	public List<T> list() {
		return (List<T>) getCurrentSession().createCriteria(getFullClassName()).list();
	}
	
	/**
	 * Find List a list of criteria.
	 * 
	 * @param criterionList
	 * @return 
	 */	        
	@SuppressWarnings("unchecked")
	public List<T> listByCriteria(Criteria criteria) {	
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> listByIds(String ids) {		
		return getCurrentSession().createCriteria(getFullClassName()).add(Restrictions.in("id", StringUtil.instance.parseIds(ids))).list();
	}

	public List<T> listByOrder(String order, boolean isAscending)  {
		return listByOrderAndLimit(order, isAscending, 0);
	}    
	
	/**
	 * Find T By Id 
	 * @param order - The order field
	 * @return an ordered list
	 */	
	@SuppressWarnings("unchecked")
	public List<T> listByOrderAndLimit(String order, boolean isAscending, int limit)  {
		if(limit <= 0) {
			return getCurrentSession().createCriteria(getFullClassName()).addOrder(isAscending == true ? Order.asc(order) : Order.desc(order)).list();
		}
		return getCurrentSession().createCriteria(getFullClassName()).addOrder(isAscending == true ? Order.asc(order) : Order.desc(order)).setMaxResults(limit).list();
	}   

	/**
	 * This method looks up an Entity using a given HQL statement.
	 * 
	 * @param HQL statement
	 * @return A list of entities
	 */
	@SuppressWarnings("unchecked")
	public List<T> listByQuery(String hql)  {
        Query query = getCurrentSession().createQuery(hql);
        return (List<T>) query.list();
	}
	
	/**
	 * Merge Detached Instance 
	 * 
	 * @param instance
	 * @return merged instance
	 */
	@SuppressWarnings("unchecked")
	public T merge(T detachedInstance) {
		T result = (T) getCurrentSession().merge(detachedInstance);
        return result;
	}
	
	/**
	 * Persist Entity
	 * 
	 * @param transientInstance
	 */
	public void persist(T transientInstance) {
		getCurrentSession().persist(transientInstance);
    }
	
	/**
	 * 
	 * @param transientInstance
	 */
	public void save(T transientInstance) {
		getCurrentSession().save(transientInstance);
    }


	/**
	 * 
	 * @param transientInstance
	 */
	public void saveOrUpdate(T transientInstance) {
		getCurrentSession().saveOrUpdate(transientInstance);
    }
    
    /**
	 * Update Entity Instance 
	 * 
	 * @param instance
	 */
	public void update(T persistentInstance) {
        getCurrentSession().update(persistentInstance);
    }
}
