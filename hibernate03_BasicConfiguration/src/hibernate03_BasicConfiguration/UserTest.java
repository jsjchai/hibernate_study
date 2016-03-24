package hibernate03_BasicConfiguration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserTest {

	public static void main(String[] args) {
		/*
		User u = new User();
		u.setId(1);
		u.setName("wang");
		u.setPassword("123456");
		*/
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		//session.save(u);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
