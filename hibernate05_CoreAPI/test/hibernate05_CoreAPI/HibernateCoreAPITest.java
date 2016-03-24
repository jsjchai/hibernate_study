package hibernate05_CoreAPI;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.Student;

public class HibernateCoreAPITest {

	private static SessionFactory sf;
	
	@BeforeClass
	public static void beforeClass(){
		Configuration cf=new Configuration().configure();
		ServiceRegistry sr=new StandardServiceRegistryBuilder()
				.applySettings(cf.getProperties()).build();
		sf=cf.buildSessionFactory(sr);
	}
	
	//getCurrentSession()与openSession()
	@Test
	public void testStudentSave() {
		Student s=new Student();
		s.setName("s2");
		s.setAge(15);
		
		Session session=sf.getCurrentSession();
		//Session session=sf.openSession();
		session.beginTransaction();
		Session session2=sf.getCurrentSession();
		session.save(s);
		System.out.println(session==session2);
		session.getTransaction().commit();
		Session session3=sf.getCurrentSession();
		System.out.println(session==session3);
	}

	//三种状态
	@Test
	public void testSaveWith3State() {
		Student s=new Student();
		s.setName("s1");
		s.setAge(13);
		
		//Transient
		System.out.println("Transient:"+s.getId());
		
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		session.save(s);
		
		//Persistent
		System.out.println("Persistent:"+s.getId());
		
		session.getTransaction().commit();
		
		//Detached
		System.out.println("Detached:"+s.getId());
		
	}

	//Delete
	@Test
	public void testDelete() {
		Student s = new Student();
		s.setName("s2");
		s.setAge(13);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(s);
		session.getTransaction().commit();

		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		session2.delete(s);
		session2.getTransaction().commit();

	}
	

	@Test
	public void testDelete2() {
		Student s = new Student();
		s.setId(3);

		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.delete(s);
		session.getTransaction().commit();

	}
	
	//load
	@Test
	public void testLoad() {
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Student s=(Student) session.load(Student.class, 5);
		//System.out.println(s.getName());
		System.out.println(s.getClass());
		session.getTransaction().commit();
		//执行完第一个输出时正确
		System.out.println(s.getClass());
		//System.out.println(s.getName());
	}
	
	//get
	@Test
	public void testGet() {

		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Student s = (Student) session.get(Student.class, 5);
		//System.out.println(s.getName());
		System.out.println(s.getClass());
		session.getTransaction().commit();
		System.out.println(s.getClass());
		//System.out.println(s.getName());
	}
	
	//update
	@Test
	public void testUpdate() {

		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Student s = (Student) session.get(Student.class, 5);
		session.getTransaction().commit();
		
		s.setName("ss5");
		
		Session session1 = sf.getCurrentSession();
		session1.beginTransaction();
		session1.update(s);
		session1.getTransaction().commit();
	}
	
	@Test
	public void testUpdate1() {
		Student s=new Student();
		s.setName("ss1");
		//必须给定id
		s.setId(1);

		Session session = sf.getCurrentSession();
		session.beginTransaction();
		//会使其他值变成默认值
		session.update(s);
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdate2() {

		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Student s = (Student) session.get(Student.class, 5);
		s.setName("ww2");
		session.getTransaction().commit();
		
	}
	
	@Test
	public void testUpdate3() {
		
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Student s = (Student)session.get(Student.class, 1);
		s.setName("qq");
		session.getTransaction().commit();
		
		s.setName("z4");
		
		Session session1 = sf.getCurrentSession();
		session1.beginTransaction();
		session1.merge(s);
		session1.getTransaction().commit();
	}
	
	@Test
	public void testUpdate4() {

		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q=session.createQuery("update Student s set s.name='ww3' where s.id=1 ");
		q.executeUpdate();
		session.getTransaction().commit();
		
	}
	
	//saveOrUpdate
	@Test
	public void testSaveOrUpdate() {
		Student s = new Student();
		s.setName("s6");
		s.setAge(19);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(s);
		session.getTransaction().commit();
		
		s.setName("ss6");

		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		session2.saveOrUpdate(s);
		session2.getTransaction().commit();

	}
	
	//clear
	@Test
	public void testClear() {
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Student s=(Student) session.load(Student.class, 5);
		System.out.println(s.getName());
		
		session.clear();
		
		Student s1=(Student) session.load(Student.class, 5);
		System.out.println(s1.getName());
		session.getTransaction().commit();
	}
	
	//flush
	@Test
	public void testFlush() {
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Student s=(Student) session.load(Student.class, 5);
		s.setName("ssss5");
		
		session.flush();
		
		s.setName("sss5");
		session.getTransaction().commit();
	}
	
	//SchemaExport
	@Test
	public void testSchemaExport(){
		new SchemaExport(new Configuration().configure()).create(true,true);;
	}
	
	@AfterClass
	public static void afterClass(){
		sf.close();
	}
	
//	public static void main(String[] args) {
//		beforeClass();
//	}
}
