package app.model;




import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String title;
	private String author;
	private String pubyear;
	private String isbn;
	private String price;
	
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name = "categoryid")
	    private Category category;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPubyear() {
		return pubyear;
	}
	public void setPubyear(String year) {
		this.pubyear = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
public Book(String title, String author, String year, String isbn, String price, Category category) {
	super();
	this.title = title;
	this.author = author;
	this.pubyear = year;
	this.isbn = isbn;
	this.price = price;
	this.category = category;
}
public Book() {}

@Override
public String toString() {
	if (this.category != null) 
	return "Book [id=" + id + ", title=" + title + ", author=" + author + ", year=" + pubyear + ", isbn=" + isbn
			+ ", price=" + price + " department =" + this.getCategory() + "]";
	else 
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", year=" + pubyear + ", isbn=" + isbn
				+ ", price=" + price + "]";
}}







