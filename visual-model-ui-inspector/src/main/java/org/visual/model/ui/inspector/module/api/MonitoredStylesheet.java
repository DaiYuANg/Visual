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
package org.visual.model.ui.inspector.module.api;

import java.nio.file.Path;

import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MonitoredStylesheet {
    private Scene scene;
    private Parent parent;
    private String originalURI;
    private Path source;

    @Override
    public String toString() {
        if (source == null) {
            return String.format("%s in [%s] is not mapped", originalURI, (parent==null)?scene:parent);
        }
        return String.format("%s in [%s] is mapped to %s", originalURI, (parent==null)?scene:parent, source);
    }
}
