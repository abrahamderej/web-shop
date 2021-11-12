package shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcart.data.PersonRepository;
import shoppingcart.domain.Person;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person createPerson(Person p){
         return personRepository.savePerson(p);

    }
    public String updatePerson(Person p){
        return personRepository.editPerson(p);
    }
    public List<Person> getAll(){
        return personRepository.getAllPerson();
    }
    public Person getPerson(String name){
        return personRepository.getPerson(name);
    }
}
