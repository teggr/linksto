package page.linksto.app.web.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import page.linksto.app.links.LinkRepository;
import page.linksto.app.users.UserRepository;
import page.linksto.app.users.saves.Saves;
import page.linksto.app.users.tags.TagRepository;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

  private final Saves saves;
  private final TagRepository tagRepository;
  private final UserRepository userRepository;
  private final LinkRepository linkRepository;

  @GetMapping
  public Object getHome(@AuthenticationPrincipal User user, Pageable pageable, Model model) {
    log.info("{}", user);
    if (user != null) {

      page.linksto.app.users.User linksToUser = userRepository.findByEmailAddress(user.getUsername());

      model.addAttribute("saves", saves.get(pageable));
      model.addAttribute("userTags", tagRepository.findAllByUser(AggregateReference.to(linksToUser.id())));
      model.addAttribute( "linkFetcher", (LinkFetcher) linkRepository::findById);

      return "userHomeView";
    } else {
      return "anonymousHomeView";
    }
  }

}
