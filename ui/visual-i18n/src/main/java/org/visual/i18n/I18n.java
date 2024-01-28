package org.visual.i18n;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@ToString
@Slf4j
public class I18n {
  private final ResourceBundle resourceBundle;

  public I18n(@NotNull Locale locale) {
    this.resourceBundle = ResourceBundle.getBundle(I18nUtil.baseName, locale);
    log.info("k:{}", resourceBundle.keySet());
  }

  public String get(@NotNull I18nKeys keys) {
    if (resourceBundle.containsKey(keys.getValue())) {
      return resourceBundle.getString(keys.getValue());
    }
    throw new NoSuchElementException(keys.getValue());
  }
}
