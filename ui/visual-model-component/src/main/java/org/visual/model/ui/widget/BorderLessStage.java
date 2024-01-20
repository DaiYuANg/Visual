package org.visual.model.ui.widget;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.visual.model.ui.constant.ResizeDirection;

import java.util.Objects;

@Slf4j
public class BorderLessStage extends Stage {
    private boolean isDraggable = false;
    private boolean isDragging = false;
    private boolean allowDragging = true;
    private double dragMarginTop = 0.0;
    private double dragMarginRight = 0.0;
    private double dragMarginBottom = 0.0;
    private double dragMarginLeft = 0.0;

    private boolean isFullscreenable = false;
    private boolean isFullscreen = false;
    private boolean allowFullscreen = true;
    private double fullscreenMarginTop = 0.0;
    private double fullscreenMarginRight = 0.0;
    private double fullscreenMarginBottom = 0.0;
    private double fullscreenMarginLeft = 0.0;
    private double stageWidthBeforeFullscreen = 0.0;
    private double stageHeightBeforeFullscreen = 0.0;
    private double stageXBeforeFullscreen = 0.0;
    private double stageYBeforeFullscreen = 0.0;

    private boolean isResizeable = false;
    private boolean isResizing = false;
    private boolean allowResizing = true;
    private ResizeDirection resizeDirection = null;
    private double resizeMarginTop = 0.0;
    private double resizeMarginRight = 0.0;
    private double resizeMarginBottom = 0.0;
    private double resizeMarginLeft = 0.0;

    private boolean made = false;

    {
        initStyle(StageStyle.UNDECORATED);
    }

    {
        addEventHandler(WindowEvent.WINDOW_SHOWN, e -> {
            if (Boolean.TRUE.equals(made)) {
                return;
            }
            makeDraggable();
            makeFullscreenable();
            makeResizeable();
            made = true;
        });
    }


    public BorderLessStage makeDraggable(
            double marginTop,
            double marginRight,
            double marginBottom,
            double marginLeft
    ) {
        dragMarginTop = marginTop;
        dragMarginRight = marginRight;
        dragMarginBottom = marginBottom;
        dragMarginLeft = marginLeft;

        if (isDraggable) {
            return this;
        }

        isDraggable = true;

        double[] dragStartOffsetX = {0.0};
        double[] dragStartOffsetY = {0.0};
        getScene().addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            boolean isWithinBounds = detectDraggingBounds(event);

            if (isDraggable && allowDragging && isWithinBounds) {
                getScene().setCursor(Cursor.OPEN_HAND);
            } else {
                if (getScene().getCursor() == Cursor.OPEN_HAND) {
                    getScene().setCursor(Cursor.DEFAULT);
                }
            }
        });

        getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            dragStartOffsetX[0] = this.getX() - event.getScreenX();
            dragStartOffsetY[0] = this.getY() - event.getScreenY();
        });

        getScene().addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            boolean isWithinBounds = detectDraggingBounds(event);

            if (isDraggable && allowDragging && isWithinBounds) {
                isDragging = true;
                getScene().setCursor(Cursor.CLOSED_HAND);
            }

            if (isDragging) {
                setX(event.getScreenX() + dragStartOffsetX[0]);
                setY(event.getScreenY() + dragStartOffsetY[0]);
            }
        });

        getScene().addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if (isDragging) {
                isDragging = false;
                getScene().setCursor(Cursor.DEFAULT);
            }
        });

        return this;
    }

    private void makeDraggable() {
        makeDraggable(10.0, 10.0, 10.0, 10.0);
    }

    private boolean detectDraggingBounds(@NotNull MouseEvent event) {
        return event.getSceneY() <= dragMarginTop
                || getScene().getHeight() - event.getSceneY() <= dragMarginBottom
                || event.getSceneX() <= dragMarginLeft
                || getScene().getWidth() - event.getSceneX() <= dragMarginRight;
    }

    public BorderLessStage makeFullscreenable(
            double marginTop,
            double marginRight,
            double marginBottom,
            double marginLeft
    ) {
        fullscreenMarginTop = marginTop;
        fullscreenMarginRight = marginRight;
        fullscreenMarginBottom = marginBottom;
        fullscreenMarginLeft = marginLeft;

        if (!isFullscreenable) {
            isFullscreenable = true;

            getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                boolean isDoubleClick = event.getButton() == MouseButton.PRIMARY && event.getClickCount() >= 2;

                if (isFullscreenable && allowFullscreen && isDoubleClick && detectFullscreenBounds(event)) {
                    if (isFullscreen) {
                        isFullscreen = false;
                        allowDragging = true;
                        allowResizing = true;

                        this.setX(stageXBeforeFullscreen);
                        this.setY(stageYBeforeFullscreen);
                        this.setWidth(stageWidthBeforeFullscreen);
                        this.setHeight(stageHeightBeforeFullscreen);
                    } else {
                        isFullscreen = true;
                        allowDragging = false;
                        allowResizing = false;
                        stageWidthBeforeFullscreen = this.getWidth();
                        stageHeightBeforeFullscreen = this.getHeight();
                        stageXBeforeFullscreen = this.getX();
                        stageYBeforeFullscreen = this.getY();

                        Screen screen = Screen.getPrimary();
                        double newWidth = Math.min(this.getMaxWidth(), screen.getVisualBounds().getWidth());
                        double newHeight = Math.min(this.getMaxHeight(), screen.getVisualBounds().getHeight());

                        this.setWidth(newWidth);
                        this.setHeight(newHeight);
                        this.setX(screen.getVisualBounds().getMinX());
                        this.setY(screen.getVisualBounds().getMinY());
                    }
                }
            });
        }

        return this;
    }

    private void makeFullscreenable() {
        makeFullscreenable(10.0, 10.0, 10.0, 10.0);
    }

    private boolean detectFullscreenBounds(@NotNull MouseEvent event) {
        val isWithinBounds = event.getSceneY() <= fullscreenMarginTop
                || getScene().getHeight() - event.getSceneY() <= fullscreenMarginBottom
                || event.getSceneX() <= fullscreenMarginLeft
                || getScene().getWidth() - event.getSceneX() <= fullscreenMarginRight;

        val resizeDirection = detectResizeDirection(event);

        return isWithinBounds && resizeDirection == null;
    }

    public BorderLessStage makeResizable(
            double marginTop,
            double marginRight,
            double marginBottom,
            double marginLeft
    ) {
        resizeMarginTop = marginTop;
        resizeMarginRight = marginRight;
        resizeMarginBottom = marginBottom;
        resizeMarginLeft = marginLeft;

        if (isResizeable) {
            return this;
        }
        isResizeable = true;

        getScene().addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            val e = detectResizeDirection(event);
            if (isResizeable && allowResizing && !isResizing && Objects.nonNull(e)) {
                switch (e) {
                    case NORTH_WEST -> getScene().setCursor(Cursor.NW_RESIZE);
                    case NORTH_EAST -> getScene().setCursor(Cursor.NE_RESIZE);
                    case SOUTH_WEST -> getScene().setCursor(Cursor.SW_RESIZE);
                    case SOUTH_EAST -> getScene().setCursor(Cursor.SE_RESIZE);
                    case NORTH -> getScene().setCursor(Cursor.N_RESIZE);
                    case SOUTH -> getScene().setCursor(Cursor.S_RESIZE);
                    case WEST -> getScene().setCursor(Cursor.W_RESIZE);
                    case EAST -> getScene().setCursor(Cursor.E_RESIZE);

//                    default:
//                        scene.get
//                        if (scene.getCursor().getType().isResizable()) {
//                            scene.setCursor(Cursor.DEFAULT);
//                        }
                    default -> throw new IllegalStateException("Unexpected value: " + e);
                }
            }
        });

        double[] resizeStartFromSceneX = {0.0};
        double[] resizeStartFromSceneY = {0.0};
        double[] resizeStartFromScreenX = {0.0};
        double[] resizeStartFromScreenY = {0.0};
        double[] resizeStartStageWidth = {0.0};
        double[] resizeStartStageHeight = {0.0};

        getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (isResizeable && allowResizing && !isResizing) {
                resizeDirection = detectResizeDirection(event);

                if (resizeDirection != null) {
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() >= 2) {
                        Screen screen = Screen.getPrimary();

                        if (resizeDirection == ResizeDirection.NORTH || resizeDirection == ResizeDirection.NORTH_WEST || resizeDirection == ResizeDirection.NORTH_EAST) {
                            this.setHeight(ensureStageHeightIsWithinLimits(this.getHeight() + this.getY()));
                            this.setY(0.0);
                        }

                        if (resizeDirection == ResizeDirection.SOUTH || resizeDirection == ResizeDirection.SOUTH_WEST || resizeDirection == ResizeDirection.SOUTH_EAST) {
                            this.setHeight(ensureStageHeightIsWithinLimits(screen.getVisualBounds().getHeight() - this.getY() + screen.getVisualBounds().getMinY()));

                            if (this.getHeight() == screen.getVisualBounds().getHeight()) {
                                this.setY(0.0);
                            }
                        }

                        if (resizeDirection == ResizeDirection.WEST || resizeDirection == ResizeDirection.NORTH_WEST || resizeDirection == ResizeDirection.SOUTH_WEST) {
                            this.setWidth(ensureStageWidthIsWithinLimits(this.getWidth() + this.getX()));
                            this.setX(0.0);
                        }

                        if (resizeDirection == ResizeDirection.EAST || resizeDirection == ResizeDirection.NORTH_EAST || resizeDirection == ResizeDirection.SOUTH_EAST) {
                            this.setWidth(ensureStageWidthIsWithinLimits(screen.getVisualBounds().getWidth() - this.getX()));

                            if (this.getWidth() == screen.getVisualBounds().getWidth()) {
                                this.setX(0.0);
                            }
                        }
                    } else {
                        isResizing = true;
                        isDraggable = false;
                        isFullscreenable = false;

                        resizeStartFromScreenX[0] = event.getScreenX();
                        resizeStartFromScreenY[0] = event.getScreenY();
                        resizeStartFromSceneX[0] = event.getSceneX();
                        resizeStartFromSceneY[0] = event.getSceneY();
                        resizeStartStageWidth[0] = this.getWidth();
                        resizeStartStageHeight[0] = this.getHeight();
                    }
                }
            }
        });

        getScene().addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            if (!isResizing) {
                return;
            }
            if (resizeDirection == ResizeDirection.NORTH || resizeDirection == ResizeDirection.NORTH_WEST || resizeDirection == ResizeDirection.NORTH_EAST) {
                val newHeight = ensureStageHeightIsWithinLimits(resizeStartStageHeight[0] + (resizeStartFromScreenY[0] - event.getScreenY()));
                val newY = newHeight == this.getMaxHeight() || newHeight == this.getMinHeight() ? this.getY() : event.getScreenY() - resizeStartFromSceneY[0];

                this.setHeight(newHeight);
                this.setY(newY);
            }

            if (resizeDirection == ResizeDirection.SOUTH || resizeDirection == ResizeDirection.SOUTH_WEST || resizeDirection == ResizeDirection.SOUTH_EAST) {
                double newHeight = ensureStageHeightIsWithinLimits(resizeStartStageHeight[0] + (event.getScreenY() - resizeStartFromScreenY[0]));

                this.setHeight(newHeight);
            }

            if (resizeDirection == ResizeDirection.WEST || resizeDirection == ResizeDirection.NORTH_WEST || resizeDirection == ResizeDirection.SOUTH_WEST) {
                double newWidth = ensureStageWidthIsWithinLimits(resizeStartStageWidth[0] + (resizeStartFromScreenX[0] - event.getScreenX()));
                double newX = newWidth == this.getMaxWidth() || newWidth == this.getMinWidth() ? this.getX() : event.getScreenX() - resizeStartFromSceneX[0];

                this.setWidth(newWidth);
                this.setX(newX);
            }

            if (resizeDirection == ResizeDirection.EAST || resizeDirection == ResizeDirection.NORTH_EAST || resizeDirection == ResizeDirection.SOUTH_EAST) {
                double newWidth = ensureStageWidthIsWithinLimits(resizeStartStageWidth[0] + (event.getScreenX() - resizeStartFromScreenX[0]));

                this.setWidth(newWidth);
            }
        });

        getScene().addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if (isResizing) {
                isResizing = false;
                isDraggable = true;
                isFullscreenable = true;
            }
        });

        return this;
    }

    private void makeResizeable() {
        makeResizable(10.0, 10.0, 10.0, 10.0);
    }

    private @Nullable ResizeDirection detectResizeDirection(@NotNull MouseEvent event) {
        boolean isNorthResize = event.getSceneY() <= resizeMarginTop;
        boolean isSouthResize = getScene().getHeight() - event.getSceneY() <= resizeMarginBottom;
        boolean isWestResize = event.getSceneX() <= resizeMarginLeft;
        boolean isEastResize = getScene().getWidth() - event.getSceneX() <= resizeMarginRight;
        boolean isNorthWestResize = isNorthResize && isWestResize;
        boolean isNorthEastResize = isNorthResize && isEastResize;
        boolean isSouthWestResize = isSouthResize && isWestResize;
        boolean isSouthEastResize = isSouthResize && isEastResize;

        if (isNorthWestResize) {
            return ResizeDirection.NORTH_WEST;
        } else if (isNorthEastResize) {
            return ResizeDirection.NORTH_EAST;
        } else if (isSouthWestResize) {
            return ResizeDirection.SOUTH_WEST;
        } else if (isSouthEastResize) {
            return ResizeDirection.SOUTH_EAST;
        } else if (isNorthResize) {
            return ResizeDirection.NORTH;
        } else if (isSouthResize) {
            return ResizeDirection.SOUTH;
        } else if (isWestResize) {
            return ResizeDirection.WEST;
        } else if (isEastResize) {
            return ResizeDirection.EAST;
        } else {
            return null;
        }
    }

    private double ensureStageWidthIsWithinLimits(double width) {
        val screen = Screen.getPrimary();

        if (width > this.getMaxWidth()) {
            return this.getMaxWidth();
        } else if (width < this.getMinWidth()) {
            return this.getMinWidth();
        } else return Math.min(width, screen.getVisualBounds().getWidth());
    }

    private double ensureStageHeightIsWithinLimits(double height) {
        val screen = Screen.getPrimary();

        if (height > this.getMaxHeight()) {
            return this.getMaxHeight();
        } else if (height < this.getMinHeight()) {
            return this.getMinHeight();
        } else return Math.min(height, screen.getVisualBounds().getHeight());
    }

}

