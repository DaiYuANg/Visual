package org.visual.controller.creation;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.context.CreationContext;
import org.visual.context.DIContext;
import org.visual.guide.Guide;

@Singleton
@Slf4j
public class CreationGuideContainerController implements Initializable {

  @FXML StackPane container;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    val guides =
        DIContext.INSTANCE.getInjector().list(Guide.class).stream()
            .collect(Collectors.toMap(Guide::feature, Guide::guideView));

    Optional.ofNullable(CreationContext.INSTANCE.get())
        .ifPresent(
            feature -> {
              val currentPane = guides.get(feature);
              container.getChildren().add(currentPane.get());
            });

    CreationContext.INSTANCE.addListener(
        (observableValue, feature, newValue) -> {
          log.atTrace().log("selected:{}", newValue);
          if (Boolean.FALSE.equals(container.getChildren().isEmpty())) {
            container.getChildren().removeFirst();
          }
          val currentPane = guides.get(newValue);
          container.getChildren().add(currentPane.get());
        });
  }
}
