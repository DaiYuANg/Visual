plugins {
//  alias(libs.plugins.javafx)
  `kotlin-project`
}

group = "org.visual.model.text.editor"

version = "unspecified"

dependencies {
  api(projects.ui.visualComponentAnnotation)
  api(projects.module.visualShared)
  implementation(projects.ui.visualComponent)
}

//javafx {
//  version = libs.versions.javafxVersion.get()
//  modules(
//      "javafx.controls",
//      "javafx.fxml",
//      "javafx.graphics",
//      "javafx.swing",
//      "javafx.media",
//  )
//  configurations = arrayOf("implementation", "testImplementation")
//}
