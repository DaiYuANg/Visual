package org.visual.app.lifecycle;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.jthemedetecor.OsThemeDetector;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import static javafx.application.Application.setUserAgentStylesheet;

@Singleton
@RequiredArgsConstructor
@Named("SetupThemeLifecycle")
public class SetupThemeLifecycle implements ViewLifecycle {
  private final OsThemeDetector detector;

  @Override
  public void onInit() {
    setUserAgentStylesheet(detectTheme());
    detector.registerListener(isDark -> setUserAgentStylesheet(isDark ? new PrimerDark().getUserAgentStylesheet() : new PrimerLight().getUserAgentStylesheet()));
  }

  private @NotNull String detectTheme() {
    val isDark = detector.isDark();
    return isDark ? new PrimerDark().getUserAgentStylesheet() : new PrimerLight().getUserAgentStylesheet();
  }
}
