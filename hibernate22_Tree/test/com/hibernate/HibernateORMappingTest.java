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
		Org o=new Org();
		o.setName("总公司");
		Org o1=new Org();
		o1.setName("分公司1");
		Org o2=new Org();
		o2.setName("分公司2");
		Org o11=new Org();
		o11.setName("分公司1部门1");
		Org o12=new Org();
		o12.setName("分公司1部门2");
		
		o.getChildren().add(o1);
		o.getChildren().add(o2);
		o1.getChildren().add(o11);
		o1.getChildren().add(o12);
		o1.setParent(o);
		o2.setParent(o);
		o11.setParent(o1);
		o12.setParent(o1);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
	}	
	
	@Test
	public void testLoad() {
		testSave();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Org o=(Org)session.load(Org.class, 1);
		//使用fetch=FetchType.EAGER 会将整个树放在内存
		print(o,1);
		
		session.getTransaction().commit();
		session.close();
	}
	
	private void print(Org o,int level) {
		String str="";
		for(int i=0;i<level;i++){
			str+="      ";
		}
		System.out.println(str+o.getName());
		for(Org child : o.getChildren()){
			print(child,level+1);
		}
	}

	@AfterClass
	public static void afterClass(){
		sessionFactory.close();
	}
	public static void main(String[] args) {
		beforeClass();
	}
}
