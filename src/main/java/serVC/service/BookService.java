package serVC.service;

import org.springframework.transaction.annotation.Transactional;
import serVC.domain.Book;
import serVC.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks(Integer amountRows, Integer skipRows, String sort);

    void remove(Integer id);

    Optional<Book> getBook(Integer id);

    Book updateOrSave(BookDto bookDto);
}
