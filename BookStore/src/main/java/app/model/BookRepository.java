package app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends CrudRepository <Book, Long> {
	List<Book> findByAuthor(@Param("author")String author);
	List<Book> findByAuthorIgnoreCase(String author);
	List<Book> findByAuthorOrderByPriceAsc(String author);
    
}