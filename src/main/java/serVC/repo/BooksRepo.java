package serVC.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import serVC.domain.Book;

public interface BooksRepo extends JpaRepository<Book, Integer> {
}
