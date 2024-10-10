package com.example.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.BookModel;
import com.example.demo.Service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/books")
public class BooksController {
  private final BookService bookService;

  public BooksController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public String getBooksPage(@RequestParam(name = "search", required = false) String search, Model model) {
    model.addAttribute("search", search);
    List<BookModel> books = bookService.getAllBookModelsByYear(search);
    model.addAttribute("books", books);

    return "books/books_page";
  }

  @GetMapping("/create")
  public String getCreateBookPage(Model model) {
    model.addAttribute("newBook", new BookModel());
    return "books/create-book";
  }

  @GetMapping("/edit/{title}")
  public String getEditBookPage(Model model, @PathVariable String title) {
    BookModel byTitle = bookService.findBookByTitle(title);
    model.addAttribute("bookToEdit", byTitle);
    return "books/edit-book";
  }

  @PostMapping("/addBook")
  public String addBook(@ModelAttribute BookModel book) {
    bookService.save(book);
    return "redirect:/books";
  }

  @PostMapping("/editBook")
  public String editBook(@ModelAttribute BookModel book) {
    bookService.edit(book);
    return "redirect:/books";
  }

  @GetMapping("/deleteBook/{title}")
  public String deleteBook(@PathVariable String title) {
    bookService.remove(title);
    return "redirect:/books";
  }
  
}
