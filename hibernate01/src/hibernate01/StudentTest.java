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

		// 实例化配置文件
		Configuration configuration = new Configuration().configure();
		// 实例化服务登记
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		// 获取Session工厂
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		// 生成一个session
		Session session = sessionFactory.openSession();
		// 开启事务
		session.beginTransaction();

		session.save(s);
		// 提交事务
		session.getTransaction().commit();
		// 关闭session
		session.close();
		// 关闭session工厂
		sessionFactory.close();
	}

}
