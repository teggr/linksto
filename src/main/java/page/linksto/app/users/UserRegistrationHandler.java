package page.linksto.app.users;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationHandler {

  private final UserRepository userRepository;

  @EventListener
  public void onSuccess(AuthenticationSuccessEvent success) {

    String username = ((org.springframework.security.core.userdetails.User) success.getAuthentication().getPrincipal()).getUsername();

    if (!userRepository.existsByEmailAddress(username)) {
      userRepository.save(new User(null, username));
    }

  }

}