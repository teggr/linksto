package page.linksto.app.web.save;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import page.linksto.app.links.Link;

import java.net.URL;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@Controller
@RequestMapping("/save")
@RequiredArgsConstructor
public class SaveController {

  @GetMapping
  public Object startSave(@RequestParam(name = "url", required = false) URL url, Model model) {
    model.addAttribute("url", url);
    model.addAttribute("saveUrl", fromMethodCall(on(SaveController.class).save(null, null)));
    return "saveView";
  }

  @PostMapping
  public Object save(@ModelAttribute Link savedLink, BindingResult bindingResult) {
    return "redirect:/";
  }

}
