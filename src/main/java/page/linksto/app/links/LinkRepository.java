package page.linksto.app.links;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface LinkRepository extends ListCrudRepository<Link, Long>, ListPagingAndSortingRepository<Link, Long> {

}
