package org.visual.app.component;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class GridBackground extends Region {
  private static final double HALF_PIXEL_OFFSET = -0.5;

  private static final String STYLE_CLASS = "graph-editor-grid";

  private static final Color DEFAULT_GRID_COLOR = Color.rgb(222, 248, 255);

  private double mLastWidth = -1;
  private double mLastHeight = -1;
  private final Path mGrid = new Path();

  public GridBackground() {
    super();
    mGrid.strokeProperty().set(DEFAULT_GRID_COLOR);
    getStyleClass().add(STYLE_CLASS);
    setManaged(true);
    setMouseTransparent(true);
  }

//  @Override
//  protected void layoutChildren() {
//
//  }

  @Override
  public void resize(double pWidth, double pHeight) {
    super.resize(pWidth, pHeight);

    if (mLastHeight != pHeight || mLastWidth != pWidth) {
      mLastHeight = pHeight;
      mLastWidth = pWidth;
      draw(pWidth, pHeight);
    }
  }

  void draw(final double pWidth, final double pHeight) {
    val spacing = 0.5;
    val hLineCount = Math.floor((pHeight + 1) / spacing);
    val vLineCount = Math.floor((pWidth + 1) / spacing);

    for (int i = 0; i < hLineCount; i++) {
      val y = (i + 1) * spacing + HALF_PIXEL_OFFSET;
      mGrid.getElements().add(new MoveTo(0, y));
      mGrid.getElements().add(new LineTo(pWidth, y));
    }

    for (int i = 0; i < vLineCount; i++) {
      val x = (i + 1) * spacing + HALF_PIXEL_OFFSET;
      mGrid.getElements().add(new MoveTo(x, 0));
      mGrid.getElements().add(new LineTo(x, pHeight));
    }
  }
}
