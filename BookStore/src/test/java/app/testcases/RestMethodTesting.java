package app.testcases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.Mockito;
import app.controller.BookController;
import app.model.AppUserRepository;
import app.model.Book;
import app.model.BookRepository;
import app.model.CategoryRepository;
import org.springframework.security.test.context.support.WithMockUser;


import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(BookController.class)
public class RestMethodTesting {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private  CommandLineRunner bookDemo;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CategoryRepository categoryRepository;
    
    @MockBean
    private AppUserRepository appuserRepository;

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testBookListPage() throws Exception {
        
        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book("Book 1", null, null, null, null, null), new Book("Book 2", null, null, null, null, null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/booklist"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("booklist"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testBookListRest() throws Exception {
        
        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book("Book 1", null, null, null, null, null), new Book("Book 2", null, null, null, null, null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testFindBookRest() throws Exception {
        
        Long bookId = 1L;
        Book book = new Book("Test Book", null, null, null, null, null);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/{id}", bookId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Book"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testAddBookPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("addbook"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testSaveBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/save"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) 
            .andExpect(MockMvcResultMatchers.redirectedUrl("/booklist"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testDeleteBook() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/delete/{id}", bookId))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) 
            .andExpect(MockMvcResultMatchers.redirectedUrl("/booklist"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    public void testEditBookPage() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/edit/{id}", bookId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("editbook"));
    }

    
}