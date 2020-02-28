import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DbDao {
	private String url = "jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false";
    private String user = "root";
    private String pass = "Root123#";
    private Connection conn = null;
    
    public DbDao() {}
	
	public DbDao(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	protected void connect() throws SQLException {
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException e) {
            	e.printStackTrace();
            }
    }

	protected void disconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
	
	public List<BookAuthor> listAll() throws SQLException{
		List<BookAuthor> list = new ArrayList<>();
		String sql = "SELECT id, title, isbn, author FROM books \r\n" + 
				"INNER JOIN book_author ON books.book_id = book_author.book_id \r\n" + 
				"INNER JOIN authors ON authors.auth_id = book_author.auth_id";
        
        connect();
        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery(sql);
        
        while (res.next()) {
            int id = res.getInt("book_id");
            String title = res.getString("title");
            String author = res.getString("author");
            Long isbn = res.getLong("isbn");
            
            Book b = new Book(title, isbn);
            Author a = new Author(author);
            BookAuthor ba = new BookAuthor(id, b, a);
            list.add(ba);
        }
         
        res.close();
        st.close();
        disconnect();
         
        return list;
	}
	
/*	public BookAuthor listLibrary() throws SQLException {
		connect();
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("SELECT id, title, isbn, author FROM books \r\n" + 
				"INNER JOIN book_author ON books.book_id = book_author.book_id \r\n" + 
				"INNER JOIN authors ON authors.auth_id = book_author.auth_id");
		
		int id = result.getInt("id");
        String title = result.getString("title");
        Long isbn = result.getLong("isbn");
        String author = result.getString("author");    		 
        BookAuthor b_a = new BookAuthor();
        
        while(result.next()) {
        	b_a.cucak(id, title, isbn, author);
		}
	        
	    result.close();
	    state.close();
	    disconnect();
	    return b_a;
	} */
	
	public void insert(Book book, Author auth) throws SQLException {
			connect();
			String sql1 = "INSERT INTO library.books (title, isbn) VALUES (?, ?)";
			String sql2 = "INSERT INTO library.authors (author) VALUES (?)";
			String sql3 = "INSERT INTO library.book_author(book_id, auth_id) VALUES(?, ?)";
			
			PreparedStatement state1 = conn.prepareStatement(sql1);
	        state1.setString(1, book.getTitle());
	        state1.setLong(2, book.getIsbn());
	        state1.executeUpdate();
	        
	        PreparedStatement state2 = conn.prepareStatement(sql2);
	        state2.setString(1, auth.getAuthor());
	        state2.executeUpdate();
	        
	        PreparedStatement state3 = conn.prepareStatement(sql3);
	        state3.setInt(1, book.getBook_id());
	        state3.setInt(2, auth.getAuth_id());
	        state3.executeUpdate();
	        
	        //state1.close();
	        //state2.close();
	        //state3.close();
	        disconnect();
	}
	
	public void searchBook(Book book) throws SQLException {
			connect();
			String sql = "SELECT title, author FROM books \r\n" + 
					"INNER JOIN book_author \r\n" + 
					"ON books.book_id = book_author.book_id AND books.title = book \r\n" + 
					"INNER JOIN authors ON authors.auth_id = book_author.auth_id";
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery(sql);
			
			while(result.next()) {
				System.out.println(result.getString("Title") + ", " + result.getString("author"));
			}
			
			result.close();
	        state.close();
	        disconnect();
	}
	
	public void searchAuthor(Author author) throws SQLException {
			connect();
			String sql = "SELECT title, author FROM authors \r\n" + 
					"INNER JOIN book_author \r\n" + 
					"ON  authors.auth_id = book_author.auth_id\r\n" + 
					" AND authors.author = author\r\n" + 
					"INNER JOIN books ON books.book_id = book_author.book_id";
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery(sql);
			
			while(result.next()) {
				System.out.println(result.getString("Title") + ", " + result.getString("author"));
			}
			
			result.close();
	        state.close();
	        disconnect();
	}
	
	public boolean delBA(BookAuthor ba) throws SQLException {
        	String sql = "DELETE books, book_author, authors FROM books\r\n" + 
        		"INNER JOIN book_author ON book_author.book_id = books.book_id \r\n" + 
        		"INNER JOIN authors ON authors.auth_id = book_author.auth_id\r\n" + 
        		"WHERE book_author.id = ?";
        	connect();
         
        	PreparedStatement state = conn.prepareStatement(sql);
        	state.setInt(1, ba.getId());
        	boolean rowDel = state.executeUpdate() > 0;
        
        	state.close();
        	disconnect(); 
        	return rowDel;
    }
	
}
