package model;

public class Cat {

	private CatPK pk;
	private int age;
	
	public CatPK getPk() {
		return pk;
	}
	public void setPk(CatPK pk) {
		this.pk = pk;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
