package serVC.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import serVC.domain.Author;

public interface AuthorsRepo extends JpaRepository<Author, Integer> {
}
