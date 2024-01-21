plugins {
  alias(libs.plugins.javafx)
  java
}

group = "org.visual.model.ui.inspector"

version = "unspecified"

javafx {
  version = libs.versions.javafxVersion.get()
  modules(
      "javafx.controls",
      "javafx.fxml",
      "javafx.graphics",
      "javafx.swing",
      "javafx.media",
      "javafx.web")
  configurations = arrayOf("implementation", "testImplementation")
}

dependencies {
  implementation(projects.ui.visualModelComponent)
  implementation(projects.visualModelShared)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  implementation(libs.gestaltConfig)
  implementation(libs.pcollections)
    implementation(projects.visualModelI18n)
}

tasks.jar {
  manifest { attributes("Premain-Class" to "org.visual.model.debugger.VisualModelDebugger") }
}
