package com.hibernate;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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
	public void testQueryList() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		List<Category> categorys=session.createQuery("from Category").list();
		
		for(Category c : categorys){
			System.out.println(c.getName());
		}
		
		List<Category> categorys1 = session.createQuery("from Category").list();

		for (Category c : categorys1) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();

	}
	
	@Test
	public void testQueryIterator() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		Iterator<Category> categories=session.createQuery("from Category").iterate();
		
		while(categories.hasNext()) {
			Category c = categories.next();
			System.out.println(c.getName());
		}
		Iterator<Category> categories1 = session.createQuery("from Category").iterate();

		while (categories1.hasNext()) {
			Category c = categories1.next();
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	@AfterClass
	public static void afterClass() {
		sf.close();
	}

}
