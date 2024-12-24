/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.editor;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class PanningWindow extends Region {

    private static final float SCALE_MIN = 0.5f;
    private static final float SCALE_MAX = 1.5f;

    private Region content;

    private final DoubleProperty contentX = new SimpleDoubleProperty() {

        @Override
        protected void invalidated() {
            requestLayout();
        }
    };
    private final DoubleProperty contentY = new SimpleDoubleProperty() {

        @Override
        protected void invalidated() {
            requestLayout();
        }
    };
    private final ScrollBar scrollX = new ScrollBar();
    private final ScrollBar scrollY = new ScrollBar();

    private final EventHandler<MouseEvent> mousePressedHandler = this::handlePanningMousePressed;
    private final EventHandler<MouseEvent> mouseDraggedHandler = this::handlePanningMouseDragged;
    private final EventHandler<MouseEvent> mouseReleasedHandler = this::handlePanningMouseReleased;

    private final EventHandler<TouchEvent> touchPressedHandler = this::handlePanningTouchPressed;
    private final EventHandler<TouchEvent> touchDraggedHandler = this::handlePanningTouchDragged;
    private final EventHandler<TouchEvent> touchReleasedHandler = this::handlePanningFinished;

    private final EventHandler<ZoomEvent> zoomHandler = this::handleZoom;
    private final EventHandler<ScrollEvent> scrollHandler = this::handleScroll;

    private Point2D clickPosition;
    private Point2D windowPosAtClick;

    private final DoubleProperty zoom = new SimpleDoubleProperty(1);
    private final Scale scale = new Scale();

//    private GraphEditorProperties properties;

    public PanningWindow() {
        val clip = new Rectangle();
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());
        setClip(clip);

        scale.xProperty().bind(zoom);
        scale.yProperty().bind(zoom);

        getChildren().addAll(scrollX, scrollY);

        scrollX.setOrientation(Orientation.HORIZONTAL);
        scrollX.valueProperty().bindBidirectional(contentX);
        scrollX.getStyleClass().add("graph-editor-scroll-bar"); //$NON-NLS-1$

        scrollY.valueProperty().bindBidirectional(contentY);
        scrollY.setOrientation(Orientation.VERTICAL);
        scrollY.getStyleClass().add("graph-editor-scroll-bar"); //$NON-NLS-1$
    }

    public void setEditorProperties(
//      final GraphEditorProperties pEditorProperties
    ) {
//        properties = pEditorProperties;
    }

    public void panTo(final double x, final double y) {
        panToX(x);
        panToY(y);
    }


    private boolean canNotPanX() {
        return content == null || content.getWidth() < getWidth();
    }

    private boolean canNotPanY() {
        return content == null || content.getHeight() < getHeight();
    }

    public void panToX(final double x) {
        if (canNotPanX()) {
            return;
        }

        final double newX = checkContentX(x);
        if (newX != getContentX()) {
            contentX.set(newX);
        }
    }

    public void panToY(final double y) {
        if (canNotPanY()) {
            return;
        }

        final double newY = checkContentY(y);
        if (newY != contentY.get()) {
            contentY.set(newY);
        }
    }

    public void panTo(final @NotNull Pos position) {
        double x = 0;
        double y = 0;

        switch (position.getHpos()) {
            case LEFT -> x = 0;
            case CENTER -> x = (content.getWidth() - getWidth()) / 2;
            case RIGHT -> x = content.getWidth() - getWidth();
            default -> {
            }
        }

        switch (position.getVpos()) {
            case TOP -> y = 0;
            case CENTER -> y = (content.getHeight() - getHeight()) / 2;
            case BOTTOM -> y = content.getHeight() - getHeight();
            default -> {
            }
        }

        contentX.set(x);
        contentY.set(y);

        checkWindowBounds();
    }

    /**
     * @return the x coordinate of the window relative to the top-left corner of
     *         the content.
     */
    public double getContentX() {
        return contentX.get();
    }

    /**
     * @return the y coordinate of the window relative to the top-left corner of
     *         the content.
     */
    public double getContentY() {
        return contentY.get();
    }

    /**
     * @return {@link DoubleProperty} with the current zoom level
     * @since 14.11.2018
     */
    public DoubleProperty zoomProperty() {
        return zoom;
    }

    /**
     * Set new zoom level without moving the viewport
     *
     * @param pZoom
     *            new zoom factor
     */
    public void setZoom(final double pZoom) {
        setZoomAt(pZoom, getContentX(), getContentY());
    }

    /**
     * Zoom at the given location
     *
     * @param pZoom
     *            new zoom factor
     * @param pPivotX
     *            the X coordinate about which point the scale occurs
     * @param pPivotY
     *            the Y coordinate about which point the scale occurs
     */
    public void setZoomAt(final double pZoom, final double pPivotX, final double pPivotY) {
        final double oldZoomLevel = getZoom();
        final double newZoomLevel = constrainZoom(pZoom);

        if (newZoomLevel != oldZoomLevel) {
            final double f = newZoomLevel / oldZoomLevel - 1;
            zoom.set(newZoomLevel);
            panTo(getContentX() + f * pPivotX, getContentY() + f * pPivotY);
        }
    }

    /**
     * @return current zoom factor
     */
    public double getZoom() {
        return zoom.get();
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        final double height = getHeight();
        final double width = getWidth();
        final Node theContent = content;

        if (theContent != null) {
            theContent.relocate(-contentX.get(), -contentY.get());
        }

        val w = scrollY.getWidth();
        val h = scrollX.getHeight();

        scrollX.resizeRelocate(0, snapPositionY(height - h), snapSizeX(width - w), h);
        scrollY.resizeRelocate(snapPositionX(width - w), 0, w, snapSizeY(height - h));

        final double zoomFactor = theContent == null ? 1 : theContent.getLocalToSceneTransform().getMxx();
        scrollX.setMin(0);
        scrollX.setMax(getMaxX());
        scrollX.setVisibleAmount(zoomFactor * width);
        scrollY.setMin(0);
        scrollY.setMax(getMaxY());
        scrollY.setVisibleAmount(zoomFactor * height);
    }

    private static double constrainZoom(final double pZoom) {
        final double zoom = Math.round(pZoom * 100.0) / 100.0;
        if (zoom <= 1.02 && zoom >= 0.98) {
            return 1.0;
        }
        return Math.min(Math.max(zoom, SCALE_MIN), SCALE_MAX);
    }

    /**
     * Checks that the window bounds are completely inside the content bounds,
     * and repositions if necessary.
     *
     * <p>
     * Also checks that the window position values are integers to ensure
     * strokes are drawn cleanly.
     * </p>
     */
    protected void checkWindowBounds() {
        panTo(getContentX(), getContentY());
    }

    private double getMaxX() {
        final Region theContent = content;
        if (theContent != null) {
            final double zoomFactor = theContent.getLocalToSceneTransform().getMxx();
            return zoomFactor * theContent.getWidth() - getWidth();
        }
        return 0;
    }

    private double getMaxY() {
        final Region theContent = content;
        if (theContent != null) {
            final double zoomFactor = theContent.getLocalToSceneTransform().getMxx();
            return zoomFactor * theContent.getHeight() - getHeight();
        }
        return 0;
    }

    private double checkContentX(final double xToCheck) {
        return snapPositionX(Math.min(getMaxX(), Math.max(xToCheck, 0)));
    }

    private double checkContentY(final double yToCheck) {
        return snapPositionY(Math.min(getMaxY(), Math.max(yToCheck, 0)));
    }

    @Override
    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }

    /**
     * Sets the content of the panning window.
     *
     * <p>
     * Note that the content's {@code managed} attribute will be set to false.
     * Its size must therefore be set manually using the {@code resize()} method
     * of the {@link Node} class.
     * </p>
     *
     * @param pContent
     *            the {@link Region} to be displayed inside the panning window
     */
    protected void setContent(final Region pContent) {
        // Remove children and release bindings from old content, if any exists.
        final Region prevContent = content;
        if (prevContent != null) {
            removeMouseHandlersFromContent(prevContent);
            getChildren().remove(prevContent);
            prevContent.getTransforms().remove(scale);
        }

        content = pContent;

        if (pContent != null) {
            pContent.setManaged(false);
            getChildren().add(pContent);
            addMouseHandlersToContent(pContent);
            pContent.getTransforms().add(scale);

            scrollX.setVisible(true);
            scrollY.setVisible(true);
        } else {
            scrollX.setVisible(false);
            scrollY.setVisible(false);
        }
    }

    private void handlePanningMousePressed(final MouseEvent event) {
        startPanning(event.getScreenX(), event.getScreenY());
//        if (properties != null && properties.activateGesture(GraphInputGesture.PAN, event, this))
//        {
//            startPanning(event.getScreenX(), event.getScreenY());
//        }
    }

    /**
     * Handle mouse released event
     *
     * @param pEvent
     *            {@link MouseEvent}
     */
    protected void handlePanningMouseReleased(final MouseEvent pEvent) {
        handlePanningFinished(pEvent);
    }

    private void handlePanningMouseDragged(final MouseEvent event) {
        if (!Cursor.MOVE.equals(getCursor())) {
            startPanning(event.getScreenX(), event.getScreenY());
        }

        final double deltaX = event.getScreenX() - clickPosition.getX();
        final double deltaY = event.getScreenY() - clickPosition.getY();

        final double newWindowX = windowPosAtClick.getX() - deltaX;
        final double newWindowY = windowPosAtClick.getY() - deltaY;

        panTo(newWindowX, newWindowY);
//        if (properties != null && properties.activateGesture(GraphInputGesture.PAN, event, this))
//        {
//
//        }
    }

    private void handlePanningFinished(final Event event) {
        setCursor(null);
        event.consume();
//        if (properties != null && properties.finishGesture(GraphInputGesture.PAN, this))
//        {
//
//        }
    }

    private void handlePanningTouchPressed(final TouchEvent event) {
        startPanning(event.getTouchPoint().getScreenX(), event.getTouchPoint().getScreenY());

//        if (properties != null && properties.activateGesture(GraphInputGesture.PAN, event, this))
//        {
//        }
    }

    private void handlePanningTouchDragged(final TouchEvent event) {
        if (!Cursor.MOVE.equals(getCursor())) {
            startPanning(event.getTouchPoint().getScreenX(), event.getTouchPoint().getScreenY());
        }

        final double deltaX = event.getTouchPoint().getScreenX() - clickPosition.getX();
        final double deltaY = event.getTouchPoint().getScreenY() - clickPosition.getY();

        final double newWindowX = windowPosAtClick.getX() - deltaX;
        final double newWindowY = windowPosAtClick.getY() - deltaY;

        panTo(newWindowX, newWindowY);
//        if (properties != null && properties.activateGesture(GraphInputGesture.PAN, event, this)) {
//
//        }
    }

    private void handleScroll(final ScrollEvent pEvent) {
        // this intended for mouse-scroll events (event direct == false)
        // the event also gets synthesized from touch events, which we want to ignore as they are handled in handleZoom()
//        if (pEvent.isDirect() || pEvent.getTouchCount() > 0 || properties == null) {
//            return;
//        }
//
//        if (properties.activateGesture(GraphInputGesture.ZOOM, pEvent, this)) {
//            try {
//                final double modifier = pEvent.getDeltaY() > 1 ? 0.06 : -0.06;
//                setZoomAt(getZoom() + modifier, pEvent.getX(), pEvent.getY());
//                pEvent.consume();
//            } finally {
//                properties.finishGesture(GraphInputGesture.ZOOM, this);
//            }
//        } else if (properties.activateGesture(GraphInputGesture.PAN, pEvent, this)) {
//            try {
//                panTo(getContentX() - pEvent.getDeltaX(), getContentY() - pEvent.getDeltaY());
//                pEvent.consume();
//            } finally {
//                properties.finishGesture(GraphInputGesture.PAN, this);
//            }
//        }
    }

    private void handleZoom(final ZoomEvent pEvent) {
//        if (properties == null) {
//            return;
//        }
//
//        if (pEvent.getEventType() == ZoomEvent.ZOOM_STARTED && properties.activateGesture(GraphInputGesture.ZOOM, pEvent, this)) {
//            pEvent.consume();
//        } else if (pEvent.getEventType() == ZoomEvent.ZOOM_FINISHED && properties.finishGesture(GraphInputGesture.ZOOM, this)) {
//            pEvent.consume();
//        } else if (pEvent.getEventType() == ZoomEvent.ZOOM && properties.activateGesture(GraphInputGesture.ZOOM, pEvent, this)) {
//            final double newZoomLevel = getZoom() * pEvent.getZoomFactor();
//            setZoomAt(newZoomLevel, pEvent.getX(), pEvent.getY());
//            pEvent.consume();
//        }
    }

    private void addMouseHandlersToContent(final Node pContent) {
        pContent.addEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedHandler);
        pContent.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDraggedHandler);

        pContent.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedHandler);
        // sometimes MOUSE_RELEASED is not delivered but the MOUSE_CLICKED..
        pContent.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseReleasedHandler);

        pContent.addEventHandler(TouchEvent.TOUCH_PRESSED, touchPressedHandler);
        pContent.addEventHandler(TouchEvent.TOUCH_MOVED, touchDraggedHandler);
        pContent.addEventHandler(TouchEvent.TOUCH_RELEASED, touchReleasedHandler);

        pContent.addEventHandler(ZoomEvent.ANY, zoomHandler);
        pContent.addEventHandler(ScrollEvent.SCROLL, scrollHandler);
    }

    private void removeMouseHandlersFromContent(final @NotNull Node pContent) {
        pContent.removeEventHandler(MouseEvent.MOUSE_PRESSED, mousePressedHandler);
        pContent.removeEventHandler(MouseEvent.MOUSE_DRAGGED, mouseDraggedHandler);

        pContent.removeEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedHandler);
        // sometimes MOUSE_RELEASED is not delivered but the MOUSE_CLICKED..
        pContent.removeEventHandler(MouseEvent.MOUSE_CLICKED, mouseReleasedHandler);

        pContent.removeEventHandler(TouchEvent.TOUCH_PRESSED, touchPressedHandler);
        pContent.removeEventHandler(TouchEvent.TOUCH_MOVED, touchDraggedHandler);
        pContent.removeEventHandler(TouchEvent.TOUCH_RELEASED, touchReleasedHandler);

        pContent.removeEventHandler(ZoomEvent.ANY, zoomHandler);
        pContent.removeEventHandler(ScrollEvent.SCROLL, scrollHandler);
    }

    private void startPanning(final double x, final double y) {
        setCursor(Cursor.MOVE);

        clickPosition = new Point2D(x, y);
        windowPosAtClick = new Point2D(getContentX(), getContentY());
    }
}