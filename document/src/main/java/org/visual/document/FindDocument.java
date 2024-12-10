package org.visual.document;

import io.vavr.control.Option;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;

@UtilityClass
@Slf4j
public class FindDocument {

  @SneakyThrows
  public Optional<URI> getDocumentURL(final @NotNull Locale locale) {
    val language = locale.getLanguage();
    log.atInfo().log("Language:{}", language);

    val root = FindDocument.class.getClassLoader().getResource("org/visual/document/asciidoc/" + language + "index.html");
    return Option.ofOptional(Optional.ofNullable(root))
      .toTry()
      .mapTry(URL::toURI)
      .toJavaOptional();
  }
}
