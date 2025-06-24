package page.linksto.app.web.home;

import dev.rebelcraft.j2html.spring.webmvc.J2HtmlView;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import page.linksto.app.web.site.LinksToNavigation;
import page.linksto.app.web.site.LinksToSiteLayout;

import java.util.Map;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static j2html.TagCreator.*;
import static j2html.TagCreator.h1;

@Component
public class AnonymousHomeView extends J2HtmlView {

  @SuppressWarnings({"unchecked", "null"})
  @Override
  protected DomContent renderMergedOutputModelDomContent(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    // get from the model

    // nav
    String saveUrl = (String) model.get("saveUrl");

    // build the ui
    return LinksToSiteLayout.add("LinksTo | Home", model,

      each(

        LinksToNavigation.linksToNavigation(model),

        div().withClasses(container_fluid, py_4).with(

          div().withClasses(p_5, mb_4, bg_body_tertiary, rounded_3).with(
            div().withClasses(container_fluid, py_5).with(

              h1().withClasses(display_5, fw_bold).withText("linksto.page"),
              p().withClasses(col_md_8, fs_4).withText("Save. Tag. Share."),

              form().withMethod("get").withAction(saveUrl)
                .withClasses(row, row_cols_lg_auto, g_3, align_items_center)
                .with(
                   div().withClasses(col_12).with(
                    label().withFor("inputLink").withText("Link").withClasses(visually_hidden),
                    div().withClasses(input_group).with(
                      div().withClasses(input_group_text).with(
                        span().withClasses("bi", "bi-link-45deg")
                      ),
                      input().withName("url").withId("inputLink").withType("text").withClasses(form_control)
                        .withPlaceholder("Link URL")

                    )
                  ),
                  div().withClasses(col_12).with(
                    button().withClasses(btn, btn_primary).withType("submit").withText("Start saving")
                  )
                )
            )
          )

        )

      ));

  }

}

