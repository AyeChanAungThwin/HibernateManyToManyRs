package acat.hibernate;

import java.util.List;

import acat.hibernate.dao.LaptopDao;
import acat.hibernate.dao.PersonDao;
import acat.hibernate.dependency.DependencyRegistry;
import acat.hibernate.dto.PersonDto;
import acat.hibernate.entity.FullName;
import acat.hibernate.entity.Gender;
import acat.hibernate.entity.Laptop;
import acat.hibernate.entity.Person;

public class App {
	
	public static void main( String[] args ) {
		
		DependencyRegistry dependency = DependencyRegistry.getInstance(); //Dependency
		
		Person person1 = dependency.createPerson(); //Dependency Injected
		
		Laptop laptop1 = dependency.createLaptop();
		laptop1.setBrand("Dell");
		laptop1.setDescription("It is dell laptop.");
		
		Laptop laptop2 = dependency.createLaptop();
		laptop2.setBrand("System76");
		laptop2.setDescription("This is awesome!");
		
		Laptop laptop3 = dependency.createLaptop();
		laptop3.setBrand("MacBook");
		laptop3.setDescription("This is shit!");
		
		LaptopDao laptopDao = dependency.createLaptopDao(); //Dependency Injected
		laptopDao.save(laptop1);
		laptopDao.save(laptop2);
		laptopDao.save(laptop3);
		
		person1.setFullName(new FullName("Dwayne", "Johnson"));
		person1.setGender(Gender.Male);
		person1.setEmail("dwaynejohnson@gmail.com");
		person1.setPhNo("+1549724440");
		Laptop ltp1 = laptopDao.findOne(1);
		ltp1.getPerson().add(person1);
		person1.getLaptop().add(ltp1);
		Laptop ltp2 = laptopDao.findOne(2);
		ltp2.getPerson().add(person1);
		person1.getLaptop().add(ltp2);
		Laptop ltp3 = laptopDao.findOne(3);
		ltp3.getPerson().add(person1);
		person1.getLaptop().add(ltp3);
		
		Person person2 = dependency.createPerson();
		person2.setFullName(new FullName("Taylor", "Swift"));
		person2.setGender(Gender.Female);
		person2.setEmail("tf@gmail.com");
		person2.setPhNo("+1384940300");
		ltp1.getPerson().add(person2);
		person2.getLaptop().add(ltp1);
		
		PersonDao personDao = dependency.createPersonDao(); //Dependency Injected
		personDao.save(person1);
		personDao.save(person2);
		
		List<Person> people = personDao.findAll();
		for (int i=0; i<people.size(); i++) {
			//String is immutable in java.
			StringBuilder sb=dependency.createStringBuilder(); //To reduce heap size
			sb.append("Person [id="+people.get(i).getId());
			sb.append(", name="+people.get(i).getFullName().getFirstName());
			sb.append(" "+people.get(i).getFullName().getLastName());
			sb.append(", email="+people.get(i).getEmail());
			sb.append(", phNo="+people.get(i).getPhNo());
			//sb.append(", "+people.get(i).getLaptop().get(i).getBrand());
			//sb.append(", "+people.get(i).getLaptop().get(i).getDescription()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
		
		Person prn = personDao.findOne(2);
		//System.out.println(prn.getLaptop().getBrand()); //This can result in LazyInitializationException
		PersonDto personDto = dependency.createPersonDto(prn); //To avoid LazyInitializationException, use DTO.
		System.out.println(personDto.getEmail());
		if (prn!=null) {
			personDao.delete(prn);
		}
    }
}
