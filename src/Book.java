public class Book {
	private int book_id;
	private String title;
	private Long isbn;
	
	public Book() {}
	
	public Book(int book_id){
		this.book_id = book_id;
	}
	
	public Book(int book_id, String title, Long isbn) {
		this.title = title;
		this.isbn = isbn;
		this.book_id = book_id;
	}
	
	public Book(String title, Long isbn){
		this.title = title;
		this.isbn = isbn;
	}

	public Book(String title){
		this.title = title;
	}
	
	public int getBook_id() {
		return book_id;
	}

	public String getTitle() {
		return title;
	}

	public Long getIsbn() {
		return isbn;
	}
	
	public String toString() {
		return title + ", " + isbn;
	}
}
