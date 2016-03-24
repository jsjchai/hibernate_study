package hibernate02_OR_Mapping_Simulation;

import model.Student;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		Student s = new Student();
		s.setId(2);
		s.setName("zhang");
		s.setAge(21);
		
		Session ss=new Session();
		ss.save(s);
	}
}
