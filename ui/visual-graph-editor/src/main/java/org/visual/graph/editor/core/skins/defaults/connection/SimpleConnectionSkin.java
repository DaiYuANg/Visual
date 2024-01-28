/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.graph.editor.core.skins.defaults.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import kotlin.NotImplementedError;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.graph.editor.core.connections.RectangularConnections;
import org.visual.graph.editor.api.GConnectionSkin;
import org.visual.graph.editor.api.GJointSkin;
import org.visual.graph.editor.api.utils.DraggableBox;
import org.visual.graph.editor.api.utils.GeometryUtils;
import org.visual.graph.editor.core.skins.defaults.connection.segment.ConnectionSegment;
import org.visual.graph.editor.core.skins.defaults.connection.segment.DetouredConnectionSegment;
import org.visual.graph.editor.core.skins.defaults.connection.segment.GappedConnectionSegment;
import org.visual.graph.editor.model.GConnection;

/**
 * A simple rectangular connection skin.
 *
 * <p>
 * Shows a rectangular connection shape based on the positions of its joints. Shows a graphical effect at points where
 * the connection intersects other connections.
 * </p>
 */
public class SimpleConnectionSkin extends GConnectionSkin {

    /**
     * Property key to show detours at intersections.
     *
     * <p>
     * By default small gaps are drawn points where the connection passes <b>under</b> other connections. However it is
     * also possible to draw detours (small semicircles) at points where the connection passes <b>over</b> others.
     * </p>
     *
     * <p>
     * To activate this functionality, add this key to the graph editor's custom properties with the value "true". Do
     * <b>NOT</b> mix 'detoured' and 'gapped' connection skins in the same graph, it will look bad.
     * </p>
     */
    public static final String SHOW_DETOURS_KEY = "default-connection-skin-show-detours";

    protected final Group root = new Group();
    protected final Path path = new Path();
    protected final Path backgroundPath = new Path();

    protected final List<ConnectionSegment> connectionSegments = new ArrayList<>();

    private static final String STYLE_CLASS = "default-connection";
    private static final String STYLE_CLASS_BACKGROUND = "default-connection-background";

    private List<GJointSkin> jointSkins;

    /**
     * Creates a new simple connection skin instance.
     *
     * @param connection the {@link GConnection} the skin is being created for
     */
    public SimpleConnectionSkin(final GConnection connection) {

        super(connection);

        root.setManaged(false);

        // Background path is invisible and used only to capture hover events.
        root.getChildren().add(backgroundPath);
        root.getChildren().add(path);

        path.setMouseTransparent(true);

        backgroundPath.getStyleClass().setAll(STYLE_CLASS_BACKGROUND);
        path.getStyleClass().setAll(STYLE_CLASS);
    }

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public void setJointSkins(final List<GJointSkin> jointSkins) {

        if (this.jointSkins != null) {
            removeOldRectangularConstraints();
        }

        this.jointSkins = jointSkins;

        addRectangularConstraints();
    }

    @Override
    public Point2D[] update() {
        final Point2D[] points = super.update();
        checkFirstAndLastJoints(points);
        return points;
    }

    @Override
    public void draw(final Map<GConnectionSkin, Point2D[]> allPoints) {
        super.draw(allPoints);

        // If we are showing detours, get all intersections with connections *behind* this one. Otherwise in front.
        final double[][] intersections = IntersectionFinder.find(this, allPoints, checkShowDetours());

        final Point2D[] points = allPoints.get(this);
        if (points != null) {
            drawAllSegments(points, intersections);
        } else {
            connectionSegments.clear();
            path.getElements().clear();
        }
    }

    /**
     * Removes the old rectangular constraints on the connection's list of joint skins.
     */
    private void removeOldRectangularConstraints() {
        IntStream.range(0, jointSkins.size() - 1).forEach(i -> {
            final DraggableBox thisJoint = jointSkins.get(i).getRoot();
            final DraggableBox nextJoint = jointSkins.get(i + 1).getRoot();
            if (RectangularConnections.isSegmentHorizontal(getItem(), i)) {
                thisJoint.bindLayoutX(null);
                nextJoint.bindLayoutX(null);
            } else {
                thisJoint.bindLayoutY(null);
                nextJoint.bindLayoutY(null);
            }
        });
    }

    /**
     * Adds constraints to the connection's joints in order to keep the connection rectangular in shape.
     */
    private void addRectangularConstraints() {
        // Our rectangular connection logic assumes an even number of joints.
        IntStream.range(0, jointSkins.size() - 1).forEach(i -> {
            final DraggableBox thisJoint = jointSkins.get(i).getRoot();
            final DraggableBox nextJoint = jointSkins.get(i + 1).getRoot();
            if (RectangularConnections.isSegmentHorizontal(getItem(), i)) {
                thisJoint.bindLayoutX(nextJoint);
                nextJoint.bindLayoutX(thisJoint);
            } else {
                thisJoint.bindLayoutY(nextJoint);
                nextJoint.bindLayoutY(thisJoint);
            }
        });
    }

    /**
     * Checks the position of the first and last joints and makes sure they are aligned with their adjacent connectors.
     *
     * @param points all points that the connection should pass through (both connector and joint positions)
     */
    private void checkFirstAndLastJoints(final Point2D[] points) {
        alignJoint(points, RectangularConnections.isSegmentHorizontal(getItem(), 0), true);
        alignJoint(points, RectangularConnections.isSegmentHorizontal(getItem(), points.length - 2), false);
    }

    /**
     * Aligns the first or last joint to have the same vertical or horizontal position as the start or end point.
     *
     * @param points   the list of points in this connection
     * @param vertical {@code true} to align in the vertical (y) direction, {@code false} for horizontal (x)
     * @param start    {@code true} to align the first joint to the start, {@code false} for the last joint to the end
     */
    private void alignJoint(final Point2D[] points, final boolean vertical, final boolean start) {
        val targetPositionIndex = start ? 0 : points.length - 1;
        val jointPositionIndex = start ? 1 : points.length - 2;
        val jointSkin = jointSkins.get(start ? 0 : jointSkins.size() - 1);

        if (vertical) {
            final double newJointY = points[targetPositionIndex].getY();
            final double newJointLayoutY = GeometryUtils.moveOnPixel(newJointY - jointSkin.getHeight() / 2);
            jointSkin.getRoot().setLayoutY(newJointLayoutY);

            final double currentX = points[jointPositionIndex].getX();
            points[jointPositionIndex] = new Point2D(currentX, newJointY);
        } else {
            final double newJointX = points[targetPositionIndex].getX();
            final double newJointLayoutX = GeometryUtils.moveOnPixel(newJointX - jointSkin.getWidth() / 2);
            jointSkin.getRoot().setLayoutX(newJointLayoutX);

            final double currentY = points[jointPositionIndex].getY();
            points[jointPositionIndex] = new Point2D(newJointX, currentY);
        }
    }

    /**
     * Draws all segments of the connection.
     *
     * @param points        all points that the connection should pass through (both connector and joint positions)
     * @param intersections all intersection-points of this connection with other connections
     */
    private void drawAllSegments(final Point2D @NotNull [] points, final double[][] intersections) {
        val startX = points[0].getX();
        val startY = points[0].getY();

        val moveTo = new MoveTo(GeometryUtils.moveOffPixel(startX), GeometryUtils.moveOffPixel(startY));

        connectionSegments.clear();
        path.getElements().clear();
        path.getElements().add(moveTo);

        for (int i = 0; i < points.length - 1; i++) {
            final Point2D start = points[i];
            final Point2D end = points[i + 1];

            val segmentIntersections = intersections != null ? intersections[i] : null;
            val segment = checkShowDetours() ?
                    new DetouredConnectionSegment(start, end, segmentIntersections) :
                    new GappedConnectionSegment(start, end, segmentIntersections);

            segment.draw();

            connectionSegments.add(segment);
            path.getElements().addAll(segment.getPathElements());
        }

        backgroundPath.getElements().clear();
        backgroundPath.getElements().addAll(path.getElements());
    }

    /**
     * Checks whether the custom property has been set to show detours instead of gaps when connections intersect.
     *
     * @return {@code true} if the custom property to show detours has been set
     */
    private boolean checkShowDetours() {
        boolean showDetours = false;

        final String value = getGraphEditor().getProperties().getCustomProperties().get(SHOW_DETOURS_KEY);
        if (Boolean.toString(true).equals(value)) {
            showDetours = true;
        }

        return showDetours;
    }

    @Override
    protected void selectionChanged(boolean isSelected) {
        throw new NotImplementedError();
    }
}