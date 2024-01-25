package org.visual.model.shared;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.val;
import org.apache.commons.lang3.SystemUtils;
import org.jetbrains.annotations.NotNull;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter
@ToString
public enum OS {
    MAC,

    WINDOWS,

    LINUX,

    UNKNOWN;

    public static final OS OS = currentPlatform();

    public static final int cpuCore = Runtime.getRuntime().availableProcessors();

    public static final SystemInfo sysInfo = new SystemInfo();

    public static final OperatingSystem.OSVersionInfo version = sysInfo.getOperatingSystem().getVersionInfo();

    public static final String family = sysInfo.getOperatingSystem().getFamily();

    private static OS currentPlatform() {
        if (SystemUtils.IS_OS_MAC) return MAC;

        if (SystemUtils.IS_OS_LINUX) return LINUX;

        if (SystemUtils.IS_OS_WINDOWS) return WINDOWS;

        return UNKNOWN;
    }

    public static boolean isWindows10OrLater() {
        return SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_11;
    }

    public static boolean isMacOsMojaveOrLater() {
        return SystemUtils.IS_OS_MAC_OSX_MOJAVE ||
                SystemUtils.IS_OS_MAC_OSX_BIG_SUR ||
                SystemUtils.IS_OS_MAC_OSX_CATALINA ||
                SystemUtils.IS_OS_MAC_OSX_MONTEREY ||
                SystemUtils.IS_OS_MAC_OSX_VENTURA;
    }

    @SneakyThrows
    public static boolean isGnome() {
        return SystemUtils.IS_OS_LINUX && (
                queryResultContains("echo $XDG_CURRENT_DESKTOP", "gnome") ||
                        queryResultContains("echo $XDG_DATA_DIRS | grep -Eo 'gnome'", "gnome") ||
                        queryResultContains("ps -e | grep -E -i \"gnome\"", "gnome")
        );
    }

    private static boolean queryResultContains(@NotNull String cmd, @NotNull String subResult) throws IOException {
        return query(cmd).toLowerCase().contains(subResult);
    }

    @NotNull
    private static String query(@NotNull String cmd) throws IOException {
        val process = new ProcessBuilder().command(cmd).start();
        val stringBuilder = new StringBuilder();
        try (val reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String actualReadLine;
            while ((actualReadLine = reader.readLine()) != null) {
                if (!stringBuilder.isEmpty())
                    stringBuilder.append('\n');
                stringBuilder.append(actualReadLine);
            }
        }
        return stringBuilder.toString();
    }
}
