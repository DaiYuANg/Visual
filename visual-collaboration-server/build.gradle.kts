import org.siouan.frontendgradleplugin.infrastructure.gradle.InstallFrontendTask

plugins {
  application
  alias(libs.plugins.graalvm)
  alias(libs.plugins.shadow)
  alias(libs.plugins.jlink)
  alias(libs.plugins.javamodularity)
  alias(libs.plugins.docker)
  alias(libs.plugins.maniftest)
  alias(libs.plugins.frontend)
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
  implementation(libs.agrona)
  implementation(libs.jgrapht)
  implementation(libs.vertx.web)
  implementation(libs.mutiny.vertx.web)
  implementation(libs.gestalt)
  implementation(libs.gestalt.yaml)
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

frontend {
  nodeVersion.set("22.11.0")
  packageJsonDirectory.set(project.layout.projectDirectory.dir("src/main/frontend"))
  assembleScript.set("build")
}

tasks.withType(InstallFrontendTask::class.java) {
  environmentVariables.put("COREPACK_NPM_REGISTRY", "https://registry.npmmirror.com")
}

tasks.create("copyFrontend", Copy::class) {
  group = "build"
  from(layout.projectDirectory.dir("src/main/frontend/dist"))
  destinationDir = layout.buildDirectory.dir("classes/java/main/webroot").get().asFile
  dependsOn(tasks.assembleFrontend)
}

tasks.processResources {
  dependsOn("copyFrontend")
}