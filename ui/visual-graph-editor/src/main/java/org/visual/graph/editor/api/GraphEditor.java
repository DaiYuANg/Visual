/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.graph.editor.api;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Region;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.visual.graph.editor.api.utils.GraphEditorProperties;
import org.visual.graph.editor.api.utils.RemoveContext;
import org.visual.graph.editor.model.GConnection;
import org.visual.graph.editor.model.GModel;
import org.visual.graph.editor.model.GNode;

/**
 * Provides functionality for displaying and editing graph-like diagrams in JavaFX.
 *
 * <p>Example:
 *
 * <pre>
 * <code>GModel model = GraphFactory.eINSTANCE.createGModel();
 *
 * GraphEditor graphEditor = new DefaultGraphEditor();
 * graphEditor.setModel(model);
 *
 * Region view = graphEditor.getView();</code>
 * </pre>
 *
 * The view is a {@link Region} and can be added to the JavaFX scene graph in the usual way. For
 * large graphs, the editor can be put inside a pannable container (see module core) instead.
 *
 * <p>The editor updates its underlying model via EMF commands. This means any user action should be
 * undoable. Helper methods for common operations are provided in the {@link Commands} class, such
 * as:
 *
 * <ul>
 *   <li>Add Node
 *   <li>Clear All
 *   <li>Undo
 *   <li>Redo
 * </ul>
 *
 * <p>Look and feel can be customised by setting custom skin classes. The default skins can also be
 * customised to some extent via CSS. See <b>defaults.css</b> in the core module for more
 * information.
 */
public interface GraphEditor extends GraphEditorSkins {

  /**
   * Sets a custom connector validator.
   *
   * <p>This will be used to decide which connections are allowed / forbidden during drag and drop
   * events in the editor.
   *
   * @param validator a custom validator implements {@link GConnectorValidator}, or null to use the
   *     default validator
   */
  void setConnectorValidator(final GConnectorValidator validator);

  /**
   * Sets the graph model to be edited.
   *
   * @param model the {@link GModel} to be edited
   */
  void setModel(final GModel model);

  /**
   * Gets the graph model that is currently being edited.
   *
   * @return the {@link GModel} being edited, or {@code null} if no model was ever set
   */
  GModel getModel();

  /**
   * Reloads the graph model currently being edited.
   *
   * <p><b>Note: </b><br>
   * If the model is updated via EMF commands, as is recommended, it should rarely be necessary to
   * call this method. The model will be reloaded automatically via a command-stack listener.
   */
  void reload();

  /**
   * The property containing the graph model being edited.
   *
   * @return a property containing the {@link GModel} being edited
   */
  ObjectProperty<GModel> modelProperty();

  /**
   * Gets the view where the graph is displayed and edited.
   *
   * <p>The view is a JavaFX {@link Region}. It should be added to the scene graph in the usual way.
   *
   * @return the {@link Region} where the graph is displayed and edited
   */
  Region getView();

  /**
   * Gets the properties of the editor.
   *
   * <p>This provides access to global properties such as:
   *
   * <ul>
   *   <li>Show/hide alignment grid.
   *   <li>Toggle snap-to-grid on/off.
   *   <li>Toggle editor bounds on/off.
   * </ul>
   *
   * @return an {@link GraphEditorProperties} instance containing the properties of the editor
   */
  GraphEditorProperties getProperties();

  /**
   * Gets the skin lookup.
   *
   * <p>The skin lookup is used to get any skin instance associated to a model element instance.
   *
   * @return a {@link SkinLookup} used to lookup skins
   */
  SkinLookup getSkinLookup();

  /**
   * Gets the selection manager.
   *
   * <p>The selection manager keeps track of the selected nodes, connections, etc.
   *
   * @return the {@link SelectionManager}
   */
  SelectionManager getSelectionManager();

  /**
   * Sets a method to be called when a connection is created in the editor.
   *
   * <p>This can be used to append additional commands to the one that created the connection.
   *
   * @param consumer a consumer to append additional commands
   */
  void setOnConnectionCreated(Function<GConnection, Command> consumer);

  /**
   * Sets a method to be called when a connection is removed in the editor.
   *
   * <p>This can be used to create additional commands to the one that removed the connection.
   *
   * @param pOnConnectionRemoved a {@link BiFunction} creating the additional command
   */
  void setOnConnectionRemoved(BiFunction<RemoveContext, GConnection, Command> pOnConnectionRemoved);

  /**
   * Sets a method to be called when a node is removed in the editor.
   *
   * <p>This can be used to create additional commands to the one that removed the node.
   *
   * @param pOnNodeRemoved a {@link BiFunction} creating the additional command
   */
  void setOnNodeRemoved(BiFunction<RemoveContext, GNode, Command> pOnNodeRemoved);

  /**
   * Deletes all elements that are currently selected.
   *
   * @param pItems the items to remove from the graph
   */
  void delete(Collection<EObject> pItems);
}
