plugins {
  application
  alias(libs.plugins.graalvm)
  alias(libs.plugins.shadow)
  alias(libs.plugins.jlink)
  alias(libs.plugins.javamodularity)
  alias(libs.plugins.docker)
  alias(libs.plugins.maniftest)
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
  implementation(projects.visualDataStructure)
  implementation(projects.visualCore)
  implementation(libs.logback)
  implementation(libs.slf4jJulBridage)
  implementation(libs.slf4jJdkPlatform)
  implementation(libs.fastutil)
  implementation(libs.guava)
  implementation(libs.mutiny)
  implementation(libs.mutiny.vertx)
  implementation(libs.vertx.micrometer.metrics)
  implementation(libs.micrometer.registry.jmx)
  implementation(libs.vertx.core)
  implementation(libs.mutiny.vertx.web.templ.thymeleaf)
  implementation(libs.vertx.web.templ.thymeleaf)
  implementation(libs.bootstrap)
  implementation(libs.tailwindcss)
  implementation(libs.agrona)
  implementation(libs.jgrapht)
  implementation(libs.vertx.web)
  implementation(libs.mutiny.vertx.web)
  implementation(libs.gestalt)
  implementation(libs.gestalt.yaml)
  implementation(libs.avaje.inject)
  annotationProcessor(libs.avaje.inject.generator)
  testImplementation(libs.avaje.inject.test)
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