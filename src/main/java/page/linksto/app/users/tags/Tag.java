package page.linksto.app.users.tags;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;
import page.linksto.app.users.User;

@Table("USER_TAGS")
public record Tag(@Id String name, AggregateReference<User, Long> user) {

  public Tag {
    name = name != null ? name.toLowerCase() : null;
  }

}
