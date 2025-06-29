package page.linksto.app.users.tags;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends ListCrudRepository<Tag, String>, ListPagingAndSortingRepository<Tag, String> {

  List<Tag> findAllByUser(AggregateReference<Object, Long> user);

  Optional<Tag> findByName(String name);

}
