package hibernate04_id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.Cat;
import model.CatPK;

public class CatTest {

	public static void main(String[] args) {
		Cat c=new Cat();
		CatPK cp= new CatPK();
		cp.setId(1);
		cp.setName("cat1");
		c.setPk(cp);
		c.setAge(1);
		
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(c);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
