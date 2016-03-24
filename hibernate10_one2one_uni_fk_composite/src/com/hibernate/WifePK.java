package com.hibernate;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WifePK implements Serializable {

	private int id;
	private String name;
	
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
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof WifePK){
			WifePK wf=(WifePK)obj;
			if(this.id==wf.id&&this.name==wf.name)
				return true;
		}
		return false;
	}
}
