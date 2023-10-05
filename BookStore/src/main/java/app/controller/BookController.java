package app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import app.model.Book;
import app.model.BookRepository;
import app.model.CategoryRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository drepository;
	
	// Show all students
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }


	@RequestMapping(value= {"/booklist"})
    public String bookList(Model model) {	
		model.addAttribute("books", repository.findAll());
    		
        return "booklist";
    }
	//REST
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    
	//REST
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }
	

	@RequestMapping(value = "/add")
	public String addBook(Model model){
	model.addAttribute("book"
	, new Book());
	model.addAttribute("categorys", drepository.findAll());
	return "addbook";
	}
	@RequestMapping(value = "/save"
	, method = RequestMethod.POST)
	public String save(Book book){
	repository.save(book);
	return "redirect:booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	repository.deleteById(bookId);
	return "redirect:/booklist";
	}
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model){
	model.addAttribute("book", repository.findById(bookId));
	model.addAttribute("categorys", drepository.findAll());
	return "editbook";
	}
}
//http://localhost:8080/booklist