package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Book;

public class BookDao {
  
  private Connection connection;
  private final String GET_BOOKS = "SELECT * FROM books";
  private final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
  private final String CREATE_NEW_BOOK = "INSERT INTO books(name, author_lastname, publish_date) values (?,?,?)";
  private final String DELETE_BOOK_BY_ID = "DELETE FROM books WHERE id = ?";
  private final String UPDATE_BOOKS_BY_ID = "UPDATE books SET name = ?, author_lastname = ?, publish_date = ? WHERE id = ?";
  
  public BookDao() {
    connection = DBConnection.getConnection();
  }
  
  public List<Book> getBooks() throws SQLException {
    ResultSet rs = connection.prepareStatement(GET_BOOKS).executeQuery();
    List<Book> books = new ArrayList<Book>();
    
    while (rs.next()) {
      books.add(populateBook(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
    }
    
    return books;
  }
  
  public Book getBookByID(int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_BOOK_BY_ID);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    rs.next();
    return populateBook(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
  }
  
  public void createNewBook(String bookName, String authorLastName, String publishedYear) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(CREATE_NEW_BOOK);
    ps.setString(1, bookName);
    ps.setString(2, authorLastName);
    ps.setString(3, publishedYear);
    ps.executeUpdate();
  }
  
  private Book populateBook(int id, String bookName, String authorLastName, String publishDate) {
    return new Book(id, bookName, authorLastName, publishDate);
  }
  
  public void deleteBookById(int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(DELETE_BOOK_BY_ID);
    ps.setInt(1, id);
    ps.executeUpdate();
}
  
  public void updateBookById(int id, String bookName, String authorLastName, String publishDate) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(UPDATE_BOOKS_BY_ID);
    ps.setString(1, bookName);
    ps.setString(2, authorLastName);
    ps.setString(3, publishDate);
    ps.setInt(4, id);
    ps.executeUpdate();
}

}
