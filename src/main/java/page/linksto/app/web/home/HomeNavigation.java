package page.linksto.app.web.home;

import j2html.tags.DomContent;

import static dev.rebelcraft.j2html.ext.ExtendedTagCreator.svg;
import static j2html.TagCreator.*;
import static page.linksto.app.web.utils.components.CollapseableSidebarMenuGroup.group;
import static page.linksto.app.web.utils.components.CollapseableSidebarMenuGroupItem.item;
import static page.linksto.app.web.utils.components.CollapseableSidebarMenu.menu;

public class HomeNavigation {

  public static DomContent homeSidebar() {

    return div().withClasses("flex-shrink-0", "p-3").withStyle("width: 280px;").with(
      a().withHref("/").withClasses("d-flex", "align-items-center", "pb-3", "mb-3", "link-body-emphasis", "text-decoration-none", "border-bottom").with(
        svg().withClasses("bi", "pe-none", "me-2").attr("width", "30").attr("height", "24").attr("aria-hidden", "true"),
        span("Home").withClasses("fs-5", "fw-semibold")
      ),
      menu(
        group( "home-collapse","Home",
          item("Latest", "#"),
          item("Stats", "#")
        ),
        group( "tags-collapse","Tags",
          item("github", "#"),
          item("tech", "#"),
          item("java", "#"),
          item("blog", "#")
        )
      )
    );

  }

}
