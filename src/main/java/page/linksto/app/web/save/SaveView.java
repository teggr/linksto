package page.linksto.app.web.save;

import dev.rebelcraft.j2html.spring.webmvc.J2HtmlView;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import page.linksto.app.web.site.LinksToNavigation;
import page.linksto.app.web.site.LinksToSiteLayout;

import java.net.URL;
import java.util.Map;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.container_fluid;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.py_4;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;

@Component
public class SaveView extends J2HtmlView {

  @Override
  protected DomContent renderMergedOutputModelDomContent(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String saveUrl = (String) model.get("saveUrl");
    URL url = (URL) model.get("url");

    return LinksToSiteLayout.add("LinksTo | Save", model,
      each(

        LinksToNavigation.linksToNavigation(model),

        div().withClasses(container_fluid, py_4).with(



          )

      )
    );

  }

}
