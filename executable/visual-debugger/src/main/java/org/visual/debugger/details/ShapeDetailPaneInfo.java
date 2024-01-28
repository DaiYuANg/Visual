/*
 * Scenic View,
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.visual.debugger.details;

import javafx.scene.Node;
import javafx.scene.shape.Shape;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.debugger.api.FXConnectorEventDispatcher;
import org.visual.debugger.controller.StageID;


/**
 *
 */
@Slf4j
class ShapeDetailPaneInfo extends DetailPaneInfo {

    Detail fillDetail;

    ShapeDetailPaneInfo(final FXConnectorEventDispatcher dispatcher, final StageID stageID) {
        super(dispatcher, stageID, DetailPaneType.SHAPE);
    }

    @Override
    Class<? extends Node> getTargetClass() {
        return Shape.class;
    }

    @Override
    public boolean targetMatches(final Object candidate) {
        return candidate instanceof Shape;
    }

    @Override
    protected void createDetails() {
        fillDetail = addDetail("fill", "fill:", Detail.ValueType.COLOR);
    }

    @Override
    protected void updateDetail(final @NotNull String propertyName) {
        val all = propertyName.equals("*");

        val shapeNode = (Shape) getTarget();
        if (all || propertyName.equals("fill")) {
            if (shapeNode != null && shapeNode.getFill() == null) {
                log.atInfo().log("Error: null shape fill for property " + propertyName);
            }
            fillDetail.setValue(shapeNode != null && shapeNode.getFill() != null ? shapeNode.getFill().toString() : "-");
            fillDetail.setDefault(shapeNode == null || shapeNode.getFill() == null);
            fillDetail.setSimpleProperty((shapeNode != null) ? shapeNode.fillProperty() : null);
            if (!all)
                fillDetail.updated();
            if (!all)
                return;
        }
        if (all)
            sendAllDetails();
    }
}
