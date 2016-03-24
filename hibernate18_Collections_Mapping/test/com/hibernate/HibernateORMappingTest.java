package com.hibernate;

import java.util.Map;

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
	public static void beforeClass(){
		Configuration cf=new Configuration().configure();
		ServiceRegistry sr=new StandardServiceRegistryBuilder()
				.applySettings(cf.getProperties()).build();
		sf=cf.buildSessionFactory(sr);
	}
	
	@Test
	public void testDeleteGroup() {
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		
		Group g=(Group)session.load(Group.class, 2);
		for(Map.Entry<Integer, User> e : g.getUsers().entrySet()){
			System.out.println(e.getKey()+"  "+ e.getValue().getName());
		}
		/*
		for(User u : g.getUsers()){
			System.out.println(u.getName());
		}*/
		session.getTransaction().commit();
	}
	
	@AfterClass
	public static void afterClass(){
		sf.close();
	}
	
}
