package hibernate02_OR_Mapping_Simulation;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;
import java.sql.*;

import model.Student;

public class Session {

	String tableName = "Student";
	Map<String, String> cfs = new HashMap<String, String>();
	String[] methodName;

	public Session() {
		cfs.put("id", "id");
		cfs.put("name", "name");
		cfs.put("age", "age");
		methodName = new String[cfs.size()];
	}

	public void save(Student s) throws Exception {
		String sql = createsql();
		System.out.println(sql);
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/hibernate", "root", "527007690");
		/*
		 * for(String str:methodName){ System.out.println(str); }
		 */
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < cfs.size(); i++) {
			// ����һ�� Method ��������ӳ�� Class ��������ʾ�����ӿڵ�ָ��������Ա������
			Method m = s.getClass().getMethod(methodName[i]);
			Class<?> r = m.getReturnType();
			// System.out.println(m.getName()+"|"+m.getReturnType());
			if (r.getName().equals("java.lang.String")) {
				// �Դ���ָ��������ָ����������ɴ� Method �����ʾ�ĵײ㷽��
				String returnValue = (String) m.invoke(s);
				ps.setString(i + 1, returnValue);
			}
			if (r.getName().equals("int")) {
				int returnValue = (int) m.invoke(s);
				ps.setInt(i + 1, returnValue);
			}
		}

		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	private String createsql() {
		String str1 = "";
		int index = 0;
		for (String s : cfs.keySet()) {
			String v = cfs.get(s);
			v = Character.toUpperCase(v.charAt(0)) + v.substring(1);
			methodName[index] = "get" + v;
			str1 += s + ",";
			index++;
		}
		str1 = str1.substring(0, str1.length() - 1);
		System.out.println(str1);
		String str2 = "";
		for (int i = 0; i < cfs.size(); i++) {
			str2 += "?,";
		}
		str2 = str2.substring(0, str2.length() - 1);
		System.out.println(str2);
		String sql = "insert into " + tableName + " (" + str1 + ") "
				+ " values (" + str2 + ")";
		return sql;
	}

}
