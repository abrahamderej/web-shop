package shoppingcart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shoppingcart.domain.Person;
import shoppingcart.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/customers/")
public class PersonController {

    @Autowired
    PersonService personService;

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason="no Such Person")
    @GetMapping("/api/hello")
    public String hello() throws RuntimeException{

        return "HEllo World";
    }

    @PostMapping("person")
    public Person createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }
    @PutMapping("person")
    public Person updatePerson(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @GetMapping("person")
    public List<Person> getPersons(){
        return personService.getAll();
    }

    @GetMapping("name/{h}")
    public String getHello(@PathVariable String h){
        return "Hello using Get" + h;
    }

//    @ ("/api/person/{name}/{id}")
//    public String getPerson(@PathVariable String name, @PathVariable int id){
//        return "";
//    }
}

