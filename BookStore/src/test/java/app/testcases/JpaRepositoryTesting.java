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
import app.BookstoreApplication;
import app.model.*;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryTesting {
	
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testSaveAndFindBook() {
        Book book = new Book("Test Title", "Test Author", null, null, null, null);
        bookRepository.save(book);

        Book foundBook = bookRepository.findById(book.getId()).orElse(null);
        assertNotNull(foundBook);
        assertEquals("Test Title", foundBook.getTitle());
        assertEquals("Test Author", foundBook.getAuthor());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("Test Title", "Test Author", null, null, null, null);
        bookRepository.save(book);

        bookRepository.deleteById(book.getId());

        Book deletedBook = bookRepository.findById(book.getId()).orElse(null);
        assertNull(deletedBook);
    }
    @Test
    public void testSaveAndFindCategory() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        Category foundCategory = categoryRepository.findById(category.getCategoryid()).orElse(null);
        assertNotNull(foundCategory);
        assertEquals("Test Category", foundCategory.getName());
    }
}



