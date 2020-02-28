

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/new")
public class Library extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Book b;
	private Author a;
	private BookAuthor ba;
	private DbDao dao;
	
	private String url;
	private String user;
	private String pass;
	
	public void init() {
        url = getServletContext().getInitParameter("url");
        user = getServletContext().getInitParameter("user");
        pass = getServletContext().getInitParameter("pass");
 
        dao = new DbDao(url, user, pass);
 
    }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getServletPath();
		
		try {
            switch (action) {
            case "/insert":
                insertBook(req, res);
                break;
            case "/search":
            	searchB(req, res);
            	break;
            case "/delete":
                deleteBook(req, res);
                break;
            default:
            	listLib(req, res);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	
	private void listLib(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, ServletException {
		 List<BookAuthor> ba = dao.listAll();
		req.setAttribute("listLibrary", ba);
        RequestDispatcher disp = req.getRequestDispatcher("index.jsp");
        disp.forward(req, res);
    }
		
	private void insertBook(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException {
        String title = req.getParameter("title");
        Long isbn = Long.parseLong(req.getParameter("isbn"));
        String author = req.getParameter("author");
        
        b = new Book(title, isbn);
        a = new Author(author);
        dao.insert(b, a);
        res.sendRedirect("index.jsp");
    }	
	
	private void searchB(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException {
		try {
		String title = req.getParameter("title");
		
		b = new Book(title);
		req.setAttribute("list", b);
        RequestDispatcher view = req.getRequestDispatcher("/result.jsp");
        
			view.forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	private void searchA(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException {
		try {
		String author = req.getParameter("author");
		
		a = new Author(author);
		req.setAttribute("list", a);
        RequestDispatcher view = req.getRequestDispatcher("/result.jsp");
        
			view.forward(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteBook(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
 
        ba = new BookAuthor(id);
        dao.delBA(ba);
        res.sendRedirect("/delete");
 
    }

}
