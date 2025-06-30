package page.linksto.app.web.home;

import dev.rebelcraft.j2html.spring.webmvc.J2HtmlView;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import page.linksto.app.links.Link;
import page.linksto.app.users.saves.Save;
import page.linksto.app.web.site.LinksToNavigation;
import page.linksto.app.web.site.LinksToSiteLayout;
import page.linksto.app.web.utils.components.CollapseableSidebarMenu;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static dev.rebelcraft.j2html.ext.ExtendedTagCreator.svg;
import static j2html.TagCreator.*;
import static j2html.TagCreator.h1;
import static page.linksto.app.web.utils.components.CollapseableSidebarMenu.COLLAPSEABLE_SIDEBAR_MENU_CSS;

@Component
public class UserHomeView extends J2HtmlView {

  @SuppressWarnings({"unchecked", "null"})
  @Override
  protected DomContent renderMergedOutputModelDomContent(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    // get from the model
    Page<Save> saves = (Page<Save>) model.get("saves");
    List<String> userTags = (List<String>) model.get("userTags");
    LinkFetcher linkFetcher = (LinkFetcher) model.get("linkFetcher");

    // nav
    String saveUrl = (String) model.get("saveUrl");

    // build the ui
    return LinksToSiteLayout.add("LinksTo | Home", model,

      Set.of(COLLAPSEABLE_SIDEBAR_MENU_CSS),

      each(

        LinksToNavigation.linksToNavigation(model),

        div().withClasses(container_fluid, py_4).with(

          div().withClasses(row, my_3).with(

            div().withClasses(col_1).with(

              HomeNavigation.homeSidebar()

            ),

            div().withClasses(col).with(

              each( saves.getContent(), save -> {

                return div().withClasses(row, my_3).with(

                  text(linkFetcher.get( save.link().getId() ).map(Link::href).orElse("Missing") + " " + save.tags() + " " + save.notes() )

                );

              } )

            )

          )

        )

      ));

  }

}

