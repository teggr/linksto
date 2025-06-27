package page.linksto.app.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User, Long>, ListPagingAndSortingRepository<User, Long> {

  boolean existsByEmailAddress(String emailAddress);

  User findByEmailAddress(String emailAddress);
}
