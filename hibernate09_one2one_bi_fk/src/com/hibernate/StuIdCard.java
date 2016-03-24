package com.hibernate;

public class StuIdCard {
	
	private int id;
	private String name;
	private Student student;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Student getStudent() {
		return student;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
