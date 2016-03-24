package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DogPK implements Serializable{

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
		return name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DogPK) {
			DogPK cp = (DogPK) obj;
			if (this.id == cp.id && this.name == cp.name)
				return true;
		}
		return false;
	}
}
