package serVC.service;

import serVC.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks(Integer amountRows, Integer skipRows, String sort);

    Book create(Book book);

    void remove(Integer id);

    void update(Book book);

    Optional<Book> getBook(Integer id);
}
