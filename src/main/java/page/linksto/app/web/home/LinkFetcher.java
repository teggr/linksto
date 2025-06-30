package page.linksto.app.web.home;

import page.linksto.app.links.Link;

import java.util.Optional;

@FunctionalInterface
public interface LinkFetcher {

  Optional<Link> get(Long id );

}
