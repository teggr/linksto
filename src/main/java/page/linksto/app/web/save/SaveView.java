package page.linksto.app.web.save;

import dev.rebelcraft.j2html.spring.webmvc.J2HtmlView;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import page.linksto.app.web.site.LinksToNavigation;
import page.linksto.app.web.site.LinksToSiteLayout;

import java.net.URL;
import java.util.Map;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static j2html.TagCreator.*;

@Component
public class SaveView extends J2HtmlView {

  @Override
  protected DomContent renderMergedOutputModelDomContent(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String saveUrl = (String) model.get("saveUrl");
    SaveForm saveForm = (SaveForm) model.get("saveForm");

    CsrfToken csrfToken = (CsrfToken) model.get("_csrf");

    return LinksToSiteLayout.add("LinksTo | Save", model,
      each(

        LinksToNavigation.linksToNavigation(model),

        div().withClasses(container_fluid, py_4).with(

          form().withAction(saveUrl).withMethod("post").withClasses(row, g_3).with(

            input().withType("hidden").withName(csrfToken.getParameterName()).withValue(csrfToken.getToken()),

            div().withClasses(col_12).with(
              label("URL").withClasses(form_label).withFor("url"),
              input()
                .withType("url")
                .withId("url")
                .withName("url")
                .withClasses(form_control)
                .withValue(saveForm.url())
            ),
            div().withClasses(col_12).with(
              button("Save").withClasses(btn, btn_primary).withType("submit")
            )
          )
        )
      )
    );

  }

}
