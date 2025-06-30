package page.linksto.app.web.utils.components;

import j2html.tags.DomContent;

import static j2html.TagCreator.a;
import static j2html.TagCreator.li;

public class CollapseableSidebarMenuGroupItem {
  public static DomContent item(String text, String href) {
    return li(
        a(text)
          .withHref(href)
          .withClasses("link-body-emphasis", "d-inline-flex", "text-decoration-none", "rounded")
    );
  }
}
