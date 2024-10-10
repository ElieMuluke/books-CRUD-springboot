package com.example.demo.Service;
import org.springframework.stereotype.Service;
import com.example.demo.Model.BookModel;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
  private static List<BookModel> books;

  static{
    books = new ArrayList<>();
    books.add(new BookModel("1", "The Lord of the Rings", "J.R.R. Tolkien", "1954"));
    books.add(new BookModel("2", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "1997"));
  }

  public List<BookModel> getAllBookModelsByYear(String keywordString){
    if(keywordString != null){
      return books.stream()
      .filter(book->book.getYear().equals(keywordString))
      .toList();
    }
    
    return books;
  }

  public void save(BookModel book){
    books.add(book);
  }

  public BookModel findBookByTitle(String title){
    BookModel bookModel = books.stream()
    .filter(it-> it.getTitle().equals(title))
    .findFirst()
    .orElseThrow(IllegalArgumentException::new);
    books.remove(bookModel);
    return bookModel;
  }

  public void edit(BookModel book){
    save(book);
  }

  public void remove(String title){
   findBookByTitle(title);
  }
}
