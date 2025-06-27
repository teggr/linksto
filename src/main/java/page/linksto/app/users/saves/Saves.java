package page.linksto.app.users.saves;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import page.linksto.app.links.Link;
import page.linksto.app.links.LinkRepository;
import page.linksto.app.users.User;
import page.linksto.app.users.UserRepository;
import page.linksto.app.users.tags.Tag;
import page.linksto.app.users.tags.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Saves {

  private final SaveRepository saveRepository;
  private final LinkRepository linkRepository;
  private final TagRepository tagRepository;
  private final UserRepository userRepository;

  @Transactional
  public Save save(String href, String title, List<String> tagNames, String emailAddress) {

    User user = userRepository.findByEmailAddress(emailAddress);
    AggregateReference<User, Long> userRef = AggregateReference.to(user.id());

    // does the link exist, check the link service
    // if yes, use the reference, otherwise create the link
    Link link = linkRepository.findByHref(href)
      .orElseGet(() -> linkRepository.save(new Link(null, href, title)));
    AggregateReference<Link, Long> linkRef = AggregateReference.to(link.id());

    // do we already have his saved? if so, we can ignore creating a new one
    // if not saved, then we can create a new save
    Save save = saveRepository.findByLinkAndUser(link.id(), user.id())
      .orElseGet(() -> saveRepository.save(new Save(null, linkRef, userRef)));

    // store a copy of the tags in the tags repository - no duplicates
    if (tagNames != null && !tagNames.isEmpty()) {
      tagNames.forEach(tagName -> {
        tagRepository.findById(tagName.toLowerCase())
          .orElseGet(() -> tagRepository.save(new Tag(tagName, userRef)));
      });
    }

    // return the save
    return saveRepository.save(save);
  }

}
