plugins {
  `kotlin-project`
}
group = "org.visual.debugger"

val mainClassPath = "org.visual.debugger.VisualDebugger"

version = "unspecified"

dependencies {
  implementation(projects.ui.visualComponent)
  implementation(projects.module.visualShared)
  implementation(libs.gestaltConfig)
  implementation(libs.pcollections)
  implementation(projects.ui.visualI18n)
  implementation(libs.gestaltGuice)
  implementation(libs.gestaltKotlin)
  implementation(libs.bytebuddy)
  implementation(projects.ui.visualTextEditor)
  implementation(libs.guice)
  implementation(libs.preferencesfx)
  implementation(libs.classgraph)
}

tasks.jar {
  manifest { attributes("Premain-Class" to "org.visual.model.debugger.VisualModelDebugger") }
}