/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.editor.api;


import org.visual.data.structure.graph.Model;

/**
 * Responsible for telling connection skins to draw themselves.
 */
public interface ConnectionLayouter
{

    void initialize(final Model model);

    void draw();
}
