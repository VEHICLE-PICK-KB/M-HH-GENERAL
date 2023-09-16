package app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository <Book, Long> {
	List<Book> findByAuthor(String author);
	List<Book> findByAuthorIgnoreCase(String author);
	List<Book> findByAuthorOrderByPriceAsc(String author);
    
}