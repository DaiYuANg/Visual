/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.model.graph.editor.core.view;

import java.util.Objects;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Region;
import lombok.Getter;
import org.visual.model.graph.editor.api.GraphEditor;
import org.visual.model.graph.editor.api.window.AutoScrollingWindow;
import org.visual.model.graph.editor.api.window.GraphEditorMinimap;
import org.visual.model.graph.editor.model.GModel;


/**
 * A container for the graph editor.
 *
 * <p>
 * This is intended for graphs that can be larger than the space available in
 * the scene. The user can pan around by right-clicking and dragging. A minimap
 * can be shown to help with navigation.
 * </p>
 *
 * <p>
 * Example:
 *
 * <pre>
 * <code>GraphEditorContainer graphEditorContainer = new GraphEditorContainer();
 * GraphEditor graphEditor = new DefaultGraphEditor();
 *
 * graphEditorContainer.setGraphEditor(graphEditor);
 * graphEditorContainer.getMinimap().setVisible(true);</code>
 * </pre>
 * <p>
 * The graph editor container is a {@link Region} and can be added to the JavaFX
 * scene graph in the usual way.
 * </p>
 *
 * <p>
 * When a {@link GraphEditor} is set inside this container, its view becomes
 * <b>unmanaged</b> and its width and height values are set to those in the
 * {@link GModel} instance.
 * </p>
 */
public class GraphEditorContainer extends AutoScrollingWindow {

    /**
     * default view stylesheet
     */
    private static final String STYLESHEET_VIEW = Objects
            .requireNonNull(GraphEditorContainer.class.getResource("/defaults.css"))
            .toExternalForm();

    private static final double MINIMAP_INDENT = 10;

    /**
     * -- GETTER --
     * Returns the
     *
     */
    @Getter
    private final GraphEditorMinimap minimap = new GraphEditorMinimap();

    private GraphEditor graphEditor;
    private final ChangeListener<GModel> modelChangeListener = (observable, oldValue, newValue) -> modelChanged(newValue);

    /**
     * Creates a new {@link GraphEditorContainer}.
     */
    public GraphEditorContainer() {
        getChildren().add(minimap);

        minimap.setWindow(this);
        minimap.setVisible(false);
    }

    @Override
    public String getUserAgentStylesheet() {
        return STYLESHEET_VIEW;
    }

    private void modelChanged(final GModel newValue) {
        if (newValue != null) {
            graphEditor.getView().resize(newValue.getContentWidth(), newValue.getContentHeight());
        }
        checkWindowBounds();
        minimap.setModel(newValue);
    }

    /**
     * Sets the graph editor to be displayed in this container.
     *
     * @param pGraphEditor a {@link GraphEditor} instance
     */
    public void setGraphEditor(final GraphEditor pGraphEditor) {
        final GraphEditor previous = graphEditor;
        if (previous != null) {
            previous.modelProperty().removeListener(modelChangeListener);
            setEditorProperties(null);
        }

        graphEditor = pGraphEditor;

        if (pGraphEditor != null) {
            pGraphEditor.modelProperty().addListener(modelChangeListener);

            final Region view = pGraphEditor.getView();
            final GModel model = pGraphEditor.getModel();

            if (model != null) {
                view.resize(model.getContentWidth(), model.getContentHeight());
            }

            setContent(view);
            minimap.setContent(view);
            minimap.setModel(model);
            minimap.setSelectionManager(pGraphEditor.getSelectionManager());

            view.toBack();

            setEditorProperties(pGraphEditor.getProperties());
        } else {
            setEditorProperties(null);
            minimap.setContent(null);
            minimap.setModel(null);
        }
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        if (getChildren().contains(minimap)) {
            minimap.relocate(getWidth() - (minimap.getWidth() + MINIMAP_INDENT), MINIMAP_INDENT);
        }
    }
}
