package com.hibernate;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateCacheTest {

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
			Topic t=new Topic();
			t.setCategory(c);
			t.setCreateDate(new Date());
			t.setTitle("t"+i);
			session.save(c);
			session.save(t);
		}
		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void testCache2() {
		Session session = sf.openSession();
		session.beginTransaction();
		Category c = (Category)session.load(Category.class, 1);
		System.out.println(c.getName());
		
		
		session.getTransaction().commit();
		session.close();
		
		Session session2 = sf.openSession();
		session2.beginTransaction();
		Category c2 = (Category)session2.load(Category.class, 1);
		System.out.println(c2.getName());
		
		
		session2.getTransaction().commit();
		session2.close();
	}
	@Test
	public void testCache1() {
		Session session = sf.openSession();
		session.beginTransaction();
		Category c = (Category)session.load(Category.class, 1);
		System.out.println(c.getName());
		
		Category c2 = (Category)session.load(Category.class, 1);
		System.out.println(c2.getName());
		session.getTransaction().commit();
		session.close();
		
	}
	
	@AfterClass
	public static void afterClass() {
		sf.close();
	}

	
}
