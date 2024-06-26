package org.visual.i18n;

import static java.util.Optional.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Slf4j
public enum I18n {
  INSTANCE;

  private final Locale defaultLocalLocale = Locale.getDefault();

  private final Locale defaultLocale = Locale.ENGLISH;

  private final ResourceBundle defaultResourceBundle =
      ResourceBundle.getBundle("language", defaultLocale);

  private final MutableMap<Locale, ResourceBundle> loadedResource =
      Maps.mutable.<Locale, ResourceBundle>empty().asSynchronized();

  public String t(String index, Locale locale) {
    return ofNullable(loadedResource.computeIfAbsent(locale, loadBundle()))
        .filter(bundle -> bundle.containsKey(index))
        .map(bundle -> bundle.getString(index))
        .orElseGet(
            () ->
                of(defaultResourceBundle.getString(index))
                    .orElseThrow(
                        () ->
                            new IllegalArgumentException(
                                "Key not found in resource bundles: " + index)));
  }

  public String t(String index) {
    return t(index, defaultLocalLocale);
  }

  @Contract(pure = true)
  private @NotNull Function<Locale, ResourceBundle> loadBundle() {
    return loc -> ResourceBundle.getBundle("language", loc);
  }
}
