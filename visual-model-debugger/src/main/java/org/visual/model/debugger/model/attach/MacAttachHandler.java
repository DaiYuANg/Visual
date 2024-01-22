/*
 * Scenic View,
 * Copyright (C) 2013 Jonathan Giles, Ander Ruiz, Amy Fowler
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
package org.visual.model.debugger.model.attach;

import static org.visual.model.debugger.model.attach.AttachHandlerFactory.doBasicJdkSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.Nullable;

/**
 *
 */
@Slf4j
public class MacAttachHandler extends AttachHandlerBase {
    private static final String[] PATHS_TO_TOOLS_JAR = new String[]{
            "Contents/Home/lib/tools.jar",
            "lib/tools.jar"
    };

    @SneakyThrows
    @Override
    public void getOrderedJDKPaths(List<JDKToolsJarPair> jdkPaths) {
        doBasicJdkSearch(jdkPaths);

        // go down mac special path
        getToolsClassPathOnMAC(jdkPaths);
    }

    @SneakyThrows
    private void getToolsClassPathOnMAC(List<JDKToolsJarPair> jdkPaths) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        val process = runtime.exec("/usr/libexec/java_home -V");
        process.waitFor();
        try (
                val inputStream = Objects.requireNonNull(process).getErrorStream();
                val reader = new InputStreamReader(inputStream);
                val bufferedReader = new BufferedReader(reader)
        ) {
            if (bufferedReader.ready()) {
                bufferedReader.readLine();
            }
            while (bufferedReader.ready()) {
                String versionString = bufferedReader.readLine();
                versionString = versionString.trim();

                String path;
                String[] splitted = versionString.split("\t");

                if (splitted.length != 3) {
                    continue;
                }
                path = splitted[splitted.length - 1];

                val jdkHome = new File(path);
                val toolsFile = searchForToolsJar(jdkHome);
                if (toolsFile == null || !toolsFile.exists()) {
                    continue;
                }
                jdkPaths.add(new JDKToolsJarPair(jdkHome, toolsFile));
            }
        }
    }

    private @Nullable File searchForToolsJar(File jdkHome) {
        return Arrays.stream(PATHS_TO_TOOLS_JAR).map(pathToToolsJar -> new File(jdkHome, pathToToolsJar)).filter(File::exists).findFirst().orElse(null);
    }
}
