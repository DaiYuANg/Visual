apply<ModulePlugin>()

group = "org.visual.core"

dependencies {
  compileOnly(libs.jetbrains.annotation)
  api(libs.oshi)

  implementation(libs.mutiny)
  implementation(libs.mutiny.vertx)
  implementation(libs.vertx.hazelcast)
  implementation(libs.vertx.micrometer.metrics)
  implementation(libs.micrometer.registry.jmx)
  implementation(libs.vertx.core)
}