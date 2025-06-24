package page.linksto.app.web.site;

import dev.rebelcraft.j2html.bootstrap.BootstrapConfig;
import j2html.tags.DomContent;
import j2html.tags.specialized.HtmlTag;

import java.util.Map;
import java.util.Set;

import static j2html.TagCreator.*;

public class LinksToSiteLayout {

  public static DomContent add(String title, Map<String, ?> model, DomContent... content) {

    return add(title, model, Set.of(), content);

  }

  public static DomContent add(String title, Map<String, ?> model, Set<String> styles, DomContent... content) {

    // build the view
    HtmlTag html = html().withLang("en").with(
      head(
        meta().withCharset("UTF-8"),
        meta().withName("viewport")
          .withContent("width=device-width, initial-scale=1"),

        title(title),
        link().withHref(BootstrapConfig.CDN_MIN_CSS_URL)
          .withRel("stylesheet").attr(
            "integrity",
            BootstrapConfig.CDN_MIN_CSS_INTEGRITY)
          .attr("crossorigin", "anonymous"),
        link().withHref("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css")
          .withRel("stylesheet"),
        each(styles, style -> {
          return style(style);
        })
      ),
      body(each(content)),
      // script().withSrc(BootstrapConfig.POPPER_MIN_JS_URL)
      //                 .attr("integrity", BootstrapConfig.POPPER_MIN_JS_INTEGRITY)
      //                 .attr("crossorigin", "anonymous"),
      script().withSrc(BootstrapConfig.CDN_BUNDLE_MIN_JS_URL)
        .attr("integrity", BootstrapConfig.CDN_BUNDLE_MIN_JS_INTEGRITY)
        .attr("crossorigin", "anonymous"));

    return html;

  }

}
