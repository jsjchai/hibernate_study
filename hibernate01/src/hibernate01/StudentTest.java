package hibernate01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import model.Student;

public class StudentTest {

	public static void main(String[] args) {

		Student s = new Student();
		s.setId(1);
		s.setName("wang");
		s.setAge(20);

		// ʵ���������ļ�
		Configuration configuration = new Configuration().configure();
		// ʵ��������Ǽ�
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		// ��ȡSession����
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		// ����һ��session
		Session session = sessionFactory.openSession();
		// ��������
		session.beginTransaction();

		session.save(s);
		// �ύ����
		session.getTransaction().commit();
		// �ر�session
		session.close();
		// �ر�session����
		sessionFactory.close();
	}

}
