package org.visual.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.constant.AvailableFeature;
import org.visual.constant.FXMLView;
import org.visual.context.CreationContext;
import org.visual.context.UIContext;

@Singleton
@Slf4j
public class CreationGuideContainerController implements Initializable {

  private final Map<AvailableFeature, Supplier<Parent>> featureSupplierMap =
      new HashMap<>() {
        {
          put(AvailableFeature.ER, () -> UIContext.INSTANCE.load(FXMLView.ER_GUIDE));
          put(
              AvailableFeature.OBJECT_ORIENTED,
              () -> UIContext.INSTANCE.load(FXMLView.OBJECT_ORIENTED_GUIDE));
        }
      };
  @FXML public StackPane container;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    CreationContext.INSTANCE.addListener(
        (observableValue, feature, t1) -> {
          log.info("selected:{}", feature);
          if (Boolean.FALSE.equals(container.getChildren().isEmpty())) {
            container.getChildren().removeFirst();
          }
          val currentPane = featureSupplierMap.get(feature).get();
          container.getChildren().add(currentPane);
        });
  }
}
