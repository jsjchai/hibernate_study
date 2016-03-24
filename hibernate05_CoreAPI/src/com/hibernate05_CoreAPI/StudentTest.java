package com.hibernate05_CoreAPI;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		Student s=new Student();
		s.setName("s1");
		s.setAge(13);
		
		Configuration cf=new Configuration().configure();
		ServiceRegistry sr=new StandardServiceRegistryBuilder()
				.applySettings(cf.getProperties()).build();
		SessionFactory sf=cf.buildSessionFactory(sr);
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		
		session.save(s);
		
		session.getTransaction().commit();
		
		sf.close();
	}
}
