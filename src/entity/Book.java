package entity;

public class Book {
  private int bookId;
  private String bookName;
  private String authorLastName;
  private String publishDate;
  
  public Book(int bookId, String bookName, String authorLastName, String publishDate) {
    this.setBookId(bookId);
    this.setBookName(bookName);
    this.setAuthorLastName(authorLastName);
    this.setPublishDate(publishDate);
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getAuthorLastName() {
    return authorLastName;
  }

  public void setAuthorLastName(String authorLastName) {
    this.authorLastName = authorLastName;
  }

  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }
  
  

}
