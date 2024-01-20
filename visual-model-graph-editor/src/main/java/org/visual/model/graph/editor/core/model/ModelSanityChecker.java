/*
 * Copyright (C) 2005 - 2014 by TESIS DYNAware GmbH
 */
package org.visual.model.graph.editor.core.model;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.visual.model.graph.editor.core.DefaultGraphEditor;
import org.visual.model.graph.editor.model.GConnection;
import org.visual.model.graph.editor.model.GConnector;
import org.visual.model.graph.editor.model.GModel;
import org.visual.model.graph.editor.model.GNode;


/**
 * Provides a static validation method to check a {@link GModel} instance for
 * errors.
 */
@Slf4j
public final class ModelSanityChecker {

    /**
     * Static class, private constructor.
     */
    private ModelSanityChecker() {
    }

    /**
     * Validates the given {@link GModel}.
     *
     * @param model the {@link GModel} to be validated
     * @return {@code true} if the model is valid
     */
    public static boolean validate(final GModel model) {
        return validateSizes(model) && validateReferences(model);
    }

    /**
     * Performs a basic sanity check that width and height parameters are
     * non-negative.
     *
     * @param model the {@link GModel} to be validated
     * @return {@code true} if the model width and height parameters are valid
     */
    private static boolean validateSizes(final GModel model) {
        if (model.getContentWidth() < 0 || model.getContentHeight() < 0) {
            log.error("Model contains negative width / height values.");
            return false;
        }

        return model.getNodes().stream().noneMatch(node -> node.getWidth() < 0 || node.getHeight() < 0);
    }

    /**
     * Validates that the references between connectors and their connections
     * make sense.
     *
     * @param model the {@link GModel} to be validated
     * @return {@code true} if the model references are valid
     */
    private static boolean validateReferences(final @NotNull GModel model) {
        boolean valid = true;

        for (final GConnection connection : model.getConnections()) {

            val source = connection.getSource();
            val target = connection.getTarget();

            if (source == null || target == null) {
                log.error("Connection must have non-null source and target connectors.");
                valid = false;
            } else if (!source.getConnections().contains(connection)) {
                log.error("A connector is missing a reference to its connection.");
                valid = false;
            } else if (!target.getConnections().contains(connection)) {
                log.error("A connector is missing a reference to its connection.");
                valid = false;
            }
        }

        return valid;
    }
}
