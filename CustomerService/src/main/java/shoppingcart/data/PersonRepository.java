package shoppingcart.data;

import org.springframework.stereotype.Repository;
import shoppingcart.domain.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    List<Person> personList = new ArrayList<>();

    public Person savePerson(Person p1){
        //Person p = new Person(p1.getName(), p1.getAge());
        personList.add(p1);
        return p1;
    }
    public Person getPerson(String name){
        Person p = null;
        for(Person person : personList){
            if(person.getName() == name){
                p= person;
            }
        }
        return p;
    }

    public List<Person> getAllPerson(){
        return personList;
    }

    public String editPerson(Person p1){
        personList.stream().filter(e ->e.getName() == p1.getName())
                .forEach(em -> {
                    em.setName(p1.getName());
                });

//        for(Person person : personList){
//            if(person.getName() == p1.getName()){
//                person.setAge(p1.getAge());
//            }
//        }
        return "Succesfully Updated";
    }
}
