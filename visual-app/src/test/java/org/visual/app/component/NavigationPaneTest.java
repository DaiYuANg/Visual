package org.visual.app.component;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

class NavigationPaneTest extends ApplicationTest {

  private Route route;

  private Label label;

  @Override
  public void start(Stage stage) throws Exception {
    super.start(stage);
    val pane = new NavigationPane();
    val scene = new Scene(pane);
    route = new Route();
    route.setPath("/");
    label = new Label("test");
    route.setContent(label);
    pane.addRoute(route);
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void testIndex() {
    FxAssert.verifyThat(label, LabeledMatchers.hasText("test"));
  }

  @Test
  void back() {
  }

  @Test
  void forward() {
  }
}