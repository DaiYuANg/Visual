/* (C)2024*/
package org.visual.model.designer.customskins;

import java.util.List;
import javafx.geometry.Side;
import org.visual.model.designer.customskins.tree.*;
import org.visual.model.designer.selections.SelectionCopier;
import org.visual.model.graph.editor.api.*;
import org.visual.model.graph.editor.core.skins.defaults.DefaultConnectionSkin;
import org.visual.model.graph.editor.core.skins.defaults.DefaultConnectorSkin;
import org.visual.model.graph.editor.core.skins.defaults.DefaultNodeSkin;
import org.visual.model.graph.editor.core.skins.defaults.DefaultTailSkin;
import org.visual.model.graph.editor.core.view.GraphEditorContainer;
import org.visual.model.graph.editor.model.GConnection;
import org.visual.model.graph.editor.model.GConnector;
import org.visual.model.graph.editor.model.GNode;
import org.visual.model.graph.editor.model.GraphFactory;

/**
 * Responsible for tree-skin specific logic in the graph editor demo.
 */
public class TreeSkinController implements SkinController {

    protected static final int TREE_NODE_INITIAL_Y = 19;

    private final GraphEditor graphEditor;
    private final GraphEditorContainer graphEditorContainer;

    /**
     * Creates a new {@link TreeSkinController} instance.
     *
     * @param graphEditor the graph editor on display in this demo
     * @param graphEditorContainer the graph editor container on display in this demo
     */
    public TreeSkinController(final GraphEditor graphEditor, final GraphEditorContainer graphEditorContainer) {

        this.graphEditor = graphEditor;
        this.graphEditorContainer = graphEditorContainer;
    }

    @Override
    public void activate() {
        graphEditor.setNodeSkinFactory(this::createSkin);
        graphEditor.setConnectorSkinFactory(this::createSkin);
        graphEditor.setConnectionSkinFactory(this::createSkin);
        graphEditor.setTailSkinFactory(this::createTailSkin);
        graphEditorContainer.getMinimap().setConnectionFilter(c -> false);
    }

    private GNodeSkin createSkin(final GNode node) {
        return TreeSkinConstants.TREE_NODE.equals(node.getType()) ? new TreeNodeSkin(node) : new DefaultNodeSkin(node);
    }

    private GConnectionSkin createSkin(final GConnection connection) {
        return TreeSkinConstants.TREE_CONNECTION.equals(connection.getType())
                ? new TreeConnectionSkin(connection)
                : new DefaultConnectionSkin(connection);
    }

    private GConnectorSkin createSkin(final GConnector connector) {
        return TreeSkinConstants.TREE_INPUT_CONNECTOR.equals(connector.getType())
                        || TreeSkinConstants.TREE_OUTPUT_CONNECTOR.equals(connector.getType())
                ? new TreeConnectorSkin(connector)
                : new DefaultConnectorSkin(connector);
    }

    private GTailSkin createTailSkin(final GConnector connector) {
        return TreeSkinConstants.TREE_INPUT_CONNECTOR.equals(connector.getType())
                        || TreeSkinConstants.TREE_OUTPUT_CONNECTOR.equals(connector.getType())
                ? new TreeTailSkin(connector)
                : new DefaultTailSkin(connector);
    }

    @Override
    public void addNode(final double currentZoomFactor) {

        final double windowXOffset = graphEditorContainer.getContentX() / currentZoomFactor;
        final double windowYOffset = graphEditorContainer.getContentY() / currentZoomFactor;

        final GNode node = GraphFactory.eINSTANCE.createGNode();
        node.setY(TREE_NODE_INITIAL_Y + windowYOffset);

        final GConnector output = GraphFactory.eINSTANCE.createGConnector();
        node.getConnectors().add(output);

        final double initialX = graphEditorContainer.getWidth() / (2 * currentZoomFactor) - node.getWidth() / 2;
        node.setX(Math.floor(initialX) + windowXOffset);

        node.setType(TreeSkinConstants.TREE_NODE);
        output.setType(TreeSkinConstants.TREE_OUTPUT_CONNECTOR);

        // This allows multiple connections to be created from the output.
        output.setConnectionDetachedOnDrag(false);

        Commands.addNode(graphEditor.getModel(), node);
    }

    @Override
    public void addConnector(final Side position, final boolean input) {
        // Not implemented for tree nodes.
    }

    @Override
    public void clearConnectors() {
        // Not implemented for tree nodes.
    }

    @Override
    public void handlePaste(final SelectionCopier selectionCopier) {
        selectionCopier.paste((nodes, command) -> selectReferencedConnections(nodes));
    }

    @Override
    public void handleSelectAll() {
        graphEditor.getSelectionManager().selectAll();
    }

    /**
     * Selects all connections that are referenced (i.e. connected to) the given nodes.
     *
     * @param nodes a list of graph nodes
     */
    private void selectReferencedConnections(final List<GNode> nodes) {

        nodes.stream()
                .flatMap(node -> node.getConnectors().stream())
                .flatMap(connector -> connector.getConnections().stream())
                .forEach(graphEditor.getSelectionManager()::select);
    }
}
