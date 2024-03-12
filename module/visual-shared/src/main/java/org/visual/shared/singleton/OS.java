package org.visual.shared.singleton;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.SystemUtils;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

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

  public static final OperatingSystem.OSVersionInfo version =
      sysInfo.getOperatingSystem().getVersionInfo();

  public static final String family = sysInfo.getOperatingSystem().getFamily();

  private static OS currentPlatform() {
    if (SystemUtils.IS_OS_MAC) return MAC;

    if (SystemUtils.IS_OS_LINUX) return LINUX;

    if (SystemUtils.IS_OS_WINDOWS) return WINDOWS;

    return UNKNOWN;
  }
}
