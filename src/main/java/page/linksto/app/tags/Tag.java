package page.linksto.app.tags;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TAGS")
public record Tag(@Id String name) {

  public Tag {
    name = name != null ? name.toLowerCase() : null;
  }

}
