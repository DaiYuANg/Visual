package org.visual.component;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.constant.AvailableFeature;

@Singleton
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class GuideContent extends SplitPane {

  private final SimpleObjectProperty<AvailableFeature> featureProperty =
      new SimpleObjectProperty<>(AvailableFeature.ER);

  //  private final List<Guide> guides;

  private final ListView<AvailableFeature> availableFeatureListView = new ListView<>();

  private final StackPane guideContent = new StackPane();

  void init() {
    availableFeatureListView.getItems().addAll(AvailableFeature.values());
    //    val guideView = guides.stream().map(Guide::guideView).map(Supplier::get).toList();
    //    guideContent.getChildren().addAll(guideView);
    getChildren().addAll(availableFeatureListView, guideContent);
  }
}
