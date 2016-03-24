package hibernate04_id;

import model.Dog;
import model.DogPK;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DogTest {

	public static void main(String[] args) {
		Dog d=new Dog();
		//DogPK dp=new DogPK();
		d.setId(1);
		d.setName("dog1");
		//d.setName("dog1");
		//d.setPk(dp);
		d.setAge(1);
		
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(d);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
