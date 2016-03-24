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

private static SessionFactory sf;
	
	@BeforeClass
	public static void beforeClass(){
		new SchemaExport(new Configuration().configure()).create(true,true);
		Configuration cf=new Configuration().configure();
		ServiceRegistry sr=new StandardServiceRegistryBuilder()
				.applySettings(cf.getProperties()).build();
		sf=cf.buildSessionFactory(sr);
	}
	
	@Test
	public void testSave() {
		Student s=new Student();
		s.setAge(21);
		s.setMajor("计算机");
		s.setSname("李勇");
		Course c=new Course();
		c.setCredit(4);
		c.setName("数据库");
		Score sc=new Score();
		sc.setCourse(c);
		sc.setGrade(85);
		sc.setStudent(s);
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		
		session.save(s);
		session.save(c);
		session.save(sc);
		
		session.getTransaction().commit();
	}	
	@AfterClass
	public static void afterClass(){
		sf.close();
	}
	
}
