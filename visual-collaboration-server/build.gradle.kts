plugins {
  application
  alias(libs.plugins.graalvm)
  alias(libs.plugins.shadow)
  alias(libs.plugins.jlink)
  alias(libs.plugins.javamodularity)
  alias(libs.plugins.docker)
}

apply<ModulePlugin>()

group = "org.visual.collaboration.server"

val mainClassPath = "$group.CollaborationApplication"
val mainModulePath = group.toString()

application {
  mainClass.set(mainClassPath)
  mainModule.set(mainModulePath)
}

dependencies {
  implementation(libs.logback)
  implementation(libs.slf4jJulBridage)
  implementation(libs.slf4jJdkPlatform)
  implementation(libs.vertx.tcp.eventbus.bridge)
  implementation(libs.picocli)
  annotationProcessor(libs.picocli.codegen)
  implementation(libs.mutiny.vertx.tcp.eventbus.bridge)
  implementation(projects.visualCore)
}

tasks.shadowJar { mergeServiceFiles() }

tasks.prepareMergedJarsDir { dependsOn(tasks.jar) }

jlink {
  options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
  enableCds()
  mainClass.set(mainClassPath)
  moduleName.set(mainModulePath)
  forceMerge("micrometer")
  mergedModule {
    additive = true
    excludeProvides(
      mapOf(
        IMPLEMENTATION to
          "io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor",
      ),
    )
  }
  addExtraDependencies("logback")
}

docker {
  javaApplication {
    baseImage.set("java:openjdk-21-jre")
    ports.set(listOf(9090, 5701))
    images.set(setOf(group.toString().replace(".", "-")))
    jvmArgs.set(listOf("-Xms256m", "-Xmx2048m"))
  }
}