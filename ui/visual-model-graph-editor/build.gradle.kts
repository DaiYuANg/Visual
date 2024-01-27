plugins {
//  alias(libs.plugins.javafx)
  `kotlin-project`
}

group = "org.visual.model.graph-editor"

version = "unspecified"

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

dependencies {
  implementation(projects.ui.visualModelComponent)
  implementation(libs.pcollections)
  implementation(projects.module.visualModelShared)
  api("org.eclipse.emf:org.eclipse.emf.ecore:2.35.0")
  api("org.eclipse.emf:org.eclipse.emf.common:2.29.0")
  api("org.eclipse.emf:org.eclipse.emf.ecore.xmi:2.36.0")
  api("org.eclipse.emf:org.eclipse.emf.edit:2.20.0")
  implementation(projects.ui.visualModelComponentAnnotation)
}
