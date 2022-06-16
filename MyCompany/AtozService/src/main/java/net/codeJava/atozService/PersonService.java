package net.codeJava.atozService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codeJava.atozEntity.Person;
import net.codeJava.atozRepository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public void savePerson(Person person) {
		personRepository.saveAndFlush(person);
	}
	
	public Person findById(Long id) {
		return personRepository.findById(id).get();
	}
	
	public List<Person> findAll() {
		return personRepository.findAll();
	}
}
