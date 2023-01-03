package serVC.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import serVC.domain.Book;
import serVC.service.BookService;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;


    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(value = "take", required = false) Integer amountRows,
            @RequestParam(value = "skip", required = false) Integer skipRows,
            @RequestParam(value = "sort", required = false) String sort) {
        return ResponseEntity.ok().body(bookService.getAllBooks(amountRows, skipRows, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        var book = bookService.getBook(id);
        return new ResponseEntity<>(
                book.orElse(new Book()),
                book.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(
                bookService.create(book),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody Book book) {
        bookService.update(book);
        return ResponseEntity.ok().build();
    }
}
