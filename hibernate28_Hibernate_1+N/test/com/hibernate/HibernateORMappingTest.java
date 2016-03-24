package com.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
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

	//1+N
	//第一种方法:fetch=FetchType.LAZY
	@Test
	public void testQuery1() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		List<Topic> topics=session.createQuery("from Topic").list();
		
		for(Topic t : topics){
			System.out.println(t.getId()+"----"+t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	// 第二种方法:@BatchSize(size=5)
	@Test
	public void testQuery2() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		List<Topic> topics = session.createQuery("from Topic").list();
		
		for (Topic t : topics) {
			System.out.println(t.getId() + "----" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();

	}

	// 第三种方法:join fetch
	@Test
	public void testQuery3() {
		Session session = sf.openSession();
		session.beginTransaction();

		//List<Topic> topics=session.createCriteria(Topic.class).list();
		List<Topic> topics = session.createQuery("from Topic t left join fetch t.category c").list();

		for (Topic t : topics) {
			System.out.println(t.getId() + "----" + t.getTitle());
		}
		session.getTransaction().commit();
		session.close();

	}
	@AfterClass
	public static void afterClass() {
		sf.close();
	}

}
