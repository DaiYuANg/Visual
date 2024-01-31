plugins {
  `java-library`
  `kotlin-project`
}
plugins.getPlugin(FxProjectPlugin::class.java).modules("javafx.media", "javafx.web")
group = "org.visual.debugger"

val mainClassPath = "org.visual.debugger.VisualDebugger"

version = "unspecified"

dependencies {
  implementation("io.github.classgraph:classgraph:4.8.165")
  implementation(projects.ui.visualComponent)
  implementation(projects.module.visualShared)
  implementation(libs.gestaltConfig)
  implementation(libs.pcollections)
  implementation(projects.ui.visualI18n)
  implementation(projects.libs.event)
  implementation(libs.gestaltGuice)
  implementation(libs.gestaltKotlin)
  implementation("net.bytebuddy:byte-buddy:1.14.11")
  implementation(projects.ui.visualTextEditor)
  implementation("com.google.inject:guice:7.0.0")
}

tasks.jar {
  manifest { attributes("Premain-Class" to "org.visual.model.debugger.VisualModelDebugger") }
}