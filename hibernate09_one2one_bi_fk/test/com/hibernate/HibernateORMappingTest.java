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
		Configuration cf=new Configuration().configure();
		ServiceRegistry sr=new StandardServiceRegistryBuilder()
				.applySettings(cf.getProperties()).build();
		sf=cf.buildSessionFactory(sr);
	}
	
	@Test
	public void test() {
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		session.getTransaction().commit();
	}	
	@Test
	public void testSchemaExport(){
		new SchemaExport(new Configuration().configure()).create(true,true);;
	}
	
	@AfterClass
	public static void afterClass(){
		sf.close();
	}
	
}
