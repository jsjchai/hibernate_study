package com.hibernate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Husband {

	private int id;
	private String name;
	private Wife wife;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	@Column(length=20)
	public String getName() {
		return name;
	}

	@Embedded
	public Wife getWife() {
		return wife;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}
}
