package org.visual.component.control.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.visual.component.container.FusionPane;
import org.visual.component.container.VStage;
import org.visual.component.control.button.FusionButton;
import org.visual.component.layout.HPadding;
import org.visual.component.layout.VPadding;
import org.visual.component.util.FXUtils;

public class VDialog<T> {
  private static final int BUTTON_HEIGHT = 45;
  private static final int BUTTON_PANE_HEIGHT = BUTTON_HEIGHT + FusionPane.PADDING_V * 2;

  @Getter private final VStage stage = new VStage();
  private final Label messageLabel = new Label();
  private final Group content = new Group(messageLabel);
  private final FusionPane buttonPane = new FusionPane();
  private final HBox buttonHBox = new HBox();

  protected T returnValue;

  public VDialog() {
    stage.getStage().setWidth(900);
    stage.getStage().centerOnScreen();

    messageLabel.setWrapText(true);

    buttonPane.getContentPane().getChildren().add(buttonHBox);
    buttonPane.getNode().setPrefHeight(BUTTON_PANE_HEIGHT);

    buttonHBox.setAlignment(Pos.CENTER_RIGHT);
    buttonHBox.setSpacing(5);
    FXUtils.observeWidth(buttonPane.getContentPane(), buttonHBox);

    FXUtils.observeWidth(
        stage.getInitialScene().getScrollPane().getNode(),
        stage.getInitialScene().getContentPane(),
        -1);
    var root = stage.getInitialScene().getContentPane();
    root.widthProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var w = now.doubleValue();
              messageLabel.setPrefWidth(w - 20);
              buttonPane.getNode().setPrefWidth(w - 20);
            });
    root.heightProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var h = now.doubleValue();
              h = VStage.TITLE_BAR_HEIGHT + h + 10;
              stage.getStage().setHeight(h);
            });
    FXUtils.forceUpdate(stage.getStage());
    root.getChildren()
        .add(
            new HBox(
                new HPadding(10),
                new VBox(new VPadding(10), content, new VPadding(10), buttonPane.getNode())));
  }

  public void setText(String text) {
    messageLabel.setText(text);
  }

  public Label getMessageNode() {
    return messageLabel;
  }

  public void setButtons(@NotNull List<VDialogButton<T>> buttons) {
    buttonHBox.getChildren().clear();
    var ls = new ArrayList<Node>();
    buttons.forEach(
        btn -> {
          var name = btn.name;
          var button = new FusionButton(name);
          var textBounds = FXUtils.calculateTextBounds(button.getTextNode());
          button.setPrefWidth(Math.max(textBounds.getWidth() + 40, 120));
          button.setPrefHeight(BUTTON_HEIGHT);
          ls.add(button);
          button.setOnAction(
              e -> {
                if (btn.provider != null) {
                  returnValue = btn.provider.get();
                }
                onButtonClicked(btn);
                stage.close();
              });
          btn.button = button;
        });
    buttonHBox.getChildren().addAll(ls);
  }

  public Group getCleanContent() {
    content.getChildren().remove(messageLabel);
    return content;
  }

  protected void onButtonClicked(VDialogButton<T> btn) {}

  public Optional<T> showAndWait() {
    stage.showAndWait();
    getStage().temporaryOnTop();
    return Optional.ofNullable(returnValue);
  }
}
