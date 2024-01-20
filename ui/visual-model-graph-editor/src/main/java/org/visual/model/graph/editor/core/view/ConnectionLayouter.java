/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.model.graph.editor.core.view;


import org.visual.model.graph.editor.model.GConnection;
import org.visual.model.graph.editor.model.GModel;

import java.util.Collection;


/**
 * Responsible for telling connection skins to draw themselves.
 */
public interface ConnectionLayouter
{

    /**
     * Initializes the connection layout manager for the given model.
     *
     * @param pModel
     *            the {@link GModel} currently being edited
     */
    void initialize(final GModel pModel);

    /**
     * Draws all connections according to the latest layout values.
     */
    void draw();

    /**
     * Marks the complete connection layout as dirty. The next layout pass will
     * re-layout all connections (during {@link #draw()}).
     *
     * @since 31.01.2019
     */
    void redrawAll();

    /**
     * Redraw the given connection according to the latest layout values.
     *
     * @param pConnection
     *            connection to redraw
     */
    void redraw(final GConnection pConnection);

    /**
     * Redraw the given connections according to the latest layout values.
     *
     * @param pConnections
     *            connections to redraw
     */
    void redraw(final Collection<GConnection> pConnections);
}
