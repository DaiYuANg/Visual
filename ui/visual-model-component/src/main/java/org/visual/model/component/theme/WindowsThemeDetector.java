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

package org.visual.model.component.theme;

import com.sun.jna.platform.win32.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Determines the dark/light theme by the windows registry values through JNA.
 * Works on a Windows 10 system.
 *
 * @author Daniel Gyorffy
 * @author airsquared
 */
@Slf4j
class WindowsThemeDetector extends OsThemeDetector {

    private static final String REGISTRY_PATH = "Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize";
    private static final String REGISTRY_VALUE = "AppsUseLightTheme";

    private final Set<Consumer<Boolean>> listeners = new CopyOnWriteArraySet<>();
    private volatile DetectorThread detectorThread;

    @Override
    public boolean isDark() {
        return Advapi32Util.registryValueExists(WinReg.HKEY_CURRENT_USER, REGISTRY_PATH, REGISTRY_VALUE) &&
                Advapi32Util.registryGetIntValue(WinReg.HKEY_CURRENT_USER, REGISTRY_PATH, REGISTRY_VALUE) == 0;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public synchronized void registerListener(@NonNull Consumer<Boolean> darkThemeListener) {
        val listenerAdded = listeners.add(darkThemeListener);
        val singleListener = listenerAdded && listeners.size() == 1;
        val currentDetectorThread = detectorThread;
        val threadInterrupted = currentDetectorThread != null && currentDetectorThread.isInterrupted();

        if (singleListener || threadInterrupted) {
            final DetectorThread newDetectorThread = new DetectorThread(this);
            this.detectorThread = newDetectorThread;
            newDetectorThread.start();
        }
    }

    @Override
    public synchronized void removeListener(@Nullable Consumer<Boolean> darkThemeListener) {
        listeners.remove(darkThemeListener);
        if (listeners.isEmpty()) {
            this.detectorThread.interrupt();
            this.detectorThread = null;
        }
    }

    /**
     * Thread implementation for detecting the theme changes
     */
    private static final class DetectorThread extends Thread {

        private final WindowsThemeDetector themeDetector;

        private boolean lastValue;

        DetectorThread(@NotNull WindowsThemeDetector themeDetector) {
            this.themeDetector = themeDetector;
            this.lastValue = themeDetector.isDark();
            this.setName("Windows 10 Theme Detector Thread");
            this.setDaemon(true);
            this.setPriority(Thread.NORM_PRIORITY - 1);
        }

        @Override
        public void run() {
            val hkey = new WinReg.HKEYByReference();
            int err = Advapi32.INSTANCE.RegOpenKeyEx(WinReg.HKEY_CURRENT_USER, REGISTRY_PATH, 0, WinNT.KEY_READ, hkey);
            if (err != W32Errors.ERROR_SUCCESS) {
                throw new Win32Exception(err);
            }

            while (!this.isInterrupted()) {
                err = Advapi32.INSTANCE.RegNotifyChangeKeyValue(hkey.getValue(), false, WinNT.REG_NOTIFY_CHANGE_LAST_SET, null, false);
                if (err != W32Errors.ERROR_SUCCESS) {
                    throw new Win32Exception(err);
                }

                boolean currentDetection = themeDetector.isDark();
                if (currentDetection != this.lastValue) {
                    lastValue = currentDetection;
                    log.debug("Theme change detected: dark: {}", currentDetection);
                    themeDetector.listeners.forEach(listener -> listener.accept(currentDetection));
                }
            }
            Advapi32Util.registryCloseKey(hkey.getValue());
        }
    }
}
