package com.hibernate;



public class User_X {

	private int id;
	private String name;
	private Group_X group;
	
	public Group_X getGroup() {
		return group;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setGroup(Group_X group) {
		this.group = group;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
		
 }
