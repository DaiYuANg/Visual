import org.apache.commons.lang3.SystemUtils

apply<ModulePlugin>()

group = "org.visual.core"

dependencies {
  compileOnly(libs.jetbrains.annotation)
  implementation(libs.oshi)

  api(libs.mutiny)
  api(libs.mutiny.vertx)
  implementation(libs.vertx.micrometer.metrics)
  implementation(libs.micrometer.registry.jmx)
  api(libs.vertx.core)

  if (SystemUtils.IS_OS_MAC) {
    logger.info(SystemUtils.OS_ARCH)
    implementation(variantOf(libs.netty.resolver.dns.native.macos) { classifier("osx-aarch_64") })
  }
}