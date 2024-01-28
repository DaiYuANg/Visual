/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.visual.graph.editor.api;

import javafx.util.Callback;
import org.visual.graph.editor.model.GConnection;
import org.visual.graph.editor.model.GConnector;
import org.visual.graph.editor.model.GJoint;
import org.visual.graph.editor.model.GNode;

/**
 * Provides functionality for customizing the display of the graph elements.
 *
 * @author eckig
 */
public interface GraphEditorSkins {

  /**
   * Sets the custom node skin factory.
   *
   * @param nodeSkinFactory factory for creating the skins
   */
  public void setNodeSkinFactory(final Callback<GNode, GNodeSkin> nodeSkinFactory);

  /**
   * Sets the custom connector skin factory.
   *
   * @param connectorSkinFactory factory for creating the skins
   */
  public void setConnectorSkinFactory(
      final Callback<GConnector, GConnectorSkin> connectorSkinFactory);

  /**
   * Sets the custom connection skin factory.
   *
   * @param connectionSkinFactory factory for creating the skins
   */
  public void setConnectionSkinFactory(
      final Callback<GConnection, GConnectionSkin> connectionSkinFactory);

  /**
   * Sets the custom joint skin factory.
   *
   * @param jointSkinFactory factory for creating the skins
   */
  public void setJointSkinFactory(final Callback<GJoint, GJointSkin> jointSkinFactory);

  /**
   * Sets the custom tail skin factory.
   *
   * @param tailSkinFactory factory for creating the skins
   */
  public void setTailSkinFactory(final Callback<GConnector, GTailSkin> tailSkinFactory);
}
