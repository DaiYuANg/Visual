package org.visual.editor.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Pair;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.floor;

@Slf4j
public class GridBackground extends Region {
  private static final double HALF_PIXEL_OFFSET = -0.5;

  private static final String STYLE_CLASS = "editor-grid";

  private static final Color DEFAULT_GRID_COLOR = Color.GRAY;

  private double mLastWidth = -1;
  private double mLastHeight = -1;
  private final Path mGrid = new Path();

  @Getter
  private final DoubleProperty spacing = new DoublePropertyBase(12) {

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
    super();
    mGrid.strokeProperty().set(DEFAULT_GRID_COLOR);
    getStyleClass().add(STYLE_CLASS);
    setManaged(false);
    setMouseTransparent(true);
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

  void draw(final Double pWidth, final Double pHeight) {
    val spacing = this.spacing.get();
    val hLineCount = (int) floor((pHeight + 1) / spacing);
    val vLineCount = (int) floor((pWidth + 1) / spacing);

    mGrid.getElements().clear();

    val xLine = renderXLine(pWidth, hLineCount);

    val yLine = renderYLine(pHeight, vLineCount);

    val elements = Stream
      .concat(xLine, yLine)
      .flatMap(p -> Stream.of(p.getKey(), p.getValue())).toList();
    mGrid.getElements().addAll(elements);
  }

  private @NotNull Stream<Pair<MoveTo, LineTo>> renderYLine(Double pHeight, int vLineCount) {
    val spacing = this.spacing.get();
    return IntStream
      .range(0, vLineCount)
      .parallel()
      .mapToDouble(i -> (i + 1) * spacing + HALF_PIXEL_OFFSET)
      .mapToObj(x -> new Pair<>(new MoveTo(x, 0), new LineTo(x, pHeight)));
  }

  private @NotNull Stream<Pair<MoveTo, LineTo>> renderXLine(Double pWidth, int hLineCount) {
    val spacing = this.spacing.get();
    return IntStream
      .range(0, hLineCount)
      .parallel()
      .mapToDouble(i -> (i + 1) * spacing + HALF_PIXEL_OFFSET)
      .mapToObj(y -> new Pair<>(new MoveTo(0, y), new LineTo(pWidth, y)));
  }
}
