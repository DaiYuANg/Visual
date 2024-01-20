/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.model.graph.editor.api;

import javafx.collections.ObservableSet;
import org.eclipse.emf.ecore.EObject;
import org.visual.model.graph.editor.model.GConnection;
import org.visual.model.graph.editor.model.GJoint;
import org.visual.model.graph.editor.model.GNode;

import java.util.List;

/**
 * Provides actions related to selections in the graph editor.
 */
public interface SelectionManager {

    /**
     * Gets the list of currently selected nodes.
     *
     * <p>
     * This list is read-only. Nodes should be selected via {@link #select(EObject)}.
     * </p>
     *
     * @return the unmodifiable list of currently selected nodes
     */
    List<GNode> getSelectedNodes();
    
    /**
     * Gets the list of currently selected connections.
     *
     * <p>
     * This list is read-only. Connections should be selected via {@link #select(EObject)}.
     * </p>
     *
     * @return the unmodifiable list of currently selected connections
     */
    List<GConnection> getSelectedConnections();

    /**
     * Gets the list of currently selected joints.
     *
     * <p>
     * This list is read-only. Joints should be selected via {@link #select(EObject)}.
     * </p>
     *
     * @return the unmodifiable list of currently selected joints
     */
    List<GJoint> getSelectedJoints();
    
    /**
     * Convenience method to inform if the given object is currently selected. Is
     * functionally equivalent to calling
     * <code>getSelectedItems().contains(object)</code>.
     * 
     * @param object
     * @return {@code true} if the given index is selected, {@code false} otherwise.
     */
    boolean isSelected(EObject object);
    
    /**
     * Gets the {@link ObservableSet} of currently-selected items.
     *
     * <p>
     * This set is read-only. Items should be selected via {@link #select(EObject)}.
     * </p>
     *
     * @return the set of selected items
     */
    ObservableSet<EObject> getSelectedItems();
    
    /**
     * This method will attempt to select the given object.
     *
     * @param object The object to attempt to select in the underlying data model.
     */
    void select(EObject object);
    
    /**
     * Selects all selectable elements (nodes, joints, and connections) in the graph editor.
     */
    void selectAll();
    
    /**
     * This method will clear the selection of the given object.
     * If the given object is not selected, nothing will happen.
     *
     * @param object The selected item to deselect.
     */
    void clearSelection(EObject object);

    /**
     * Clears the selection, i.e. de-selects all elements.
     */
    void clearSelection();
}
