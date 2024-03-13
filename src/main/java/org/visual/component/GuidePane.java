package org.visual.component;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class GuidePane extends DialogPane {

  final GuideContent content;

  void init() {
    getButtonTypes().addAll(ButtonType.FINISH, ButtonType.CANCEL);
    setContent(content);
  }
}
