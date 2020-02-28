
public class Author {
	private int auth_id;
	private String author;
	
	public Author() {}
	
	public Author(int auth_id) {
		this.auth_id = auth_id;
	}
	
	public Author(int auth_id, String author) {
		this.auth_id = auth_id;
		this.author = author;
	}

	public Author(String author) {
		this.author = author;
	}
	
	public int getAuth_id() {
		return auth_id;
	}

	public String getAuthor() {
		return author;
	}
	
	public String toString() {
		return author;
	}
}
