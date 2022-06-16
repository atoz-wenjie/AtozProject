package net.codeJava.atozRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codeJava.atozEntity.Person;

public interface PersonRepository extends JpaRepository<Person,Long>{

}
