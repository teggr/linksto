package page.linksto.app.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
public record User(@Id Long id, String emailAddress) {

}
