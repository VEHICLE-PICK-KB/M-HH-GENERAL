package app.testcases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import app.model.*;
import app.DeparturesApplication;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DeparturesApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryTesting {
	
    @Autowired
    private FlightRepository flightRepository;
    
    @Autowired
    private TypeRepository typeRepository;

    @Test
    public void testSaveAndFindFlight() {
        Flight flight = new Flight("Test Number", "Test Company", 1, null, null, null);
        flightRepository.save(flight);

        Flight foundFlight = flightRepository.findById(flight.getId()).orElse(null);
        assertNotNull(foundFlight);
        assertEquals("Test Number", foundFlight.getNumber());
        assertEquals("Test Company", foundFlight.getCompany());
    }

    @Test
    public void testDeleteFlight() {
        Flight flight = new Flight("Test Number", "Test Company", 0, null, null, null);
        flightRepository.save(flight);

        flightRepository.deleteById(flight.getId());

        Flight deletedFlight = flightRepository.findById(flight.getId()).orElse(null);
        assertNull(deletedFlight);
    }
    @Test
    public void testSaveAndFindType() {
        Type type = new Type("Test Type");
        typeRepository.save(type);

        Type foundtype = typeRepository.findById(type.getTypeid()).orElse(null);
        assertNotNull(foundtype);
        assertEquals("Test Type", foundtype.getName());
    }
}



