package page.linksto.app.web.site;

import j2html.TagCreator;
import j2html.tags.specialized.NavTag;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;

import java.util.Map;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static j2html.TagCreator.*;
import static j2html.TagCreator.nav;

public class LinksToNavigation {

  public static NavTag linksToNavigation(Map<String, Object> model) {

    // get urls from the model
    String homeUrl = (String) model.get("homeUrl");
    String searchUrl = (String) model.get("searchUrl");

    // admin urls

    // external services

    // security
    User user = (User) model.get("user");
    String loginUrl = (String) model.get("loginUrl");
    String logoutUrl = (String) model.get("logoutUrl");
    CsrfToken csrfToken = (CsrfToken) model.get("_csrf");

    return nav().withClasses(navbar, navbar_expand_lg, bg_primary_subtle, mb_4).with(

      div().withClasses(container_fluid).with(

        a().withClasses(navbar_brand, px_3).withHref(homeUrl)
          .with(
            span().withClasses("bi", "bi-bookmark-heart-fill"),
            text("linksto.page")
          ),

        button().withClass(navbar_toggler).withType("button").attr("data-bs-toggle", "collapse")
          .attr("data-bs-target", "#navbarToggler").attr("aria-controls", "navbarToggler")
          .attr("aria-expanded", "false").attr("aria-label", "Toggle Navigation")
          .with(span().withClass(navbar_toggler_icon)),

        div().withClasses(collapse, navbar_collapse, d_lg_flex, justify_content_lg_between).withId("navbarToggler")
          .with(

            div(),

            form().withClasses(d_inline_flex, mb_0).with(

              input().withType("hidden").withName(csrfToken.getParameterName()).withValue(csrfToken.getToken()),

              input().withClasses(form_control, me_2).withType("search"), button().withType("submit")
                .withClasses(btn, btn_outline_secondary).with(span().withClasses("bi", "bi-search"))

            ),

            ul().withClass(navbar_nav).with(

              li().withClasses(nav_item, dropdown).with(

                TagCreator.iffElse(user != null,

                  a().withClasses(nav_link, dropdown_toggle).withText(user != null ? user.getUsername() : "").withHref("#")
                    .attr("data-bs-toggle", "dropdown").attr("role", "button")
                    .attr("aria-expanded", "false"),

                  a().withClasses(nav_link, dropdown_toggle).withText("Sign In").withHref("#")
                    .attr("data-bs-toggle", "dropdown").attr("role", "button")
                    .attr("aria-expanded", "false")

                ),

                ul().withClasses(dropdown_menu, dropdown_menu_end).with(

                  li().with(a().withClasses(dropdown_item).withHref(homeUrl).withText("Home")),

                  li().withClasses(dropdown_divider),

                  TagCreator.iffElse(user != null,

                    li().with(a().withClasses(dropdown_item).withHref(logoutUrl).withText("Logout")),

                    li().with(a().withClasses(dropdown_item).withHref(loginUrl).withText("SIgn In"))

                  )

                ))

            )

          )

      )

    );
  }

}
