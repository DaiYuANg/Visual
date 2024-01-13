package org.visual.model.i18n;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

@UtilityClass
public class I18nHelper {

    public final String baseName = "language";

    @Contract(" -> new")
    public static @NotNull I18n getDefaultLocale() {
        return new I18n(Locale.getDefault());
    }
}