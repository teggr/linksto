package page.linksto.app.tags;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface TagRepository extends ListCrudRepository<Tag, String>, ListPagingAndSortingRepository<Tag, String> {
}
