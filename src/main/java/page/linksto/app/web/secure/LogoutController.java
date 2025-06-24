package page.linksto.app.web.secure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

  @GetMapping("/logout")
  public Object getLogin(
    Model model) {

    model.addAttribute("logoutUrl", "/logout");
    return "logoutView";

  }

}
