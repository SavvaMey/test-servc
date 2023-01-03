package serVC.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serVC.domain.Author;
import serVC.domain.Book;
import serVC.dto.BookDto;
import serVC.exceptions.AuthorException;
import serVC.exceptions.SortException;
import serVC.repo.AuthorsRepo;
import serVC.repo.BooksRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

    private final BooksRepo booksRepo;
    private final AuthorsRepo authorsRepo;

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks(Integer amountRows, Integer skipRows, String sort) {
        Sort sortObj = createSort(sort);
        if (amountRows == null || skipRows == null) {
            return booksRepo.findAll(sortObj);
        } else {
            Pageable page = createPageable(amountRows, skipRows, sortObj);
            return booksRepo.findAll(page).getContent();
        }
    }

    private Pageable createPageable(Integer amountRows, Integer skipRows, Sort sortObj) {
        int numPage = skipRows / amountRows;
        return PageRequest.of(numPage, amountRows, sortObj);
    }

    private Sort createSort(String sort) {
        List<Sort.Order> orderList = new ArrayList<>();
        if (sort != null) {
            try {
                String[] sortDirectAndColumn = sort.split(",");
                for (String value : sortDirectAndColumn) {
                    String[] s = value.split(":");
                    Sort.Direction direction = Sort.Direction.ASC;
                    if (s[1].equals("desc")) {
                        direction = Sort.Direction.DESC;
                    }
                    orderList.add(new Sort.Order(direction, s[0]));
                }
            } catch (Exception e) {
                throw new SortException(e.getMessage());
            }

        }
        return Sort.by(orderList);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getBook(Integer id) {
        return booksRepo.findById(id);
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        var book = booksRepo.findById(id);
        book.ifPresent(booksRepo::delete);
    }

    @Transactional
    @Override
    public Book updateOrSave(BookDto bookDto) {
        Author author = authorsRepo.findById(bookDto.getAuthorId())
                .orElseThrow(()-> new AuthorException("No such author"));
            return booksRepo.save(Book.builder()
                    .id(bookDto.getId())
                    .author(author)
                    .isbn(bookDto.getIsbn())
                    .name(bookDto.getName())
                    .releaseDate(bookDto.getReleaseDate())
                    .build());
    }


}
