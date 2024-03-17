group = "org.visual.graph-editor"

version = "unspecified"

dependencies {
  implementation(projects.ui.visualComponent)
  implementation(libs.pcollections)
  implementation(projects.module.visualShared)
  api("org.eclipse.emf:org.eclipse.emf.ecore:2.35.0")
  api("org.eclipse.emf:org.eclipse.emf.common:2.30.0")
  api("org.eclipse.emf:org.eclipse.emf.ecore.xmi:2.36.0")
  api("org.eclipse.emf:org.eclipse.emf.edit:2.20.0")
  implementation(libs.jgrapht)
  implementation(libs.fastutil)
}