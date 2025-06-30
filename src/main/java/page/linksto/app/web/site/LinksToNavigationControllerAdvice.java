package page.linksto.app.web.site;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import page.linksto.app.web.home.HomeController;
import page.linksto.app.web.save.SaveController;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@ControllerAdvice
public class LinksToNavigationControllerAdvice {

  @ModelAttribute
  public void populateModel(Model model) {

    String homeUrl = fromMethodCall(on(HomeController.class).getHome(null, null, null)).build().toUriString();
    model.addAttribute("homeUrl", homeUrl);

    String saveUrl = fromMethodCall(on(SaveController.class).startSave(null,null, null)).build().toUriString();
    model.addAttribute("saveUrl", saveUrl);

    String logoutUrl = "/logout";
    model.addAttribute("logoutUrl", logoutUrl);

    String loginUrl = "/login";
    model.addAttribute("loginUrl", loginUrl);

  }

}
