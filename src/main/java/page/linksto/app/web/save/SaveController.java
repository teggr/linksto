package page.linksto.app.web.save;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import page.linksto.app.links.Link;
import page.linksto.app.users.saves.Save;
import page.linksto.app.users.saves.Saves;

import java.net.URL;
import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@Controller
@RequestMapping("/save")
@RequiredArgsConstructor
public class SaveController {

  private final Saves saves;

  @GetMapping
  public Object startSave(@RequestParam(name = "url", required = false) URL url, Model model) {
    model.addAttribute("saveUrl", fromMethodCall(on(SaveController.class).save(null, null, null)).build().toUriString());
    model.addAttribute("saveForm", new SaveForm(url.toString()));
    return "saveView";
  }

  @PostMapping
  public Object save(@AuthenticationPrincipal User user,
    @ModelAttribute SaveForm saveForm, BindingResult bindingResult) {
    // TODO: need to create a form object to capture the input including tags + get autheticated prinicple
    saves.save(
      saveForm.url(),
      "Link Title",
      List.of(),
      user.getUsername()
    );
    return "redirect:/";
  }

}
