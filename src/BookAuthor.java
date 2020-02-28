
public class BookAuthor {
	private int id;
	Book b;
	Author a;
		
	public BookAuthor(){}
	
	public BookAuthor(int id) {
		this.id = id;
	} 
	
	public BookAuthor(int id, Book b, Author a) {
		this.id = id;
		this.b = b;
		this.a = a;
	} 
	
	public int getId() {
		return id;
	}
	
	public Book getB() {
		return b;
	}

	public Author getA() {
		return a;
	}
	
	public String cucak(int id, String tit, Long isbn, String auth) {
		return id + b.getTitle() + b.getIsbn() + a.getAuthor();
	}
}
