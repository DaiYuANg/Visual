plugins {
  `kotlin-project`
}

group = "org.visual.model.text.editor"

version = "unspecified"

dependencies {
  api(projects.module.visualShared)
  implementation(projects.ui.visualComponent)
}