package page.linksto.app.web.save;

import java.util.List;

public record SaveForm(
  String url,
  String tagText,
  List<String> tags,
  String notes
) {
}
