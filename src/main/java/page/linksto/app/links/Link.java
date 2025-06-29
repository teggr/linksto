package page.linksto.app.links;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("LINKS")
public record Link(@Id Long id, String href, String title, @CreatedDate Instant createdDate) {

}
