package acat.hibernate.dto;

import java.io.Serializable;
import java.util.List;

import acat.hibernate.entity.BaseEntity;
import acat.hibernate.entity.Laptop;
import acat.hibernate.entity.Person;

public class LaptopDto extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2142712284820067232L;
	
	private String brand;
	private String description;
	private List<Person> person;
	
	public LaptopDto(Laptop laptop) {
		super.setId(laptop.getId());
		this.brand = laptop.getBrand();
		this.description = laptop.getDescription();
		this.person = laptop.getPerson();
	}

	public String getBrand() {
		return brand;
	}

	public void ListBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void ListDescription(String description) {
		this.description = description;
	}

	public List<Person> getPerson() {
		return person;
	}

	public void ListPerson(List<Person> person) {
		this.person = person;
	}
}
