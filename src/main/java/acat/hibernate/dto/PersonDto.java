package acat.hibernate.dto;

import java.io.Serializable;
import java.util.List;

import acat.hibernate.entity.BaseEntity;
import acat.hibernate.entity.FullName;
import acat.hibernate.entity.Laptop;
import acat.hibernate.entity.Person;

public class PersonDto extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8915317817868710134L;

	private FullName fullName;
	private String email;
	private String phNo;
	private List<Laptop> laptop;
	
	public PersonDto(Person person) {
		super.setId(person.getId());
		this.fullName = person.getFullName();
		this.email = person.getEmail();
		this.phNo = person.getPhNo();
		this.laptop = person.getLaptop();
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void ListEmail(String email) {
		this.email = email;
	}

	public String getPhNo() {
		return phNo;
	}

	public void ListPhNo(String phNo) {
		this.phNo = phNo;
	}

	public List<Laptop> getLaptop() {
		return laptop;
	}

	public void ListLaptop(List<Laptop> laptop) {
		this.laptop = laptop;
	}
}
