package com.hibernate;

import java.util.Set;

public class Group_X {

	private int id;
	private String name;
	private Set<User_X> s;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User_X> getS() {
		return s;
	}
	public void setS(Set<User_X> s) {
		this.s = s;
	}
}
