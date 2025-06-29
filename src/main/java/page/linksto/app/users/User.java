package page.linksto.app.users;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("USERS")
public record User(@Id Long id, String emailAddress, @CreatedDate Instant createdDate) {

}
