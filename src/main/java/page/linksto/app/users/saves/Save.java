package page.linksto.app.users.saves;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;
import page.linksto.app.links.Link;
import page.linksto.app.users.User;

import java.time.Instant;
import java.util.List;

@Table("USER_SAVES")
public record Save(
  @Id Long id,
  AggregateReference<Link, Long> link,
  AggregateReference<User, Long> user,
  List<String> tags,
  String notes,
  @CreatedDate Instant createdDate) {
}
