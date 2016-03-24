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
	public void testSaveUser() {
		Group g=new Group();
		g.setName("g1");
		User u=new User();
		u.setName("u1");
		u.setGroup(g);
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		//session.save(g);
		session.save(u);
		session.getTransaction().commit();
	}
	
	@Test
	public void testSaveGroup() {
		User u1=new User();
		u1.setName("u1");
		User u2=new User();
		u2.setName("u2");
		Group g=new Group();
		g.setName("g1");
		
		g.getUsers().add(u1);
		g.getUsers().add(u2);
		u1.setGroup(g);
		u2.setGroup(g);
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		session.save(g);
		session.getTransaction().commit();
	}	
	
	@Test
	public void testGetUser() {
		testSaveGroup();
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		User u=(User)session.get(User.class, 1);
		//fetch=FetchType.LAZY 取不到Group的值      默认是fetch=FetchType.EAGER
		System.out.println(u.getName());
		session.getTransaction().commit();
	}
	
	@Test
	public void testGetGroup() {
		testSaveGroup();
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		Group g=(Group)session.get(Group.class, 1);
		//fetch=FetchType.LAZY 取不到Group的值      默认是fetch=FetchType.LAZY
		session.getTransaction().commit();
		for(User u :g.getUsers())
			System.out.println(u.getName());
	}
	
	@Test
	public void testLoadUser() {
		testSaveGroup();
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		User u=(User)session.load(User.class, 1);
		System.out.println(u.getName());
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdateUser() {
		testSaveGroup();
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		User u=(User)session.get(User.class, 1);
		session.getTransaction().commit();
		
		u.setName("user1");
		u.getGroup().setName("group1");
		
		Session session1=sf.getCurrentSession();
		session1.beginTransaction();
		session1.update(u);
		session1.getTransaction().commit();
		
	}
	
	@Test
	public void testDeleteUser() {
		testSaveGroup();
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		/*
		User u=(User)session.load(User.class, 1);
		u.setGroup(null);
		session.delete(u);
		*/
		session.createQuery("delete from User u where id = 1").executeUpdate();
		session.getTransaction().commit();
	}
	
	@Test
	public void testDeleteGroup() {
		testSaveGroup();
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		
		Group g=(Group)session.load(Group.class, 1);
		session.delete(g);
		
		session.getTransaction().commit();
	}
	
	@AfterClass
	public static void afterClass(){
		sf.close();
	}
	
}
