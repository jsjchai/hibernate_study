package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateORMappingTest {

private static SessionFactory sessionFactory;
	
	@BeforeClass
	public static void beforeClass(){
		new SchemaExport(new Configuration().configure()).create(true,true);
		Configuration cf=new Configuration().configure();
		ServiceRegistry sr=new StandardServiceRegistryBuilder()
				.applySettings(cf.getProperties()).build();
		sessionFactory=cf.buildSessionFactory(sr);
	}
	
	@Test
	public void testSave() {
		Student s = new Student();
		s.setName("s1");
		s.setScore(80);
		Teacher t = new Teacher();
		t.setName("t1");
		t.setTitle("ÖÐ¼¶");
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(s);
		session.save(t);
		session.getTransaction().commit();
		session.close();
	}	
	
	@Test
	public void testLoad() {
		testSave();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Student s = (Student)session.load(Student.class, 1);
		System.out.println(s.getScore());
		Person p = (Person)session.load(Person.class, 2);
		System.out.println(p.getName());
		session.getTransaction().commit();
		session.close();
	}
	
	@AfterClass
	public static void afterClass(){
		sessionFactory.close();
	}
	
}
