package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
//@IdClass(value=DogPK.class)
@IdClass(DogPK.class)
public class Dog {

	//private DogPK pk;
	private int id;
	private String name;
	private int age;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Id
	@Column(length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//@EmbeddedId
//	public DogPK getPk() {
//		return pk;
//	}
//	public void setPk(DogPK pk) {
//		this.pk = pk;
//	}
	@Basic
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
