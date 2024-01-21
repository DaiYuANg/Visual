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
package org.visual.model.debugger.utils;

import java.io.*;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesUtils {

    private static final String SCENIC_VIEW_PROPERTIES_FILE = "scenicView.properties";

    private static Properties properties;

    private PropertiesUtils() {

    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = loadProperties();
        }
        return properties;
    }

    public static boolean containsKey(String key) {
        return getProperties().contains(key);
    }

    private static Properties loadProperties() {
        Properties _properties = new Properties();

        try {
            final File propertiesFile = new File(SCENIC_VIEW_PROPERTIES_FILE);
            if (propertiesFile.exists()) {
                try (FileInputStream in = new FileInputStream(propertiesFile)) {
                    _properties.load(in);
                }
            }
        } catch (final Exception e) {
            log.info("Error while loading preferences");
        }
        return _properties;
    }

    public static void saveProperties() {
        try {
            final File propertiesFile = new File(SCENIC_VIEW_PROPERTIES_FILE);
            try (FileOutputStream out = new FileOutputStream(propertiesFile)) {
                properties.store(out, "ScenicView properties");
            }
        } catch (final Exception e) {
            log.error(e.getMessage(),e, "Error while saving preferences");
        }
    }

}
