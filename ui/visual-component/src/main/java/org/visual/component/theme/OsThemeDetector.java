/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.visual.component.theme;

import java.util.Objects;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.visual.shared.OS;

/**
 * For detecting the theme (dark/light) used by the Operating System.
 *
 * @author Daniel Gyorffy
 */
@Slf4j
public abstract class OsThemeDetector {

    private static volatile OsThemeDetector osThemeDetector;

    @NotNull public static OsThemeDetector getDetector() {
        OsThemeDetector instance = osThemeDetector;

        if (Objects.isNull(instance)) {
            synchronized (OsThemeDetector.class) {
                instance = osThemeDetector;

                if (Objects.nonNull(instance)) {
                    return instance;
                }
                osThemeDetector = instance = createDetector();
            }
        }

        return instance;
    }

    @Contract(" -> new")
    private static @NotNull OsThemeDetector createDetector() {
        if (OS.isWindows10OrLater()) {
            logDetection("Windows 10", WindowsThemeDetector.class);
            return new WindowsThemeDetector();
        } else if (OS.isGnome()) {
            logDetection("Gnome", GnomeThemeDetector.class);
            return new GnomeThemeDetector();
        } else if (OS.isMacOsMojaveOrLater()) {
            logDetection("MacOS", MacOSThemeDetector.class);
            return new MacOSThemeDetector();
        } else {
            log.warn("Theme detection is not supported on the system: {} {}", OS.family, OS.version);
            log.warn("Creating empty detector...");
            return new EmptyDetector();
        }
    }

    private static void logDetection(String desktop, @NotNull Class<? extends OsThemeDetector> detectorClass) {
        log.atDebug().log("Supported Desktop detected: {}", desktop);
        log.atDebug().log("Creating {}...", detectorClass.getName());
    }

    /**
     * Returns that the os using a dark or a light theme.
     *
     * @return {@code true} if the os uses dark theme; {@code false} otherwise.
     */
    public abstract boolean isDark();

    /**
     * Registers a {@link Consumer} that will listen to a theme-change.
     *
     * @param darkThemeListener the {@link Consumer} that accepts a {@link Boolean} that represents
     *                          that the os using a dark theme or not
     */
    public abstract void registerListener(@NotNull Consumer<Boolean> darkThemeListener);

    /**
     * Removes the listener.
     */
    public abstract void removeListener(@Nullable Consumer<Boolean> darkThemeListener);

    public static boolean isSupported() {
        return OS.isWindows10OrLater() || OS.isMacOsMojaveOrLater() || OS.isGnome();
    }

    private static final class EmptyDetector extends OsThemeDetector {
        @Override
        public boolean isDark() {
            return false;
        }

        @Override
        public void registerListener(@NotNull Consumer<Boolean> darkThemeListener) {
        }

        @Override
        public void removeListener(@Nullable Consumer<Boolean> darkThemeListener) {
        }
    }
}
