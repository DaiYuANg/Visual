/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.graph.editor.api;

import java.util.List;
import javafx.geometry.Point2D;
import org.visual.graph.editor.api.utils.DraggableBox;
import org.visual.graph.editor.api.utils.ResizableBox;
import org.visual.graph.editor.model.GNode;

/**
 * The skin class for a {@link GNode}. Responsible for visualizing nodes in the graph editor.
 *
 * <p>A custom node skin must extend this class. It <b>must</b> also provide a constructor taking
 * exactly one {@link GNode} parameter.
 *
 * <p>The node skin is responsible for adding its connectors to the scene graph and laying them out.
 *
 * <p>The root JavaFX node of this skin is a {@link ResizableBox}.
 */
public abstract class GNodeSkin extends GSkin<GNode> {

  private final DraggableBox root;

  /**
   * Creates a new {@link GNodeSkin}.
   *
   * @param node the {@link GNode} represented by the skin
   */
  public GNodeSkin(final GNode node) {
    super(node);
    root = createContainer();
  }

  /**
   * Gets the root JavaFX node of the skin.
   *
   * @return a {@link ResizableBox} containing the skin's root JavaFX node
   */
  @Override
  public DraggableBox getRoot() {
    return root;
  }

  /**
   * Initializes the node skin.
   *
   * <p>The skin's layout values, e.g. its x and y position, are loaded from the {@link GNode} at
   * this point.
   */
  public void initialize() {

    getRoot().setLayoutX(getItem().getX());
    getRoot().setLayoutY(getItem().getY());

    getRoot().resize(getItem().getWidth(), getItem().getHeight());
  }

  /**
   * Sets the node's connector skins.
   *
   * <p>This will be called as the node is created, or if a connector is added or removed. The
   * connector skin's regions should be added to the scene graph.
   *
   * @param connectorSkins a list of {@link GConnectorSkin} objects for each of the node's
   *     connectors
   */
  public abstract void setConnectorSkins(List<GConnectorSkin> connectorSkins);

  /** Lays out the node's connectors. */
  public abstract void layoutConnectors();

  /**
   * Gets the position of the <b>center</b> of a connector relative to the node region.
   *
   * <p>This will be the point where a connection will attach to.
   *
   * @param connectorSkin a {@link GConnectorSkin} instance
   * @return the x and y coordinates of the connector
   */
  public abstract Point2D getConnectorPosition(GConnectorSkin connectorSkin);

  /**
   * Creates and returns the {@link DraggableBox} that serves as the root for this node skin.<br>
   * By default a {@link ResizableBox} will be created and return as most nodes will be both
   * draggable and resizable.
   *
   * @return {@link DraggableBox}
   */
  protected DraggableBox createContainer() {
    return new ResizableBox(EditorElement.NODE) {

      @Override
      protected void layoutChildren() {
        super.layoutChildren();
        layoutConnectors();
      }

      @Override
      public void positionMoved() {
        super.positionMoved();
        GNodeSkin.this.impl_positionMoved();
      }
    };
  }
}
