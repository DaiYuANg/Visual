package org.visual.i18n;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.ResourceBundle.getBundle;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class I18n {

  private final Locale defaultLocalLocale = Locale.getDefault();

  private final Locale defaultLocale = Locale.ENGLISH;

  private final ResourceBundle defaultResourceBundle =
    getBundle("language", defaultLocale);

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
    return loc -> getBundle("language", loc);
  }
}
