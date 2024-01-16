/*
 * Scenic View,
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler, Matthieu Brouillard
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
package org.visual.model.ui.inspector.helper;

import java.util.Iterator;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Window;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class FXUtils {
    /**
     * Retrieves the parent of the given node
     *
     * @param n the node for which the parent is to be found
     * @return the found parent or null
     */
    public static Parent parentOf(Node n) {
        if (n == null) {
            return null;
        }
        val p = n.getParent();
        if (p != null) {
            return parentOf(p);
        }
        if (n instanceof Parent) {
            return (Parent) n;
        }

        return null;
    }


    /**
     * Retrieves the root window containing the given node
     *
     * @param n the node to look window for
     * @return the window the node belongs to, or null if it cannot be found
     */
    public static Window windowOf(Node n) {
        if (n == null) {
            return null;
        }

        val p = n.getParent();
        if (p != null) {
            return windowOf(p);
        }

        if (n instanceof Parent container) {
            val windows = Window.getWindows();
            return windows.stream().filter(w -> w.getScene() != null)
                    .filter(w -> container == w.getScene().getRoot()).findFirst().orElse(null);
        }

        return null;
    }

    /**
     * Retrieves the root window containing the given scene
     *
     * @param s the scene to look window for
     * @return the window the scene belongs to, or null if it cannot be found
     */
    public static Window windowOf(Scene s) {
        return Optional.ofNullable(s).map(scene -> {
            val windows = Window.getWindows();
            return windows.stream().filter(w -> s == w.getScene()).findFirst().orElse(null);
        }).orElse(null);
    }
}
