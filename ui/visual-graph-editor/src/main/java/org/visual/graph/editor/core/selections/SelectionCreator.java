/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.graph.editor.core.selections;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import lombok.val;
import org.eclipse.emf.ecore.EObject;
import org.jetbrains.annotations.NotNull;
import org.visual.graph.editor.api.*;
import org.visual.graph.editor.api.utils.GraphEventManager;
import org.visual.graph.editor.api.utils.GraphInputGesture;
import org.visual.graph.editor.core.utils.EventUtils;
import org.visual.graph.editor.core.view.GraphEditorView;
import org.visual.graph.editor.model.*;

/**
 * Responsible for creating selections of nodes, connections, and joints in the graph editor.
 *
 * <p>Nodes can currently be selected by clicking on them. Additionally, one or more nodes,
 * connections, and joints can be selected by dragging a box around them.
 */
public class SelectionCreator {

  private final SkinLookup skinLookup;
  private final GraphEditorView view;
  private final SelectionDragManager selectionDragManager;
  private final SelectionManager selectionManager;

  private GModel model;

  private final Map<Node, EventHandler<MouseEvent>> mousePressedHandlers =
      new Object2ObjectArrayMap<>();
  private final Map<Node, EventHandler<MouseEvent>> mouseClickedHandlers =
      new Object2ObjectArrayMap<>();

  private final EventHandler<MouseEvent> viewPressedHandler = this::handleViewPressed;
  private final EventHandler<MouseEvent> viewDraggedHandler = this::handleViewDragged;
  private final EventHandler<MouseEvent> viewReleasedHandler = this::handleViewReleased;

  private final Set<EObject> selectedElementsBackup = new HashSet<>();

  private Rectangle2D selection;

  private Point2D selectionBoxStart;
  private Point2D selectionBoxEnd;

  /**
   * Creates a new selection creator instance. Only one instance should exist per {@link
   * DefaultGraphEditor} instance.
   *
   * @param pSkinLookup the {@link SkinLookup} used to look up skins
   * @param pView the {@link GraphEditorView} instance
   * @param pSelectionDragManager the {@link SelectionDragManager} instance for this graph editor
   */
  public SelectionCreator(
      final SkinLookup pSkinLookup,
      final @NotNull GraphEditorView pView,
      final SelectionManager pSelectionManager,
      final SelectionDragManager pSelectionDragManager) {
    selectionManager = pSelectionManager;
    skinLookup = pSkinLookup;
    view = pView;
    selectionDragManager = pSelectionDragManager;

    pView.addEventHandler(MouseEvent.MOUSE_PRESSED, new WeakEventHandler<>(viewPressedHandler));
    pView.addEventHandler(MouseEvent.MOUSE_DRAGGED, new WeakEventHandler<>(viewDraggedHandler));
    pView.addEventHandler(MouseEvent.MOUSE_RELEASED, new WeakEventHandler<>(viewReleasedHandler));
  }

  /**
   * Initializes the selection creator for the current model.
   *
   * @param model the {@link GModel} currently being edited
   */
  public void initialize(final GModel model) {
    this.model = model;
    addClickSelectionMechanism();
  }

  /**
   * Adds a mechanism to select nodes by clicking on them.
   *
   * <p>Holding the <b>shortcut</b> key while clicking will add to the existing selection.
   */
  private void addClickSelectionMechanism() {
    // remove all listeners:
    EventUtils.removeEventHandlers(mousePressedHandlers, MouseEvent.MOUSE_PRESSED);
    EventUtils.removeEventHandlers(mouseClickedHandlers, MouseEvent.MOUSE_CLICKED);

    if (model != null) {
      addClickSelectionForNodes();
      addClickSelectionForJoints();
    }
  }

  private void handleSelectionClick(final @NotNull MouseEvent event, final GSkin<?> skin) {
    if (!MouseButton.PRIMARY.equals(event.getButton())) {
      return;
    }

    if (!skin.isSelected()) {
      if (!event.isShortcutDown()) {
        selectionManager.clearSelection();
      } else {
        backupSelections();
      }
      selectionManager.select(skin.getItem());
    } else {
      if (event.isShortcutDown()) {
        selectionManager.clearSelection(skin.getItem());
      }
    }

    // Consume this event so it's not passed up to the parent (i.e. the view).
    event.consume();
  }

  public void addNode(final GNode node) {
    val skin = skinLookup.lookupNode(node);
    if (skin == null) {
      return;
    }
    final Region nodeRegion = skin.getRoot();

    if (!mousePressedHandlers.containsKey(nodeRegion)) {
      final EventHandler<MouseEvent> newNodePressedHandler =
          event -> handleNodePressed(event, skin);
      nodeRegion.addEventHandler(MouseEvent.MOUSE_PRESSED, newNodePressedHandler);
      mousePressedHandlers.put(nodeRegion, newNodePressedHandler);
    }

    node.getConnectors().forEach(this::addConnector);
  }

  public void removeNode(final GNode node) {
    val skin = skinLookup.lookupNode(node);
    if (skin == null) {
      return;
    }
    final Region nodeRegion = skin.getRoot();

    final EventHandler<MouseEvent> newNodePressedHandler = mousePressedHandlers.remove(nodeRegion);

    if (newNodePressedHandler != null) {
      nodeRegion.removeEventHandler(MouseEvent.MOUSE_PRESSED, newNodePressedHandler);
    }

    node.getConnectors().forEach(this::removeConnector);
  }

  public void addConnector(final GConnector connector) {
    final GConnectorSkin connectorSkin = skinLookup.lookupConnector(connector);
    if (connectorSkin == null) {
      return;
    }
    final Node connectorRoot = connectorSkin.getRoot();

    if (!mouseClickedHandlers.containsKey(connectorRoot)) {
      final EventHandler<MouseEvent> connectorClickedHandler =
          event -> handleSelectionClick(event, connectorSkin);
      connectorRoot.addEventHandler(MouseEvent.MOUSE_CLICKED, connectorClickedHandler);
      mouseClickedHandlers.put(connectorRoot, connectorClickedHandler);
    }
  }

  public void removeConnector(final GConnector connector) {
    val connectorSkin = skinLookup.lookupConnector(connector);
    if (connectorSkin == null) {
      return;
    }
    final Node connectorRoot = connectorSkin.getRoot();
    final EventHandler<MouseEvent> connectorClickedHandler =
        mouseClickedHandlers.remove(connectorRoot);
    if (connectorClickedHandler != null) {
      connectorRoot.removeEventHandler(MouseEvent.MOUSE_CLICKED, connectorClickedHandler);
    }
  }

  public void addConnection(final GConnection connection) {
    val connSkin = skinLookup.lookupConnection(connection);
    if (connSkin != null) {

      final Node skinRoot = connSkin.getRoot();
      if (!mousePressedHandlers.containsKey(skinRoot)) {
        final EventHandler<MouseEvent> connectionPressedHandler =
            event -> handleConnectionPressed(event, connection);
        skinRoot.addEventHandler(MouseEvent.MOUSE_PRESSED, connectionPressedHandler);
        mousePressedHandlers.put(skinRoot, connectionPressedHandler);
      }
    }

    connection.getJoints().forEach(this::addJoint);
  }

  public void removeConnection(final GConnection connection) {
    val connSkin = skinLookup.lookupConnection(connection);
    if (connSkin != null) {

      final EventHandler<MouseEvent> connectionPressedHandler =
          mousePressedHandlers.remove(connSkin.getRoot());
      if (connectionPressedHandler != null) {
        connSkin.getRoot().removeEventHandler(MouseEvent.MOUSE_PRESSED, connectionPressedHandler);
      }
    }

    connection.getJoints().forEach(this::removeJoint);
  }

  public void addJoint(final GJoint joint) {
    val jointSkin = skinLookup.lookupJoint(joint);
    if (jointSkin == null) {
      return;
    }

    final Region jointRegion = jointSkin.getRoot();

    if (!mousePressedHandlers.containsKey(jointRegion)) {

      final EventHandler<MouseEvent> jointPressedHandler =
          event -> handleJointPressed(event, jointSkin);
      jointRegion.addEventHandler(MouseEvent.MOUSE_PRESSED, jointPressedHandler);
      mousePressedHandlers.put(jointRegion, jointPressedHandler);
    }
  }

  public void removeJoint(final GJoint joint) {
    final GJointSkin jointSkin = skinLookup.lookupJoint(joint);
    if (jointSkin == null) {
      return;
    }

    final Region jointRegion = jointSkin.getRoot();

    final EventHandler<MouseEvent> jointPressedHandler = mousePressedHandlers.remove(jointRegion);

    if (jointPressedHandler != null) {
      jointRegion.removeEventHandler(MouseEvent.MOUSE_PRESSED, jointPressedHandler);
    }
  }

  /** Adds a click selection mechanism for nodes. */
  private void addClickSelectionForNodes() {
    model.getNodes().forEach(this::addNode);
  }

  /** Adds a click selection mechanism for joints. */
  private void addClickSelectionForJoints() {
    model.getConnections().forEach(this::addConnection);
  }

  /**
   * Handles mouse-pressed events on the given node.
   *
   * @param event a mouse-pressed event
   * @param nodeSkin the {@link GNodeSkin} on which this event occurred
   */
  private void handleNodePressed(final @NotNull MouseEvent event, final GNodeSkin nodeSkin) {
    if (!MouseButton.PRIMARY.equals(event.getButton())) {
      return;
    }

    // first update the selection:
    handleSelectionClick(event, nodeSkin);

    // Do not bind the positions of other selected nodes if this node is about to be resized.
    if (Boolean.FALSE.equals(nodeSkin.getRoot().isMouseInPositionForResize())) {
      selectionDragManager.bindPositions(nodeSkin.getRoot());
    }

    // Consume this event so it's not passed up to the parent (i.e. the view).
    event.consume();
  }

  /**
   * Handles mouse-pressed events on the given connection.
   *
   * @param event a mouse-pressed event
   * @param connection the {@link GConnection} on which this event occurred
   */
  private void handleConnectionPressed(
      final @NotNull MouseEvent event, final GConnection connection) {
    if (!MouseButton.PRIMARY.equals(event.getButton())) {
      return;
    }

    val connSkin = skinLookup.lookupConnection(connection);
    if (connSkin != null) {
      handleSelectionClick(event, connSkin);
    }

    event.consume();
  }

  /**
   * Handles mouse-pressed events on the given joint.
   *
   * @param event a mouse-pressed event
   * @param jointSkin the {@link GJointSkin} on which this event occurred
   */
  private void handleJointPressed(final @NotNull MouseEvent event, final GJointSkin jointSkin) {
    if (Boolean.FALSE.equals(MouseButton.PRIMARY.equals(event.getButton())) || event.isConsumed()) {
      return;
    }

    // first update the selection:
    handleSelectionClick(event, jointSkin);

    // then bind position of other slave items:
    selectionDragManager.bindPositions(jointSkin.getRoot());

    event.consume();
  }

  /**
   * Handles mouse-pressed events on the view.
   *
   * @param pEvent a mouse-pressed event
   */
  private void handleViewPressed(final MouseEvent pEvent) {
    if (model == null || pEvent.isConsumed() || !activateGesture(pEvent)) {
      return;
    }

    if (!pEvent.isShortcutDown()) {
      selectionManager.clearSelection();
    } else {
      backupSelections();
    }

    selectionBoxStart = new Point2D(Math.max(0, pEvent.getX()), Math.max(0, pEvent.getY()));
  }

  /**
   * Handles mouse-dragged events on the view.
   *
   * @param pEvent a mouse-dragged event
   */
  private void handleViewDragged(final MouseEvent pEvent) {
    if (model == null
        || pEvent.isConsumed()
        || selectionBoxStart == null
        || !activateGesture(pEvent)) {
      return;
    }

    selectionBoxEnd =
        new Point2D(
            Math.min(model.getContentWidth(), Math.max(0, pEvent.getX())),
            Math.min(model.getContentHeight(), Math.max(0, pEvent.getY())));

    evaluateSelectionBoxParameters();

    view.drawSelectionBox(
        selection.getMinX(), selection.getMinY(), selection.getWidth(), selection.getHeight());
    updateSelection(pEvent.isShortcutDown());
  }

  /**
   * Handles mouse-released events on the view.
   *
   * @param event a mouse-released event
   */
  private void handleViewReleased(final MouseEvent event) {
    selectionBoxStart = null;
    if (finishGesture()) {
      event.consume();
    }
    view.hideSelectionBox();
  }

  private boolean isNodeSelected(final @NotNull GNode node, final boolean isShortcutDown) {
    return selection.contains(node.getX(), node.getY(), node.getWidth(), node.getHeight())
        || isShortcutDown && selectedElementsBackup.contains(node);
  }

  private boolean isJointSelected(final @NotNull GJoint joint, final boolean isShortcutDown) {
    return selection.contains(joint.getX(), joint.getY())
        || isShortcutDown && selectedElementsBackup.contains(joint);
  }

  private boolean isConnectionSelected(final GConnection connection, final boolean isShortcutDown) {
    return isShortcutDown && selectedElementsBackup.contains(connection);
  }

  /**
   * Updates the selection according to what nodes & joints are inside / outside the selection box.
   */
  private void updateSelection(final boolean isShortcutDown) {
    model
        .getNodes()
        .forEach(
            node -> {
              if (isNodeSelected(node, isShortcutDown)) {
                selectionManager.select(node);
              } else {
                selectionManager.clearSelection(node);
              }
            });

    model
        .getConnections()
        .forEach(
            connection -> {
              if (isConnectionSelected(connection, isShortcutDown)) {
                selectionManager.select(connection);
              } else {
                selectionManager.clearSelection(connection);
              }
              connection
                  .getJoints()
                  .forEach(
                      joint -> {
                        if (isJointSelected(joint, isShortcutDown)) {
                          selectionManager.select(joint);
                        } else {
                          selectionManager.clearSelection(joint);
                        }
                      });
            });
  }

  /**
   * Stores the currently selected objects in this class' backup lists.
   *
   * <p>This is used to add to an existing selection when holding the shortcut key (e.g. Ctrl in
   * Windows).
   *
   * <p>
   */
  private void backupSelections() {
    selectedElementsBackup.clear();
    selectedElementsBackup.addAll(selectionManager.getSelectedItems());
  }

  /** Updates the current selection box values based on the cursor start- and endpoints. */
  private void evaluateSelectionBoxParameters() {
    val x = Math.min(selectionBoxStart.getX(), selectionBoxEnd.getX());
    val y = Math.min(selectionBoxStart.getY(), selectionBoxEnd.getY());

    val width = Math.abs(selectionBoxStart.getX() - selectionBoxEnd.getX());
    val height = Math.abs(selectionBoxStart.getY() - selectionBoxEnd.getY());

    selection = new Rectangle2D(x, y, width, height);
  }

  private boolean activateGesture(final Event pEvent) {
    final GraphEventManager eventManager = view.getEditorProperties();
    if (eventManager == null) {
      return true;
    }
    return eventManager.activateGesture(GraphInputGesture.SELECT, pEvent, this);
  }

  private boolean finishGesture() {
    final GraphEventManager eventManager = view.getEditorProperties();
    if (eventManager == null) {
      return true;
    }
    return eventManager.finishGesture(GraphInputGesture.SELECT, this);
  }
}
