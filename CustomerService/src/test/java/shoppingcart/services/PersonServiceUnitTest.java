package shoppingcart.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import shoppingcart.data.PersonRepository;
import shoppingcart.domain.Person;
import shoppingcart.service.PersonService;
import  static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersonServiceUnitTest {
    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPerson(){
        Person mockPerson = new Person("Tigist" , 25);

        // mockito is framework for providing mock data
        when(personRepository.savePerson(any(Person.class))).thenReturn(mockPerson);
        Person p = personService.createPerson(null) ;
       // assertNotNull(p);
       // assertNotNull(p.getName());
        assertEquals("Tigst", mockPerson.getName());

    }
}
