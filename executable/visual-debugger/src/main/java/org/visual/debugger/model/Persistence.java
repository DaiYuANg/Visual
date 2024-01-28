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
package org.visual.debugger.model;

import io.avaje.inject.PreDestroy;
import java.util.*;
import java.util.prefs.Preferences;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.debugger.utils.PropertiesUtils;
import org.visual.debugger.VisualModelDebugger;


@Slf4j
public class Persistence {

    private static Properties properties;

    private final Preferences preferences = Preferences.userNodeForPackage(VisualModelDebugger.class);

    private static final Map<String, Object> persistentComponents = new HashMap<>();

    public static void loadProperties() {
        properties = PropertiesUtils.getProperties();
    }

    public static String loadProperty(final String propertyName, final String defaultValue) {
        return properties.getProperty(propertyName, defaultValue);
    }

    public static void saveProperty(final String propertyName, final String defaultValue) {
        properties.put(propertyName, defaultValue);
    }

    public static void loadProperty(final String propertyName, final Object component, final @NotNull Object defaultValue) {
        final String property = properties.getProperty(propertyName, defaultValue.toString());
        if (component instanceof CheckMenuItem c) {
            c.setSelected(Boolean.parseBoolean(property));
        }
        // We should think of a better way of doing this
        else if (component instanceof SplitPane) {
            ((SplitPane) component).setDividerPosition(0, Double.parseDouble(property));
        }
        // else if(component instanceof Control) {
        // if(propertyName.toLowerCase().indexOf("width")!=-1) {
        // ((Control) component).setPrefWidth(Double.parseDouble(property));
        // }
        // else {
        // ((Control) component).setPrefHeight(Double.parseDouble(property));
        // }
        // }
        else if (component instanceof Stage) {
            if (propertyName.toLowerCase().contains("width")) {
                ((Stage) component).setWidth(Double.parseDouble(property));
            } else {
                ((Stage) component).setHeight(Double.parseDouble(property));
            }
        }
        persistentComponents.put(propertyName, component);
    }

    public static void saveProperties() {
        persistentComponents.keySet().forEach(propertyName -> {
            final Object component = persistentComponents.get(propertyName);
            if (component instanceof CheckMenuItem) {
                properties.put(propertyName, Boolean.toString(((CheckMenuItem) component).isSelected()));
            } else if (component instanceof SplitPane) {
                properties.put(propertyName, Double.toString(((SplitPane) component).getDividerPositions()[0]));
            }
            // else if(component instanceof Control) {
            // if(propertyName.toLowerCase().indexOf("width") != -1) {
            // properties.put(propertyName, Double.toString(((Control)
            // component).getWidth()));
            // }
            // else {
            // properties.put(propertyName, Double.toString(((Control)
            // component).getHeight()));
            // }
            // }
            else if (component instanceof Stage) {
                if (propertyName.toLowerCase().contains("width"))
                    properties.put(propertyName, Double.toString(((Stage) component).getWidth()));
                else {
                    properties.put(propertyName, Double.toString(((Stage) component).getHeight()));
                }
            }
        });
        PropertiesUtils.saveProperties();
    }


    @SneakyThrows
    @PreDestroy
    void preDestroy() {
        log.atInfo().log("save user preferences:{}", Arrays.toString(preferences.keys()));
        preferences.flush();
    }
}
