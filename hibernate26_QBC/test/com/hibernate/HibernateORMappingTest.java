package com.hibernate;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateORMappingTest {

	private static SessionFactory sf;

	@BeforeClass
	public static void beforeClass() {
		
		Configuration cf = new Configuration().configure();
		ServiceRegistry sr = new StandardServiceRegistryBuilder()
				.applySettings(cf.getProperties()).build();
		sf = cf.buildSessionFactory(sr);
	}

	@Test
	public void testSave() {
		Session session = sf.openSession();
		session.beginTransaction();

		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setName("c" + i);
			session.save(c);
		}

		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setId(1);
			Topic t = new Topic();
			t.setCategory(c);
			t.setTitle("t" + i);
			t.setCreateDate(new Date());
			session.save(t);

		}

		for (int i = 0; i < 10; i++) {

			Topic t = new Topic();
			t.setId(1);
			Msg m = new Msg();
			m.setCont("m" + i);
			m.setTopic(t);
			session.save(m);

		}

		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void testQBC() {
		Session session = sf.openSession();
		session.beginTransaction();
		// criterion ��׼/׼��/Լ��
		Criteria c = session
				.createCriteria(Topic.class)// from Topic
//				.add(Restrictions.gt("id", 2))// greater than = id > 2
//				.add(Restrictions.lt("id", 8))// little than = id < 8
//				.add(Restrictions.like("title", "t_"))
				.createCriteria("category")
				.add(Restrictions.between("id", 3, 5)) // category.id >= 3 and
														// category.id <=5
		;
		// DetachedCriterea
		for (Object o : c.list()) {
			Topic t = (Topic) o;
			System.out.println(t.getId() + "-" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();

	}
	
	@AfterClass
	public static void afterClass() {
		sf.close();
	}

}
