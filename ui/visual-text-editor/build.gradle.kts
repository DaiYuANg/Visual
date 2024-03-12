group = "org.visual.model.text.editor"

dependencies {
  api(projects.module.visualShared)
  implementation(projects.ui.visualComponent)
  implementation(libs.fastutil)
  implementation(libs.jgrapht)
}