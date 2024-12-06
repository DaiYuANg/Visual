package org.visual.app.component;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

@Slf4j
public class GridBackground extends Region {
  // This is to make the stroke be drawn 'on pixel'.
  private static final double HALF_PIXEL_OFFSET = -0.5;

  private static final Color DEFAULT_GRID_COLOR = Color.rgb(222, 248, 255);

  private double mLastWidth = -1;
  private double mLastHeight = -1;
  private final Path mGrid = new Path();
//  private final StyleableObjectProperty<Color> mGridColor = new SimpleStyleableObjectProperty<>(DEFAULT_GRID_COLOR);

  private final DoubleProperty mGridSpacing = new DoublePropertyBase(0.5) {

    @Override
    public Object getBean() {
      return GridBackground.this;
    }

    @Contract(pure = true)
    @Override
    public @NotNull String getName() {
      return "gridSpacing";
    }

    @Override
    protected void invalidated() {
      draw(getWidth(), getHeight());
    }
  };

  public GridBackground() {
    setManaged(false);
    setMouseTransparent(true);
//    mGrid.strokeProperty().bind(mGridColor);
    getChildren().add(mGrid);
  }

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
    val spacing = getGridSpacing();
    val hLineCount = (int) Math.floor((pHeight + 1) / spacing);
    val vLineCount = (int) Math.floor((pWidth + 1) / spacing);

    IntStream.range(0, hLineCount)
      .mapToDouble(i -> (i + 1) * spacing + HALF_PIXEL_OFFSET).forEach(y -> {
        mGrid.getElements().add(new MoveTo(0, y));
        mGrid.getElements().add(new LineTo(pWidth, y));
      });

    IntStream
      .range(0, vLineCount)
      .mapToDouble(i -> (i + 1) * spacing + HALF_PIXEL_OFFSET).forEach(x -> {
        mGrid.getElements().add(new MoveTo(x, 0));
        mGrid.getElements().add(new LineTo(x, pHeight));
      });
  }

  public double getGridSpacing() {
    return mGridSpacing.get();
  }

  public void setGridSpacing(final double gridSpacing) {
    mGridSpacing.set(gridSpacing);
  }

  public DoubleProperty gridSpacingProperty() {
    return mGridSpacing;
  }

}
