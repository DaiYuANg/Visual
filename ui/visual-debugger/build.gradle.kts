group = "org.visual.debugger"

val mainClassPath = "org.visual.debugger.VisualDebugger"

dependencies {
  implementation(projects.ui.visualComponent)
  implementation(projects.module.visualShared)
  implementation(libs.gestaltConfig)
  implementation(libs.pcollections)
  implementation(projects.ui.visualI18n)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  implementation(libs.bytebuddy)
  implementation(projects.ui.visualTextEditor)
  implementation(libs.preferencesfx)
  testImplementation(libs.avajeInjectTest)
}

tasks.jar {
  manifest { attributes("Premain-Class" to "org.visual.model.debugger.VisualModelDebugger") }
}