package org.visual.i18n;

import java.util.Locale;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
@SuppressWarnings({"missing-explicit-ctor"})
public class I18nUtil {

  public static final String baseName = "language";

  @Contract(" -> new")
  public static @NotNull I18n getDefaultLocale() {
    return new I18n(Locale.getDefault());
  }

  @Contract("_ -> new")
  public static @NotNull I18n get(@NonNull Locale locale) {
    return new I18n(locale);
  }
}
