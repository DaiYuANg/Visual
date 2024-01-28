package org.visual.shared;

import com.github.noconnor.junitperf.JUnitPerfInterceptor;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JUnitPerfInterceptor.class)
class ManifestReaderTest {

  @SneakyThrows
  @Test
  @JUnitPerfTest(
      threads = 50,
      durationMs = 125_000,
      warmUpMs = 10_000,
      maxExecutionsPerSecond = 11_000)
  @JUnitPerfTestRequirement(
      percentiles = "90:7,95:7,98:7,99:8",
      executionsPerSec = 10_000,
      allowedErrorPercentage = 0.10f)
  void loadManifest() {
    System.err.println(ManifestReader.INSTANCE().loadManifestObject());
  }

  @Test
  @JUnitPerfTest(
      threads = 50,
      durationMs = 125_000,
      warmUpMs = 10_000,
      maxExecutionsPerSecond = 11_000)
  @JUnitPerfTestRequirement(
      percentiles = "90:7,95:7,98:7,99:8",
      executionsPerSec = 10_000,
      allowedErrorPercentage = 0.10f)
  void loadManifestStrings() {}

  @SneakyThrows
  @Test
  @JUnitPerfTest(
      threads = 50,
      durationMs = 125_000,
      warmUpMs = 10_000,
      maxExecutionsPerSecond = 11_000)
  @JUnitPerfTestRequirement(
      percentiles = "90:7,95:7,98:7,99:8",
      executionsPerSec = 10_000,
      allowedErrorPercentage = 0.10f)
  void loadManifestObject() {
    System.err.println(ManifestReader.INSTANCE().loadManifestStrings());
  }
}
