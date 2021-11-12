package shoppingcart.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shoppingcart.domain.Person;
import shoppingcart.service.PersonService;
import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE) // Non of the controller is loaded
// we want to test controller
public class PersonServiceIntegrationTest {

    @Autowired
    PersonService personService;

    @Test
    public void testAddPerson(){
        // create Person object
        Person person = new Person("Abraham", 98);

        Person newPerson = personService.createPerson(person);

        assertNotNull(newPerson);
        assertEquals("Abraham", newPerson.getName());
    }
}
