package app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlightRepository extends CrudRepository <Flight, Long> {
	List<Flight> findByNumber(@Param("number")String number);
	List<Flight> findByNumberIgnoreCase(String number);
	List<Flight> findByNumberOrderByIdAsc(String number);
    
}