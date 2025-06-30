package page.linksto.app.web.utils.components;

import j2html.tags.DomContent;

import static j2html.TagCreator.*;

public class CollapseableSidebarMenuGroup {
  public static DomContent group(String id, String name, DomContent... items) {
    return li().withClasses("mb-1").with(
      button(name)
        .withClasses("btn", "btn-toggle", "d-inline-flex", "align-items-center", "rounded", "border-0")
        .attr("data-bs-toggle", "collapse")
        .attr("data-bs-target", "#" + id)
        .attr("aria-expanded", "true"),
      div().withId(id).withClasses("collapse", "show").with(
        ul().withClasses("btn-toggle-nav", "list-unstyled", "fw-normal", "pb-1", "small").with(
          items
        )
      )
    );
  }
}
