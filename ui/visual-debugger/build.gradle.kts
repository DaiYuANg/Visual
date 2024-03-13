group = "org.visual.debugger"

val mainClassPath = "org.visual.debugger.VisualDebugger"

dependencies {
  implementation(projects.ui.visualComponent)
  implementation(projects.module.visualShared)
  implementation(libs.pcollections)
  implementation(projects.ui.visualI18n)
  implementation(libs.bytebuddy)
  implementation(projects.ui.visualTextEditor)
  implementation(libs.preferencesfx)
  implementation(libs.guice)
  implementation(libs.fastutil)
}

tasks.jar {
  manifest { attributes("Premain-Class" to "org.visual.model.debugger.VisualModelDebugger") }
}