/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.model.graph.editor.core.model;

import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.graph.editor.api.EditorElement;
import org.visual.model.graph.editor.api.GJointSkin;
import org.visual.model.graph.editor.api.GNodeSkin;
import org.visual.model.graph.editor.api.SkinLookup;
import org.visual.model.graph.editor.api.utils.GraphEditorProperties;
import org.visual.model.graph.editor.core.ModelEditingManager;
import org.visual.model.graph.editor.model.GJoint;
import org.visual.model.graph.editor.model.GNode;


/**
 * Responsible for updating the {@link GModel}'s layout values at the end of
 * each mouse gesture.
 */
@Slf4j
public class ModelLayoutUpdater {

    private final SkinLookup skinLookup;
    private final ModelEditingManager modelEditingManager;
    private final GraphEditorProperties properties;
    private final EventHandler<MouseEvent> mouseReleasedHandlerNode = event -> elementMouseReleased(EditorElement.NODE);
    private final EventHandler<MouseEvent> mouseReleasedHandlerJoint = event -> elementMouseReleased(EditorElement.JOINT);

    /**
     * Creates a new model layout updater. Only one instance should exist per
     * graph editor instance.
     *
     * @param pSkinLookup          the {@link SkinLookup} used to lookup skins
     * @param pModelEditingManager the {@link ModelEditingManager} used to update the model
     *                             values
     */
    public ModelLayoutUpdater(final SkinLookup pSkinLookup, final ModelEditingManager pModelEditingManager,
                              final GraphEditorProperties pProperties) {
        skinLookup = pSkinLookup;
        modelEditingManager = pModelEditingManager;
        properties = pProperties;
    }

    /**
     * Adds a handler to update the model when a node's layout properties
     * change.
     *
     * @param node the {@link GNode} whose values should be updated
     */
    public void addNode(final GNode node) {
        final GNodeSkin nodeSkin = skinLookup.lookupNode(node);
        if (nodeSkin == null) {
            return;
        }
        final Node root = nodeSkin.getRoot();
        if (root == null) {
            return;
        }
        root.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedHandlerNode);
    }

    public void removeNode(final GNode node) {
        Optional.ofNullable(skinLookup.lookupNode(node))
                .map(gNodeSkin -> (Node) gNodeSkin.getRoot())
                .ifPresent(r -> r.removeEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedHandlerNode));
    }

    /**
     * Adds a handler to update the model when a joint's layout properties
     * change.
     *
     * @param joint the {@link GJoint} whose values should be updated
     */
    public void addJoint(final GJoint joint) {
        final GJointSkin jointSkin = skinLookup.lookupJoint(joint);
        if (jointSkin == null) {
            return;
        }
        final Node root = jointSkin.getRoot();
        if (root == null) {
            return;
        }
        root.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedHandlerJoint);
    }

    public void removeJoint(final GJoint joint) {
        final GJointSkin jointSkin = skinLookup.lookupJoint(joint);
        if (jointSkin != null) {
            final Node root = jointSkin.getRoot();
            if (root != null) {
                root.removeEventHandler(MouseEvent.MOUSE_RELEASED, mouseReleasedHandlerJoint);
            }
        }
    }

    private void elementMouseReleased(final EditorElement pType) {
        if (canEdit(pType)) {
            modelEditingManager.updateLayoutValues(skinLookup);
        }
    }

    private boolean canEdit(final EditorElement pType) {
        final GraphEditorProperties props = properties;
        return props != null && !props.isReadOnly(pType);
    }
}
