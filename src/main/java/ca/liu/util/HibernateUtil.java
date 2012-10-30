package ca.liu.util;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * =============================================
 * Copyright (c) 2011 Canadian Broadcasting Corporation.
 * All rights reserved.
 * <p/>
 * ca.cbc.medialib.util.HibernateUtil.java
 * =============================================
 * <p/>
 * PURPOSE:	Manages Hibernate Session Factory and Session
 * <p/>
 * =============================================
 * MODIFICATION		LOG	DATE		REASON / Change Request #
 * Liu Yang			Jun 22, 2011	Initial file creation
 * =============================================
 */
public enum HibernateUtil {
	INSTANCE;
    private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class.getName());
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
		return this.getSessionFactory(null);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public <T> T doTransaction(Session session, HibernateCallback<T> callback) {
		T obj = null;
		try {
			session.beginTransaction();
			obj = callback.doInHibernate(session);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session != null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
				session = null;
			}
			logger.error("Hibernate exception while doing transaction.", e);
		} catch (SQLException e) {
			if(session != null && session.isOpen()) {
				session.getTransaction().rollback();
				session.close();
				session = null;
			}
			logger.error("SQL exception while doing transaction.", e);
		} 
		return obj;
	}

	@SuppressWarnings("deprecation")
	public SessionFactory getSessionFactory(String configFile) {
        if (sessionFactory == null) {
            logger.debug("No SessionFactory found, create session factory.");
            Configuration cfg = null;
            if (configFile == null) {
                cfg = new Configuration().configure();
            } else {
                cfg = new Configuration().configure(configFile);
            }
            this.setSessionFactory(cfg.buildSessionFactory());
        }
        return sessionFactory;
    }
	
    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
            logger.debug("*** Hibernate session is now closed.");
        }
    }
}
