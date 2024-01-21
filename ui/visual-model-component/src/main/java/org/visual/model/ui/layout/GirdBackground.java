package org.visual.model.ui.layout;

import com.google.common.collect.HashBasedTable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.IntStream;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.CacheHint;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class GirdBackground extends Pane {
    private final double halfPixelOffset;
    private Color defaultGirdColor = Color.CORNSILK;
    private final String girdColorSelector = "-grid-color";
    private final String girdColorPropertyName = "gridColor";
    private double mLastWidth = -1.0;
    private double mLastHeight = -1.0;
    private final String internalStyleClass = "graph-editor-grid";
    private final Path mGrid = new Path();
    private final HashBasedTable<Integer, Double, MoveTo> hMoveToCache = createHashBasedTable(200, 200);
    private final HashBasedTable<Double, Double, LineTo> hLineToCache = createHashBasedTable(200, 200);
    private final HashBasedTable<Double, Double, MoveTo> lMoveToCache = createHashBasedTable(200, 200);
    private final HashBasedTable<Double, Double, LineTo> lLineToCache = createHashBasedTable(200, 200);

    private final DoubleProperty mGridSpacing = new DoublePropertyBase(10.0) {
        @Override
        public Object getBean() {
            return this;
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

    private final CssMetaData<GirdBackground, Color> girdColor = new CssMetaData<GirdBackground, Color>(
            girdColorSelector, StyleConverter.getColorConverter()) {
        @Override
        public boolean isSettable(@NotNull GirdBackground node) {
            return !node.mGridColor.isBound();
        }

        @Contract(pure = true)
        @Override
        public StyleableProperty<Color> getStyleableProperty(@NotNull GirdBackground node) {
            return node.mGridColor;
        }
    };

    private final StyleableObjectProperty<Color> mGridColor = new StyleableObjectProperty<>(defaultGirdColor) {
        @Override
        public CssMetaData<GirdBackground, Color> getCssMetaData() {
            return girdColor;
        }

        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return girdColorPropertyName;
        }

        @Override
        protected void invalidated() {
            requestLayout();
        }
    };

    public GirdBackground(double halfPixelOffset, Color defaultGirdColor) {
        this.halfPixelOffset = halfPixelOffset;
        this.defaultGirdColor = defaultGirdColor;
        setManaged(false);
        setMouseTransparent(false);
        getStyleClass().add(internalStyleClass);
        mGrid.strokeProperty().bind(mGridColor);
        getChildren().add(mGrid);
        setCacheShape(true);
        setCacheHint(CacheHint.SPEED);
    }

    @Override
    public void resize(double pWidth, double pHeight) {
        super.resize(pWidth, pHeight);
        if (mLastHeight != pHeight || mLastWidth != pWidth) {
            mLastHeight = pHeight;
            mLastWidth = pWidth;
            Platform.runLater(() -> draw(pWidth, pHeight));
        }
    }

    public void draw(double pWidth, double pHeight) {
        double spacing = mGridSpacing.get();
        int hLineCount = (int) Math.min((pHeight + 1) / spacing, Integer.MAX_VALUE);
        int vLineCount = (int) Math.min((pWidth + 1) / spacing, Integer.MAX_VALUE);
        mGrid.getElements().clear();
//        mGrid.getElements().addAll(drawL(vLineCount, spacing, pHeight),
//                drawH(hLineCount, spacing, pWidth, pHeight));
    }

    private PathElement @NotNull [] drawL(int vLineCount, double spacing, double pHeight) {
        return IntStream.range(0, vLineCount)
                .mapToObj(i -> {
                    double x = (i + 1) * spacing + halfPixelOffset;
                    return Arrays.asList(lMoveTo(x), lLineTo(x, pHeight));
                })
                .flatMap(Collection::stream)
                .toArray(PathElement[]::new);
    }

    private LineTo lLineTo(double x, double pHeight) {
        return Optional.ofNullable(lLineToCache.get(x, pHeight)).orElseGet(() -> new LineTo(x, pHeight));
    }

    private MoveTo lMoveTo(double x) {
        return Optional.ofNullable(lMoveToCache.get(x, 0.0)).orElseGet(() -> new MoveTo(x, 0.0));
    }

    private PathElement @NotNull [] drawH(int hLineCount, double spacing, double pWidth, double pHeight) {
        return IntStream.range(0, hLineCount)
                .mapToObj(i -> {
                    double y = (i + 1) * spacing + halfPixelOffset;
                    return Arrays.asList(hMoveTo(y), hLineTo(pWidth, y, pHeight));
                })
                .flatMap(Collection::stream)
                .toArray(PathElement[]::new);
    }

    private LineTo hLineTo(double pWidth, double y, double pHeight) {
        return Optional.ofNullable(hLineToCache.get(pHeight, y)).orElseGet(() -> new LineTo(pWidth, y));
    }

    private MoveTo hMoveTo(double y) {
        return Optional.ofNullable(hMoveToCache.get(0, y)).orElseGet(() -> new MoveTo(0.0, y));
    }

    @Contract(value = "_, _ -> new", pure = true)
    private <R, C, V> @NotNull HashBasedTable<R, C, V> createHashBasedTable(int rows, int cols) {
        return HashBasedTable.create(rows, cols);
    }
}
