package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.BookDao;
import entity.Book;

public class Menu {
  
  private BookDao bookDao = new BookDao();
  private Scanner scanner = new Scanner(System.in);
  private List<String> options = Arrays.asList("Show Books", "Search for a Book", "Create Book", "Delete Book", "Update Book");
  
  public void start() {
    String selection = "";
    
    do {
      printMenu();
      selection = scanner.nextLine();
      
      try {
        if (selection.equals("1")) {
          displayBooks();
         } else if (selection.equals("2")) {
           displayBook();
         }
         else if (selection.equals("3")) {
           createBook();
         }
         else if (selection.equals("4")) {
           deleteBook();
         }
         else if (selection.equals("5")) {
           updateBook();
         }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      
      System.out.println("Press enter to continue...");
      scanner.nextLine();
      
    } while (!selection.equals("-1"));
  }
  
  private void printMenu() {
    System.out.println("Select your Choice:\n----------------------------------");
    for (int i = 0; i < options.size(); i++) {
      System.out.println(i +1 + ")" + options.get(i));
    }
  }
  
  private void displayBooks() throws SQLException {
    List<Book> books = bookDao.getBooks();
    for (Book book : books) {
      System.out.println(book.getBookId() + ": " + book.getBookName() + "\n     Written By: " + book.getAuthorLastName() + "\n          Published In: " + book.getPublishDate());
    }
  }
  
  private void displayBook() throws SQLException {
    System.out.println("Enter book id: ");
    int id = Integer.parseInt(scanner.nextLine());
    Book book = bookDao.getBookByID(id);
    System.out.println(book.getBookId() + ": " + book.getBookName() + "\n     Written By: " + book.getAuthorLastName() + "\n          Published In: " + book.getPublishDate());
  }
  
  private void createBook() throws SQLException {
    System.out.println("Enter Book Title:");
    String bookName = scanner.nextLine();
    System.out.println("Enter the Last Name of the Author:");
    String authorLastName = scanner.nextLine();
    System.out.println("Enter the Year the book was Published:");
    String publishDate = scanner.nextLine();
    bookDao.createNewBook(bookName, authorLastName, publishDate);
  }
  
  public void deleteBook() throws SQLException {
    System.out.println("Enter id to delete: ");
    int id = Integer.parseInt(scanner.nextLine());
    bookDao.deleteBookById(id);
    System.out.println("Book at id: " + id + " has been deleted.");
}
  
  public void updateBook() throws SQLException {
    System.out.println("Enter id to update: ");
    int id = Integer.parseInt(scanner.nextLine());
    System.out.println("Enter the Book name you would like to use: ");
    String bookName = scanner.next();
    System.out.println("Enter the Author name you would like to use: ");
    String authorLastName = scanner.next();
    System.out.println("Enter the Publishing Year you would like to use: ");
    String publishDate = scanner.next();
    bookDao.updateBookById(id, bookName, authorLastName, publishDate);
    System.out.println("Book at id: " + id + " updated!");
}

}
