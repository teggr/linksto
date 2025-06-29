package page.linksto.app.web.save;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import page.linksto.app.users.UserRepository;
import page.linksto.app.users.saves.Saves;
import page.linksto.app.users.tags.TagRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@Controller
@RequestMapping("/save")
@RequiredArgsConstructor
public class SaveController {

  private final Saves saves;
  private final TagRepository tagRepository;
  private final UserRepository userRepository;

  @GetMapping
  public Object startSave(@AuthenticationPrincipal User user,
                          @RequestParam(name = "url", required = false) URL url, Model model) {

    page.linksto.app.users.User linksToUser = userRepository.findByEmailAddress(user.getUsername());

    model.addAttribute("saveUrl", fromMethodCall(on(SaveController.class).save(null, null, null)).build().toUriString());
    model.addAttribute("saveForm", new SaveForm(url.toString(), "", List.of(), ""));
    model.addAttribute("userTags", tagRepository.findAllByUser(AggregateReference.to(linksToUser.id())));
    return "saveView";
  }

  @PostMapping(params = "add-tag")
  public Object addTag(@AuthenticationPrincipal User user,
                       @ModelAttribute SaveForm saveForm, BindingResult bindingResult, Model model) {
    page.linksto.app.users.User linksToUser = userRepository.findByEmailAddress(user.getUsername());

    model.addAttribute("saveUrl", fromMethodCall(on(SaveController.class).save(null, null, null)).build().toUriString());
    List<String> tags = new ArrayList<>(saveForm.tags() != null ? saveForm.tags() : List.of());
    tags.addAll(Arrays.stream(saveForm.tagText().split(","))
      .map(String::trim)
      .filter(Predicate.not(String::isEmpty))
      .toList()
    );
    model.addAttribute("saveForm", new SaveForm(
      saveForm.url(),
      "",
      tags,
      saveForm.notes()
    ));
    model.addAttribute("userTags", tagRepository.findAllByUser(AggregateReference.to(linksToUser.id())));

    return "saveView";
  }

  @PostMapping
  public Object save(@AuthenticationPrincipal User user,
                     @ModelAttribute SaveForm saveForm, BindingResult bindingResult) {
    // TODO: need to create a form object to capture the input including tags + get autheticated prinicple
    saves.save(
      saveForm.url(),
      saveForm.tags() != null ? saveForm.tags() : List.of(),
      saveForm.notes(),
      user.getUsername()
    );
    return "redirect:/";
  }

}
