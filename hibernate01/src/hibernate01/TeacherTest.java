package hibernate01;

import model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class TeacherTest {

	public static void main(String[] args) {
		Teacher t=new Teacher();
		t.setId(1);
		t.setName("Wang");
		t.setTitle("����");

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

		session.save(t);
		// �ύ����
		session.getTransaction().commit();
		// �ر�session
		session.close();
		// �ر�session����
		sessionFactory.close();
	}

}
