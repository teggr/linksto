package page.linksto.app.users.saves;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface SaveRepository extends ListCrudRepository<Save, Long>, ListPagingAndSortingRepository<Save, Long> {
  Optional<Save> findByLinkAndUser(Long link, Long user);
}
