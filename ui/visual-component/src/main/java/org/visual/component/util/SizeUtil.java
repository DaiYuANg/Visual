package org.visual.component.util;

import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SizeUtil {
  private final int overlapLen = 1;

  public Node makeClipFor(@NonNull Region node) {
    var cut = new Rectangle();
    node.setClip(cut);
    node.widthProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var w = now.doubleValue();
              cut.setWidth(w);
            });
    node.heightProperty()
        .addListener(
            (ob, old, now) -> {
              if (now == null) return;
              var h = now.doubleValue();
              cut.setHeight(h);
            });
    cut.setWidth(node.getWidth());
    cut.setHeight(node.getHeight());
    return cut;
  }

  @SuppressWarnings("DuplicatedCode")
  public Group makeClipFor(@NonNull Region node, double cornerRadii) {
    var nodeCutTL =
        new Circle() {
          {
            setLayoutX(cornerRadii);
            setLayoutY(cornerRadii);
            setRadius(cornerRadii);
          }
        };
    var nodeCutTR =
        new Circle() {
          {
            setLayoutY(cornerRadii);
            setRadius(cornerRadii);
          }
        };
    var nodeCutTopMid =
        new javafx.scene.shape.Rectangle() {
          {
            setLayoutX(cornerRadii);
            setHeight(cornerRadii + overlapLen);
          }
        };
    var nodeCutBL =
        new Circle() {
          {
            setLayoutX(cornerRadii);
            setRadius(cornerRadii);
          }
        };
    var nodeCutBR =
        new Circle() {
          {
            setRadius(cornerRadii);
          }
        };
    var nodeCutBotMid =
        new javafx.scene.shape.Rectangle() {
          {
            setLayoutX(cornerRadii);
            setHeight(cornerRadii + overlapLen);
          }
        };
    var nodeCutMid =
        new Rectangle() {
          {
            setLayoutY(cornerRadii);
          }
        };
    var nodeCut =
        new Group(
            nodeCutTL, nodeCutTR, nodeCutTopMid, nodeCutBL, nodeCutBR, nodeCutBotMid, nodeCutMid);
    node.setClip(nodeCut);
    ChangeListener<? super Number> widthListener =
        (ob, old, now) -> {
          if (now == null) return;
          var w = now.doubleValue();
          nodeCutTR.setLayoutX(w - cornerRadii);
          nodeCutTopMid.setWidth(w - cornerRadii * 2);
          nodeCutBR.setLayoutX(w - cornerRadii);
          nodeCutBotMid.setWidth(w - cornerRadii * 2);
          nodeCutMid.setWidth(w);
        };
    node.widthProperty().addListener(widthListener);
    ChangeListener<? super Number> heightListener =
        (ob, old, now) -> {
          if (now == null) return;
          var h = now.doubleValue();
          nodeCutBL.setLayoutY(h - cornerRadii);
          nodeCutBR.setLayoutY(h - cornerRadii);
          nodeCutBotMid.setLayoutY(h - cornerRadii - overlapLen);
          nodeCutMid.setHeight(h - cornerRadii * 2);
        };
    node.heightProperty().addListener(heightListener);
    widthListener.changed(null, null, node.getWidth());
    heightListener.changed(null, null, node.getHeight());
    return nodeCut;
  }

  @SuppressWarnings("DuplicatedCode")
  public Group makeBottomOnlyRoundedClipFor(@NonNull Region node, double cornerRadii) {
    var nodeCutBL =
        new Circle() {
          {
            setLayoutX(cornerRadii);
            setRadius(cornerRadii);
          }
        };
    var nodeCutBR =
        new Circle() {
          {
            setRadius(cornerRadii);
          }
        };
    var nodeCutBotMid =
        new javafx.scene.shape.Rectangle() {
          {
            setLayoutX(cornerRadii);
            setHeight(cornerRadii + overlapLen);
          }
        };
    var nodeCutMid = new Rectangle();
    var nodeCut = new Group(nodeCutBL, nodeCutBR, nodeCutBotMid, nodeCutMid);
    node.setClip(nodeCut);
    ChangeListener<? super Number> widthListener =
        (ob, old, now) -> {
          if (now == null) return;
          var w = now.doubleValue();
          nodeCutBR.setLayoutX(w - cornerRadii);
          nodeCutBotMid.setWidth(w - cornerRadii * 2);
          nodeCutMid.setWidth(w);
        };
    node.widthProperty().addListener(widthListener);
    ChangeListener<? super Number> heightListener =
        (ob, old, now) -> {
          if (now == null) return;
          var h = now.doubleValue();
          nodeCutBL.setLayoutY(h - cornerRadii);
          nodeCutBR.setLayoutY(h - cornerRadii);
          nodeCutBotMid.setLayoutY(h - cornerRadii - overlapLen);
          nodeCutMid.setHeight(h - cornerRadii);
        };
    node.heightProperty().addListener(heightListener);
    widthListener.changed(null, null, node.getWidth());
    heightListener.changed(null, null, node.getHeight());
    return nodeCut;
  }

  @SuppressWarnings("DuplicatedCode")
  public Group makeTopOnlyRoundedClipFor(@NonNull Region node, double cornerRadii) {
    var nodeCutTL =
        new Circle() {
          {
            setLayoutX(cornerRadii);
            setLayoutY(cornerRadii);
            setRadius(cornerRadii);
          }
        };
    var nodeCutTR =
        new Circle() {
          {
            setLayoutY(cornerRadii);
            setRadius(cornerRadii);
          }
        };
    var nodeCutTopMid =
        new javafx.scene.shape.Rectangle() {
          {
            setLayoutX(cornerRadii);
            setHeight(cornerRadii + overlapLen);
          }
        };
    var nodeCutMid =
        new Rectangle() {
          {
            setLayoutY(cornerRadii);
          }
        };
    var nodeCut = new Group(nodeCutTL, nodeCutTR, nodeCutTopMid, nodeCutMid);
    node.setClip(nodeCut);
    ChangeListener<? super Number> widthListener =
        (ob, old, now) -> {
          if (now == null) return;
          var w = now.doubleValue();
          nodeCutTR.setLayoutX(w - cornerRadii);
          nodeCutTopMid.setWidth(w - cornerRadii * 2);
          nodeCutMid.setWidth(w);
        };
    node.widthProperty().addListener(widthListener);
    ChangeListener<? super Number> heightListener =
        (ob, old, now) -> {
          if (now == null) return;
          var h = now.doubleValue();
          nodeCutMid.setHeight(h - cornerRadii);
        };
    node.heightProperty().addListener(heightListener);
    widthListener.changed(null, null, node.getWidth());
    heightListener.changed(null, null, node.getHeight());
    return nodeCut;
  }

  public double getWidthOrPref(@NonNull Region r) {
    var w = r.getWidth();
    if (w == 0) return r.getPrefWidth();
    return w;
  }

  public double getHeightOrPref(Region r) {
    var h = r.getHeight();
    if (h == 0) return r.getPrefHeight();
    return h;
  }

  public void disableFocusColor(Node node) {
    node.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
  }
}
