package ca.liu.spring.datasource;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ca.liu.dao.UserDAO;
import ca.liu.domain.User;
import ca.liu.util.ApplicationContextUtil;
import ca.liu.util.HibernateUtil;

public class DataSourceTest {	
	private ClassPathXmlApplicationContext ctx;
	
	/**
	 * add a shutdown hook for the above context... 
	 * main method exits, hook is called prior to the app shutting down...
	 */
	@Before
	public void before() {
		ctx = ApplicationContextUtil.instance.getApplicationContext("data_source.xml", this.getClass());
		ctx.registerShutdownHook();
	}
	
	@Test
	public void dataSourceTest() throws SQLException {
		DataSource source = (DataSource) ctx.getBean("myDataSource");
		source.getConnection();
	}
	
	@Test
	public void dataSourceWithPlacehodlerTest() throws SQLException {
		BasicDataSource source = ctx.getBean("placeholder", BasicDataSource.class);
		source.getConnection();
		System.out.println(source.getUrl());
	}
	
	/**
	 * Helper class that simplifies Hibernate data access code. 
	 * Automatically converts HibernateExceptions into DataAccessExceptions, following the org.springframework.dao exception hierarchy.
	 * 
	 * HibernateTemplate will manager transaction and open/close session.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void hiberanteTemplateTest() {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(ctx.getBean(SessionFactory.class));
		List<User> list = hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) {
                return session.createCriteria(User.class).setFetchMode("logs", FetchMode.JOIN).list();
            }
        });
		
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
	
	/**
	 * User TransactionTemplate to manual control transaction
	 */
	@Test
	public void transactionTemplateTest() {
		TransactionTemplate transactionTemplate = new TransactionTemplate(ctx.getBean(PlatformTransactionManager .class));
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				UserDAO userDAO = ctx.getBean("userDAOXML", UserDAO.class);
				List<User> list = userDAO.list();
				for(User u : list) {
					System.out.println(u);
				}
			}		
		});
	}
	
	/**
	 * Note that only openSession() work getCurrentSession() won't work! Also DAOs are not working at this point.
	 * Need to set exposeTransactionAwareSessionFactory to true to use original hibernate session,
	 * otherwise Spring will manage the transaction.
	 */
	@SuppressWarnings("unused")
	@Test
	public void hibernateTest() {
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		
		final User user1 = new User("Liu Yang", "12345");
		final User user2 = new User("Jack Smith", "12121");
		final User user3 = new User("Jeo Hansome", "13131");
		final User user4 = new User("Beauty Queen", "14141");
		//deal with transaction and exception
		
		HibernateUtil.INSTANCE.doTransaction(session, new HibernateCallback<List<User>>(){
			@SuppressWarnings("unchecked")
			public List<User> doInHibernate(Session session){
//				session.save(user1);
//				session.save(user2);
//				session.save(user3);
//				session.save(user4);
				
				List<User> list = session.createCriteria(User.class).list();
				for(User u : list) {
					System.out.println(u);
				}
				return list;
			}
		});
		if(session.isOpen()) {
			session.close();
			session = null;
		}
		sf.close();
		sf = null;
	}
}
