plugins {
//  alias(libs.plugins.javafx)
  application
  java
//  alias(libs.plugins.jlink)
}

group = "org.visual.model.database"

version = "unspecified"

dependencies {
  implementation(projects.ui.visualModelGraphEditor)
  implementation(projects.ui.visualModelTextEditor)
  implementation(libs.avajeInject)
  implementation(libs.avajeValidaor)
  implementation(libs.avajeValidaorCodegen)
  implementation(libs.picocli)
  annotationProcessor(libs.picocliCodegen)
  annotationProcessor(libs.avajeInjectGenerator)
}
//
//javafx {
//  version = libs.versions.javafxVersion.get()
//  modules(
//      "javafx.controls",
//      "javafx.fxml",
//      "javafx.graphics",
//      "javafx.swing",
//      "javafx.media",
//      "javafx.web")
//  configurations = arrayOf("implementation", "testImplementation")
//}
